package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IEcallApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall.EcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.service.IEcallService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
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
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        EcallRecordDto ecallRecordDto = new EcallRecordDtoAssembler().toDto(ecallService.start(otaDto.getTboxId(),
                new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), ecallType, crashSeverity,
                tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()));
        eventService.end(getVin(otaDto), getCode(otaDto), ecallRecordDto, eventId);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        ecallRecordDto.setEventId(eventId);
        ecallRecordDto.setAid(otaDto.getAid());
        ecallRecordDto.setMid(otaDto.getMid());
        return ecallRecordDto;
    }

    @Override
    public void requestEcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 3);
        Long eventId = eventService.start(vin, Constants.AID_ECALL + "3", null);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ECALL + "3", eventId);
    }

    @Override
    public Long updateEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                            Integer crashSeverity, Integer tboxBatteryStatus,
                            Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,crashSeverity,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        Call call = ecallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime());
        eventService.end(getVin(otaDto), getCode(otaDto), call.getId(), eventId);
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 5);
        Long eventId = eventService.start(vin, Constants.AID_ECALL + "5", null);
        ecallService.hangUp(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ECALL + "5", eventId);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 7);
        Long eventId = eventService.start(vin, Constants.AID_ECALL + "7", null);
        ecallService.callBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        eventService.end(vin, Constants.AID_ECALL + "7", eventId);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        ecallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public void requestCloseEcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 6);
        Long eventId = eventService.start(vin, Constants.AID_ECALL + "6", null);
        ecallService.close(vin);
        Long stepId = eventService.findInstance(vin, Constants.AID_ECALL + "6").getId();
        sendQueue(otaDto, null, stepId.toString());
    }

    @Override
    public void closeEcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        ecallService.close(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }
}