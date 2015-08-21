package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IEcallApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall.EcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEcallService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 eCall API实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:26
 * modify date 2015-8-11 10:07
 * @version 0.10, 2015-8-11
 */
@Service
public class EcallApiImpl extends BaseApi implements IEcallApi {

    @Autowired
    private IEcallService ecallService;
    @Autowired
    private IEventService eventService;

    @Override
    public EcallRecordDto startEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                                     Integer crashSeverity, Integer tboxBatteryStatus,
                                     Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,crashSeverity,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus);
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        EcallRecordDto ecallRecordDto = null;
        if(!event.isRetry()) {
            ecallRecordDto = new EcallRecordDtoAssembler().toDto(ecallService.start(otaDto.getTboxId(),
                    new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), ecallType, crashSeverity,
                    tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()));
            event.setResult(ecallRecordDto);
            eventService.end(event);
            otaDto.setMid(2);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                ecallRecordDto = JSON.parse(event.getResult().toString(), EcallRecordDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ecallRecordDto.setEventId(event.getId());
        ecallRecordDto.setAid(otaDto.getAid());
        ecallRecordDto.setMid(otaDto.getMid());
        return ecallRecordDto;
    }

    @Override
    public void requestEcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 3);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public Long updateEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                            Integer crashSeverity, Integer tboxBatteryStatus,
                            Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,crashSeverity,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        Long callId;
        if(!event.isRetry()) {
            callId = ecallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                    ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()).getId();
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
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 5);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            ecallService.hangUp(vin);
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 7);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            ecallService.callBack(vin, callNumber);
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
            ecallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
            eventService.end(event);
        }
    }

    @Override
    public void requestCloseEcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 6);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            ecallService.close(vin);
        }
        Long stepId = eventService.findInstance(vin, Constants.AID_ECALL + "6").getId();
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, null, stepId.toString());
    }

    @Override
    public void closeEcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            ecallService.close(otaDto.getTboxId());
            eventService.end(event);
        }
    }
}