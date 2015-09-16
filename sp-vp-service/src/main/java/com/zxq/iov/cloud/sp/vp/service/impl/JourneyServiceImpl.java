/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-09       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.JourneyServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDao;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IJourneyService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 行程服务接口实现类
 */
@Service
public class JourneyServiceImpl extends BaseService implements IJourneyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JourneyServiceImpl.class);

	@Autowired
	private IJourneyDao journeyDao;
	@Autowired
	private IStatusService statusService;

	private static final Integer RUNNING_STATUS = 1;
	private static final Integer END_STATUS = 2;

	@Override
	public void start(Tbox tbox, Date startTime, Integer tboxJourneyId, Integer keyId) throws ServLayerException {
		AssertRequired("tboxId,startTime,tboxJourneyId", tbox.getTboxId(), startTime, tboxJourneyId);
		Journey journey = getByIdAndTboxJourneyId(tboxJourneyId, tbox.getTboxId());
		if (null == journey) {
			journey = new Journey(tboxJourneyId, tbox.getTboxId(), tbox.getUserId(), tbox.getVin());
			journey.setStartTime(startTime);
			journey.setKeyId(keyId);
			journey.setStatus(RUNNING_STATUS);
			journeyDao.createJourney(journey);
		} else {
			journey.setStartTime(startTime);
			journey.setKeyId(keyId);
			journeyDao.updateJourney(journey);
		}
	}

	@Override
	public void update(Tbox tbox, Integer tboxJourneyId, Integer instFuelConsumption, VehiclePos vehiclePos)
			throws ServLayerException {
		AssertRequired("tboxId,vin,userId,tboxJourneyId,vehiclePos", tbox.getTboxId(), tbox.getVin(), tbox.getUserId(),
				tboxJourneyId, vehiclePos);
		Journey journey = getByIdAndTboxJourneyId(tboxJourneyId, tbox.getTboxId());
		if (null == journey) {
			journey = new Journey(tboxJourneyId, tbox.getTboxId(), tbox.getUserId(), tbox.getVin());
			journey.setStatus(RUNNING_STATUS);
			journeyDao.createJourney(journey);
		}
		List<VehicleStatus> vehicleStatuses = new ArrayList<>();
		vehicleStatuses.add(new VehicleStatus("instFuelConsumption", instFuelConsumption));
		statusService.logVehicleInfo(tbox, Constants.VEHICLE_INFO_SOURCE_JOURNEY, journey.getId(), vehiclePos,
				vehicleStatuses, null, null, null);
	}

	@Override
	public void end(Tbox tbox, VehiclePos startVehiclePos, VehiclePos endVehiclePos, Integer tboxJourneyId,
			Integer distance, Integer avgSpeed, Integer fuelEco, Integer odometer, Integer fuelLevelPrc,
			Integer fuelLevelDisp, Integer fuelRange) throws ServLayerException {
		AssertRequired("tboxId,vin,userId,startVehiclePos,endVehiclePos,tboxJourneyId,distance,avgSpeed,fuelEco",
				tbox.getTboxId(), tbox.getVin(), tbox.getUserId(), startVehiclePos, endVehiclePos, tboxJourneyId,
				distance, avgSpeed, fuelEco);
		Journey journey = getByIdAndTboxJourneyId(tboxJourneyId, tbox.getTboxId());
		if (null == journey) {
			journey = new Journey(tboxJourneyId, tbox.getTboxId(), tbox.getUserId(), tbox.getVin());
			journey.setStatus(END_STATUS);
			journeyDao.createJourney(journey);
		} else {
			if (null != journey.getStartTime() && startVehiclePos.getGpsTime().before(journey.getStartTime())) {
				journey.setStartTime(startVehiclePos.getGpsTime());
			}
			journey.setEndTime(endVehiclePos.getGpsTime());
			journey.setDistance(distance);
			journey.setAvgSpeed(avgSpeed);
			journey.setFuelConsumption(fuelEco);
			journey.setStatus(END_STATUS);
			journeyDao.updateJourney(journey);
		}
		journey.setStartVehicleInfoId(statusService
				.logVehicleInfo(tbox, Constants.VEHICLE_INFO_SOURCE_JOURNEY, journey.getId(), startVehiclePos, null,
						null, null, null).getId());
		journey.setEndVehicleInfoId(statusService
				.logVehicleInfo(tbox, Constants.VEHICLE_INFO_SOURCE_JOURNEY, journey.getId(), endVehiclePos, null, null,
						null, null).getId());
		journeyDao.updateJourney(journey);
	}

	@Override
	public Journey getByIdAndTboxJourneyId(Integer tboxJourneyId, Long tboxId) throws ServLayerException {
		AssertRequired("tboxId,tboxJourneyId", tboxJourneyId, tboxId);
		return journeyDao.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, tboxId);
	}

}