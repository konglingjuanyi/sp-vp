package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IJourneyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 行程服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-10 9:10
 * modify date 2015-7-24 11:22
 * @version 0.5, 2015-7-24
 */
@Service
@Qualifier("journeyServiceProxy")
public class JourneyServiceProxy extends BaseProxy implements IJourneyService {

    @Autowired
    @Qualifier("journeyService")
    private IJourneyService journeyService;
    @Autowired
    private IJourneyDaoService journeyDaoService;
    @Autowired
    private IEvent event;

    private static final Integer END_STATUS = 2;

    @Override
    public void startJourney(OtaDto otaDto, Date startTime, Integer tboxJourneyId,
                             Integer keyId) throws Exception {
        // 这里存在tboxJourneyId唯一的特殊情况，当满足时跳过事务
        Journey journey = journeyDaoService.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.startJourney(otaDto, startTime, tboxJourneyId, keyId);
        }
        else {
            event.start(otaDto);
            journeyService.startJourney(otaDto, startTime, tboxJourneyId, keyId);
            event.end(otaDto);
        }
    }

    @Override
    public void updateJourney(OtaDto otaDto, Integer tboxJourneyId, Integer instFuelConsumption,
                              VehiclePosDto vehiclePosDto) throws Exception {
        // 这里存在tboxJourneyId唯一的特殊情况，当满足时跳过事务
        Journey journey = journeyDaoService.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.updateJourney(otaDto, tboxJourneyId, instFuelConsumption, vehiclePosDto);
        }
        else {
            event.start(otaDto);
            journeyService.updateJourney(otaDto, tboxJourneyId, instFuelConsumption, vehiclePosDto);
            event.end(otaDto);
        }
    }

    @Override
    public void endJourney(OtaDto otaDto, VehiclePosDto startVehiclePosDto, VehiclePosDto endVehiclePosDto,
                           Integer tboxJourneyId, Integer distance, Integer avgSpeed, Integer fuelEco,
                           Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp,
                           Integer fuelRange) throws Exception {
        // 这里存在tboxJourneyId唯一的特殊情况，当满足时跳过事务
        Journey journey = journeyDaoService.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.endJourney(otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId, distance,
                    avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
        }
        else {
            event.start(otaDto);
            journeyService.endJourney(otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId, distance,
                    avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
            event.end(otaDto);
        }
    }
}
