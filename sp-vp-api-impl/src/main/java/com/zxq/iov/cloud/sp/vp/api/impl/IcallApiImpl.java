package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IIcallApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall.IcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IIcallService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 iCall API实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:30
 * modify date 2015-8-11 10:11
 * @version 0.10, 2015-8-11
 */
@Service
public class IcallApiImpl extends BaseApi implements IIcallApi {

    @Autowired
    private IIcallService icallService;
    @Autowired
    private IEventService eventService;

    @Override
    public IcallRecordDto startIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, icallType, tboxBatteryStatus, vehicleBatteryStatus);
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        IcallRecordDto icallRecordDto = null;
        if(!event.isRetry()) {
            icallRecordDto = new IcallRecordDtoAssembler().toDto(icallService.start(otaDto.getTboxId(),
                    new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), icallType,
                    tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()));
            event.setResult(icallRecordDto);
            eventService.end(event);
            otaDto.setMid(2);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                icallRecordDto = JSON.parse(event.getResult().toString(), IcallRecordDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        icallRecordDto.setEventId(event.getId());
        icallRecordDto.setAid(otaDto.getAid());
        icallRecordDto.setMid(otaDto.getMid());
        return icallRecordDto;
    }

    @Override
    public void requestIcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 3);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public Long updateIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, icallType, tboxBatteryStatus, vehicleBatteryStatus);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        Long callId;
        if(!event.isRetry()) {
            callId = icallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                     icallType, tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()).getId();
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
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 5);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            icallService.hangUp(vin);
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 7);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            icallService.callBack(vin, callNumber);
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, new BcallRecordDto(callNumber));
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted,
                                 Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            icallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
            eventService.end(event);
        }
    }

    @Override
    public void requestCloseIcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 6);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            icallService.close(vin);
        }
        Long stepId = eventService.findInstance(vin, Constants.AID_ICALL + "6").getId();
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, null, stepId.toString());
    }

    @Override
    public void closeIcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            icallService.close(otaDto.getTboxId());
            eventService.end(event);
        }
    }
}