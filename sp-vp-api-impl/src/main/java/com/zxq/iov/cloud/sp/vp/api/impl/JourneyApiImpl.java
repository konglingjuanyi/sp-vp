/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-09       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.JourneyApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.api.IJourneyApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IJourneyService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防服务 行程API实现类
 */
@Service
public class JourneyApiImpl extends BaseApi implements IJourneyApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyApiImpl.class);

    @Autowired
    private IJourneyService journeyService;
    @Autowired
    private IEventService eventService;

    private static final Integer END_STATUS = 2;

    @Override
    public void startJourney(OtaDto otaDto, Date startTime, Integer tboxJourneyId, Integer keyId)
            throws ServLayerException {
        AssertRequired("otaDto,startTime,tboxJourneyId", otaDto, startTime, tboxJourneyId);
        Journey journey = journeyService.getByIdAndTboxJourneyId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.start(getTboxById(otaDto.getTboxId()), startTime, tboxJourneyId, keyId);
        }
        else {
            Event event = new EventAssembler().fromOtaDto(otaDto);
            eventService.start(event);
            if(!event.isRetry()) {
                journeyService.start(getTboxById(otaDto.getTboxId()), startTime, tboxJourneyId, keyId);
                eventService.end(event);
            }
        }
    }

    @Override
    public void updateJourney(OtaDto otaDto, Integer tboxJourneyId, Integer instFuelConsumption,
                              VehiclePosDto vehiclePosDto) throws ServLayerException {
        AssertRequired("otaDto,tboxJourneyId,vehiclePosDto", otaDto, tboxJourneyId, vehiclePosDto);
        Journey journey = journeyService.getByIdAndTboxJourneyId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.update(getTboxById(otaDto.getTboxId()), tboxJourneyId, instFuelConsumption,
                    new VehiclePosDtoAssembler().fromDto(vehiclePosDto));
        }
        else {
            Event event = new EventAssembler().fromOtaDto(otaDto);
            eventService.start(event);
            if(!event.isRetry()) {
                journeyService.update(getTboxById(otaDto.getTboxId()), tboxJourneyId, instFuelConsumption,
                        new VehiclePosDtoAssembler().fromDto(vehiclePosDto));
                eventService.end(event);
            }
        }
    }

    @Override
    public void endJourney(OtaDto otaDto, VehiclePosDto startVehiclePosDto, VehiclePosDto endVehiclePosDto,
                           Integer tboxJourneyId, Integer distance, Integer avgSpeed, Integer fuelEco,
                           Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp, Integer fuelRange)
            throws ServLayerException {
        AssertRequired("otaDto,startVehiclePosDto,endVehiclePosDto,tboxJourneyId,distance,avgSpeed",
                otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId, distance, avgSpeed);
        VehiclePosDtoAssembler assembler = new VehiclePosDtoAssembler();
        Journey journey = journeyService.getByIdAndTboxJourneyId(tboxJourneyId, otaDto.getTboxId());
        if(null != journey && journey.getStatus().intValue() == END_STATUS.intValue()) {
            journeyService.end(getTboxById(otaDto.getTboxId()), assembler.fromDto(startVehiclePosDto),
                    assembler.fromDto(endVehiclePosDto), tboxJourneyId, distance, avgSpeed, fuelEco,
                    odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
        }
        else {
            Event event = new EventAssembler().fromOtaDto(otaDto);
            eventService.start(event);
            if(!event.isRetry()) {
                journeyService.end(getTboxById(otaDto.getTboxId()), assembler.fromDto(startVehiclePosDto),
                        assembler.fromDto(endVehiclePosDto), tboxJourneyId, distance, avgSpeed, fuelEco,
                        odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
                eventService.end(event);
            }
        }
    }

}