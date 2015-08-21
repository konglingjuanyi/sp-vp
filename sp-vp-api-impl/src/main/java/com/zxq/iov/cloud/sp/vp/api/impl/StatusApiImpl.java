package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IStatusApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.*;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleInfoDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 16:37
 * modify date 2015-8-11 18:04
 * @version 0.13, 2015-8-11
 */
@Service
public class StatusApiImpl extends BaseApi implements IStatusApi {

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
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_STATUS, 1);
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
            statusService.logVehicleInfo(otaDto.getTboxId(), null, null,
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
                statusService.logVehicleAlert(otaDto.getTboxId(), otaDto.getEventCreateTime(),
                        posDtoAssembler.fromDto(vehicleAlertDto.getVehiclePosDto()),
                        alertDtoAssembler.fromDto(vehicleAlertDto));
            }
            eventService.end(event);
        }
    }

}