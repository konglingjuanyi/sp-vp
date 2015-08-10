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
 * modify date 2015-8-7 11:11
 * @version 0.9, 2015-8-7
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
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        EcallRecordDto ecallRecordDto = new EcallRecordDtoAssembler().toDto(ecallService.start(otaDto.getTboxId(),
                new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), ecallType, crashSeverity,
                tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()));
        eventService.end(getVin(otaDto), getCode(otaDto), ecallRecordDto);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto));
        eventService.end(getVin(otaDto), getCode(otaDto));
        ecallRecordDto.setEventId(eventId);
        ecallRecordDto.setAid(otaDto.getAid());
        ecallRecordDto.setMid(otaDto.getMid());
        return ecallRecordDto;
    }

    @Override
    public void requestEcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 3);
        eventService.start(vin, Constants.AID_ECALL + "3");
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ECALL + "3");
    }

    @Override
    public Long updateEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                            Integer crashSeverity, Integer tboxBatteryStatus,
                            Integer vehicleBatteryStatus) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,crashSeverity,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        Call call = ecallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime());
        eventService.end(getVin(otaDto), getCode(otaDto), call.getId());
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 5);
        eventService.start(vin, Constants.AID_ECALL + "5");
        ecallService.hangUp(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ECALL + "5");
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 7);
        eventService.start(vin, Constants.AID_ECALL + "7");
        ecallService.callBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        eventService.end(vin, Constants.AID_ECALL + "7");
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        eventService.start(getVin(otaDto), getCode(otaDto));
        ecallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestCloseEcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 6);
        eventService.start(vin, Constants.AID_ECALL + "6");
        ecallService.close(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ECALL + "6");
    }

    @Override
    public void closeEcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        eventService.start(getVin(otaDto), getCode(otaDto));
        ecallService.close(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto));
    }
}