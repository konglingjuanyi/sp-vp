package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IBcallApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall.BcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IBcallService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
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
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        BcallRecordDto bcallRecordDto = null;
        if(!event.isRetry()) {
            bcallRecordDto = new BcallRecordDtoAssembler().toDto(bcallService.start(otaDto.getTboxId(),
                    new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), bcallType, tboxBatteryStatus,
                    vehicleBatteryStatus, new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos),
                    otaDto.getEventCreateTime()));
            event.setResult(bcallRecordDto);
            eventService.end(event);
            otaDto.setMid(2);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                bcallRecordDto = JSON.parse(event.getResult().toString(), BcallRecordDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        bcallRecordDto.setEventId(event.getId());
        bcallRecordDto.setAid(otaDto.getAid());
        bcallRecordDto.setMid(otaDto.getMid());
        return bcallRecordDto;
    }

    @Override
    public void requestBcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 3);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public Long updateBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,bcallType,tboxBatteryStatus,vehicleBatteryStatus", otaDto,
                vehiclePosDtos, bcallType, tboxBatteryStatus, vehicleBatteryStatus);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        Long callId = null;
        if(!event.isRetry()) {
            callId = bcallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                    bcallType, tboxBatteryStatus, vehicleBatteryStatus,
                    new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos), otaDto.getEventCreateTime()).getId();
            event.setResult(callId);
            eventService.end(event);
        }
        else {
            callId = Long.parseLong(event.getResult().toString());
        }
        return callId;
    }

    @Override
    public void requestHangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 5);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            bcallService.hangUp(vin);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, null, event.getStep().getId().toString());
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 7);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            bcallService.callBack(vin, callNumber);
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, new BcallRecordDto(callNumber));
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            bcallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
            eventService.end(event);
        }
    }

    @Override
    public void requestCloseBcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 6);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            bcallService.close(vin);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, null, event.getStep().getId().toString());
    }

    @Override
    public void closeBcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            bcallService.close(otaDto.getTboxId());
            eventService.end(event);
        }
    }
}
