package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IBcallApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall.BcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.service.IBcallService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 bCall API实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 10:42
 * modify date 2015-8-11 10:01
 * @version 0.9, 2015-8-6
 */
@Service
@Transactional
public class BcallApiImpl extends BaseApi implements IBcallApi {

    @Autowired
    private IBcallService bcallService;
    @Autowired
    private IEventService eventService;

    @Override
    public BcallRecordDto startBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                                     List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,bcallType,tboxBatteryStatus,vehicleBatteryStatus", otaDto,
                vehiclePosDtos, bcallType, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        BcallRecordDto bcallRecordDto = new BcallRecordDtoAssembler().toDto(bcallService.start(otaDto.getTboxId(),
                new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), bcallType, tboxBatteryStatus,
                vehicleBatteryStatus, new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos),
                otaDto.getEventCreateTime()));
        eventService.end(getVin(otaDto), getCode(otaDto), bcallRecordDto, eventId);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        bcallRecordDto.setEventId(eventId);
        bcallRecordDto.setAid(otaDto.getAid());
        bcallRecordDto.setMid(otaDto.getMid());
        return bcallRecordDto;
    }

    @Override
    public void requestBcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 3);
        Long eventId = eventService.start(vin, Constants.AID_BCALL + "3", null);
        otaDto.setEventId(eventId);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_BCALL + "3", eventId);
    }

    @Override
    public Long updateBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,bcallType,tboxBatteryStatus,vehicleBatteryStatus", otaDto,
                vehiclePosDtos, bcallType, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        Call call = bcallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                bcallType, tboxBatteryStatus, vehicleBatteryStatus,
                new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos), otaDto.getEventCreateTime());
        eventService.end(getVin(otaDto), getCode(otaDto), call.getId(), eventId);
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 5);
        Long eventId = eventService.start(vin, Constants.AID_BCALL + "5", null);
        bcallService.hangUp(vin);
        otaDto.setEventId(eventId);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_BCALL + "5", eventId);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 7);
        Long eventId = eventService.start(vin, Constants.AID_BCALL + "7", null);
        bcallService.callBack(vin, callNumber);
        otaDto.setEventId(eventId);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        eventService.end(vin, Constants.AID_BCALL + "7", eventId);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        bcallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public void requestCloseBcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 6);
        Long eventId = eventService.start(vin, Constants.AID_BCALL + "6", null);
        bcallService.close(vin);
        Long stepId = eventService.findInstance(vin, Constants.AID_BCALL + "6").getId();
        otaDto.setEventId(eventId);
        sendQueue(otaDto, null, stepId.toString());
    }

    @Override
    public void closeBcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), null);
        bcallService.close(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }
}
