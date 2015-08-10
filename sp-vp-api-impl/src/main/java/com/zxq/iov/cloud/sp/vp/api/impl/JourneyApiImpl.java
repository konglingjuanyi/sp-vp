package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IJourneyApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IJourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 行程API实现类
 *
 * @author 叶荣杰
 * create date 2015-6-9 14:19
 * modify date 2015-8-7 13:17
 * @version 0.11, 2015-8-7
 */
@Service
public class JourneyApiImpl extends BaseApi implements IJourneyApi {

    @Autowired
    private IJourneyService journeyService;
    @Autowired
    private IEventService eventService;

    private static final Integer END_STATUS = 2;

    @Override
    public void startJourney(OtaDto otaDto, Date startTime, Integer tboxJourneyId, Integer keyId)
            throws Exception {
        AssertRequired("otaDto,startTime,tboxJourneyId", otaDto, startTime, tboxJourneyId);
        Journey journey = journeyService.getByIdAndTboxJourneyId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.start(otaDto.getTboxId(), startTime, tboxJourneyId, keyId);
        }
        else {
            eventService.start(getVin(otaDto), getCode(otaDto));
            journeyService.start(otaDto.getTboxId(), startTime, tboxJourneyId, keyId);
            eventService.end(getVin(otaDto), getCode(otaDto));
        }
    }

    @Override
    public void updateJourney(OtaDto otaDto, Integer tboxJourneyId, Integer instFuelConsumption,
                              VehiclePosDto vehiclePosDto) throws Exception {
        AssertRequired("otaDto,tboxJourneyId,vehiclePosDto", otaDto, tboxJourneyId, vehiclePosDto);
        Journey journey = journeyService.getByIdAndTboxJourneyId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.update(otaDto.getTboxId(), tboxJourneyId, instFuelConsumption,
                    new VehiclePosDtoAssembler().fromDto(vehiclePosDto));
        }
        else {
            eventService.start(getVin(otaDto), getCode(otaDto));
            journeyService.update(otaDto.getTboxId(), tboxJourneyId, instFuelConsumption,
                new VehiclePosDtoAssembler().fromDto(vehiclePosDto));
            eventService.end(getVin(otaDto), getCode(otaDto));
        }
    }

    @Override
    public void endJourney(OtaDto otaDto, VehiclePosDto startVehiclePosDto, VehiclePosDto endVehiclePosDto,
                           Integer tboxJourneyId, Integer distance, Integer avgSpeed, Integer fuelEco,
                           Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp, Integer fuelRange)
            throws Exception {
        AssertRequired("otaDto,startVehiclePosDto,endVehiclePosDto,tboxJourneyId,distance,avgSpeed",
                otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId, distance, avgSpeed);
        VehiclePosDtoAssembler assembler = new VehiclePosDtoAssembler();
        Journey journey = journeyService.getByIdAndTboxJourneyId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.end(otaDto.getTboxId(), assembler.fromDto(startVehiclePosDto),
                    assembler.fromDto(endVehiclePosDto), tboxJourneyId, distance, avgSpeed, fuelEco,
                    odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
        }
        else {
            eventService.start(getVin(otaDto), getCode(otaDto));
            journeyService.end(otaDto.getTboxId(), assembler.fromDto(startVehiclePosDto),
                    assembler.fromDto(endVehiclePosDto), tboxJourneyId, distance, avgSpeed, fuelEco,
                    odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
            eventService.end(getVin(otaDto), getCode(otaDto));
        }
    }

}