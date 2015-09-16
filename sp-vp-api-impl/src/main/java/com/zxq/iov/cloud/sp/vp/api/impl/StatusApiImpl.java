/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.StatusApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.api.IStatusApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.*;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleInfoDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态API实现类
 */
@Service
public class StatusApiImpl extends BaseApi implements IStatusApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusApiImpl.class);

    @Autowired
    private IStatusService statusService;
    @Autowired
    private IEventService eventService;

    @Override
    public Long requestVehicleStatus(String vin, Integer statusType) throws ServLayerException {
        AssertRequired("vin", vin);
        if(null == statusType) {
            statusType = Constants.VEHICLE_STATUS_BASIC;
        }
        if(statusType != Constants.VEHICLE_STATUS_BASIC && statusType != Constants.VEHICLE_STATUS_ALERT) {
            throw new ServLayerException(ExceptionConstants.WRONG_VEHICLE_STATUS);
        }
        OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_STATUS, 1);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, new VehicleStatusReqDto(statusType));
        return event.getId();
    }

    @Override
    public void responseVehicleStatus(OtaDto otaDto, Date statusTime, VehiclePosDto vehiclePosDto,
                                      List<VehicleStatusDto> vehicleStatusDtos,
                                      List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,statusTime,vehiclePosDto,vehicleStatusDtos", otaDto, statusTime,
                vehiclePosDto, vehicleStatusDtos);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            statusService.logVehicleInfo(getTboxById(otaDto.getTboxId()), null, null,
                    new VehiclePosDtoAssembler().fromDto(vehiclePosDto),
                    new VehicleStatusDtoAssembler().fromDtoList(vehicleStatusDtos),
                    new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos),
                    otaDto.getEventCreateTime(), otaDto.getEventId());
            eventService.end(event);
        }
    }

    @Override
    public VehicleInfoDto getVehicleStatus(String vin, Long eventId) throws ServLayerException {
        AssertRequired("vin", vin);
        return new VehicleInfoDtoAssembler().toDto(statusService.getVehicleInfo(vin, eventId));
    }

    @Override
    public void logVehicleAlert(OtaDto otaDto, List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,vehicleAlertDtos", otaDto, vehicleAlertDtos);
        VehiclePosDtoAssembler posDtoAssembler = new VehiclePosDtoAssembler();
        VehicleAlertDtoAssembler alertDtoAssembler = new VehicleAlertDtoAssembler();
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            for(VehicleAlertDto vehicleAlertDto : vehicleAlertDtos) {
                statusService.logVehicleAlert(getTboxById(otaDto.getTboxId()), otaDto.getEventCreateTime(),
                        posDtoAssembler.fromDto(vehicleAlertDto.getVehiclePosDto()),
                        alertDtoAssembler.fromDto(vehicleAlertDto));
            }
            eventService.end(event);
        }
    }

}