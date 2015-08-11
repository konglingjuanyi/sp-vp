package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IIcallApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall.IcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IIcallService;
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
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        IcallRecordDto icallRecordDto = new IcallRecordDtoAssembler().toDto(icallService.start(otaDto.getTboxId(),
                new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), icallType,
                tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()));
        eventService.end(getVin(otaDto), getCode(otaDto), icallRecordDto, eventId);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        icallRecordDto.setEventId(eventId);
        icallRecordDto.setAid(otaDto.getAid());
        icallRecordDto.setMid(otaDto.getMid());
        return icallRecordDto;
    }

    @Override
    public void requestIcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 3);
        Long eventId = eventService.start(vin, Constants.AID_ICALL + "3", null);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ICALL + "3", eventId);
    }

    @Override
    public Long updateIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, icallType, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        Call call = icallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                icallType, tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime());
        eventService.end(getVin(otaDto), getCode(otaDto), call.getId(), eventId);
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 5);
        Long eventId = eventService.start(vin, Constants.AID_ICALL + "5", null);
        icallService.hangUp(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ICALL + "5", eventId);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 7);
        Long eventId = eventService.start(vin, Constants.AID_ICALL + "7", null);
        icallService.callBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        eventService.end(vin, Constants.AID_ICALL + "7", eventId);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted,
                                 Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        icallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public void requestCloseIcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 6);
        Long eventId = eventService.start(vin, Constants.AID_ICALL + "6", null);
        icallService.close(vin);
        Long stepId = eventService.findInstance(vin, Constants.AID_ICALL + "6").getId();
        sendQueue(otaDto, null, stepId.toString());
    }

    @Override
    public void closeIcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        icallService.close(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }
}