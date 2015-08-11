package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IStatusApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.*;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleInfoDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务实现类
 *
 * @author 叶荣杰
 * create date 2015-5-13 16:37
 * modify date 2015-8-7 14:33
 * @version 0.12, 2015-8-7
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
        Long eventId = eventService.start(vin, Constants.AID_STATUS + "1", null);
        otaDto.setEventId(eventId);
        sendQueue(otaDto, new VehicleStatusReqDto(statusType));
        eventService.end(vin, Constants.AID_STATUS + "1", eventId);
        return eventId;
    }

    @Override
    public void responseVehicleStatus(OtaDto otaDto, Date statusTime, VehiclePosDto vehiclePosDto,
                                      List<VehicleStatusDto> vehicleStatusDtos,
                                      List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,statusTime,vehiclePosDto,vehicleStatusDtos", otaDto, statusTime,
                vehiclePosDto, vehicleStatusDtos);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        statusService.logVehicleInfo(otaDto.getTboxId(), null, null,
                new VehiclePosDtoAssembler().fromDto(vehiclePosDto),
                new VehicleStatusDtoAssembler().fromDtoList(vehicleStatusDtos),
                new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos),
                otaDto.getEventCreateTime(), otaDto.getEventId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
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
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        for(VehicleAlertDto vehicleAlertDto : vehicleAlertDtos) {
            statusService.logVehicleAlert(otaDto.getTboxId(), otaDto.getEventCreateTime(),
                    posDtoAssembler.fromDto(vehicleAlertDto.getVehiclePosDto()),
                    alertDtoAssembler.fromDto(vehicleAlertDto));
        }
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

}