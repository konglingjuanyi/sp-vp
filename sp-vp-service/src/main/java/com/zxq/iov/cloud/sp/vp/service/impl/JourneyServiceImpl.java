package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDao;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IJourneyService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 行程服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-9 14:19
 * modify date 2015-8-18 14:41
 * @version 0.12, 2015-8-18
 */
@Service
public class JourneyServiceImpl extends BaseService implements IJourneyService {

    @Autowired
    private IJourneyDao journeyDao;
    @Autowired
    private IStatusService statusService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public void start(Long tboxId, Date startTime, Integer tboxJourneyId, Integer keyId)
            throws ServLayerException {
        AssertRequired("tboxId,startTime,tboxJourneyId", tboxId, startTime, tboxJourneyId);
        Journey journey = getByIdAndTboxJourneyId(tboxJourneyId, tboxId);
        if(null == journey) {
            journey = new Journey(tboxJourneyId, tboxId, findUserIdById(tboxId),
                    findVinById(tboxId));
            journey.setStartTime(startTime);
            journey.setKeyId(keyId);
            journey.setStatus(RUNNING_STATUS);
            journeyDao.createJourney(journey);
        }
        else {
            journey.setStartTime(startTime);
            journey.setKeyId(keyId);
            journeyDao.updateJourney(journey);
        }
    }

    @Override
    public void update(Long tboxId, Integer tboxJourneyId, Integer instFuelConsumption,
                              VehiclePos vehiclePos) throws ServLayerException {
        AssertRequired("tboxId,tboxJourneyId,vehiclePos", tboxId, tboxJourneyId, vehiclePos);
        Journey journey = getByIdAndTboxJourneyId(tboxJourneyId, tboxId);
        if(null == journey) {
            journey = new Journey(tboxJourneyId, tboxId, findUserIdById(tboxId),
                    findVinById(tboxId));
            journey.setStatus(RUNNING_STATUS);
            journeyDao.createJourney(journey);
        }
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus("instFuelConsumption", instFuelConsumption));
        statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_JOURNEY,
                journey.getId(), vehiclePos, vehicleStatuses, null, null, null);
    }

    @Override
    public void end(Long tboxId, VehiclePos startVehiclePos, VehiclePos endVehiclePos,
                           Integer tboxJourneyId, Integer distance, Integer avgSpeed, Integer fuelEco,
                           Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp, Integer fuelRange)
            throws ServLayerException {
        AssertRequired("tboxId,startVehiclePos,endVehiclePos,tboxJourneyId,distance,avgSpeed,fuelEco",
                tboxId, startVehiclePos, endVehiclePos, tboxJourneyId, distance, avgSpeed, fuelEco);
        Journey journey = getByIdAndTboxJourneyId(tboxJourneyId, tboxId);
        if(null == journey) {
            journey = new Journey(tboxJourneyId, tboxId, findUserIdById(tboxId),
                    findVinById(tboxId));
            journey.setStatus(END_STATUS);
            journeyDao.createJourney(journey);
        }
        else {
            if(null != journey.getStartTime() && startVehiclePos.getGpsTime().before(journey.getStartTime())) {
                journey.setStartTime(startVehiclePos.getGpsTime());
            }
            journey.setEndTime(endVehiclePos.getGpsTime());
            journey.setDistance(distance);
            journey.setAvgSpeed(avgSpeed);
            journey.setFuelConsumption(fuelEco);
            journey.setStatus(END_STATUS);
            journeyDao.updateJourney(journey);
        }
        journey.setStartVehicleInfoId(statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_JOURNEY,
                journey.getId(), startVehiclePos, null, null, null, null).getId());
        journey.setEndVehicleInfoId(statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_JOURNEY,
                journey.getId(), endVehiclePos, null, null, null, null).getId());
        journeyDao.updateJourney(journey);
    }

    @Override
    public Journey getByIdAndTboxJourneyId(Integer tboxJourneyId, Long tboxId) throws ServLayerException {
        AssertRequired("tboxId,tboxJourneyId", tboxJourneyId, tboxId);
        return journeyDao.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, tboxId);
    }

}