package com.zxq.iov.cloud.sp.vp.api.impl;

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
 * modify date 2015-8-7 11:32
 * @version 0.9, 2015-8-7
 */
@Service
public class IcallApiImpl extends BaseApi implements IIcallApi {

    @Autowired
    private IIcallService icallService;
    @Autowired
    private IEventService eventService;

    @Override
    public IcallRecordDto startIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws Exception {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, icallType, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        IcallRecordDto icallRecordDto = new IcallRecordDtoAssembler().toDto(icallService.start(otaDto.getTboxId(),
                new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), icallType,
                tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime()));
        eventService.end(getVin(otaDto), getCode(otaDto), icallRecordDto);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto));
        eventService.end(getVin(otaDto), getCode(otaDto));
        icallRecordDto.setEventId(eventId);
        icallRecordDto.setAid(otaDto.getAid());
        icallRecordDto.setMid(otaDto.getMid());
        return icallRecordDto;
    }

    @Override
    public void requestIcallStatus(String vin) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 3);
        eventService.start(vin, Constants.AID_ICALL + "3");
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ICALL + "3");
    }

    @Override
    public Long updateIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws Exception {
        AssertRequired("otaDto,vehiclePosDtos,ecallType,tboxBatteryStatus,vehicleBatteryStatus",
                otaDto, vehiclePosDtos, icallType, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        Call call = icallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                icallType, tboxBatteryStatus, vehicleBatteryStatus, otaDto.getEventCreateTime());
        eventService.end(getVin(otaDto), getCode(otaDto), call.getId());
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 5);
        eventService.start(vin, Constants.AID_ICALL + "5");
        icallService.hangUp(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ICALL + "5");
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 7);
        eventService.start(vin, Constants.AID_ICALL + "7");
        icallService.callBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        eventService.end(vin, Constants.AID_ICALL + "7");
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted,
                                 Integer rejectReason) throws Exception {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        eventService.start(getVin(otaDto), getCode(otaDto));
        icallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestCloseIcall(String vin) throws Exception {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 6);
        eventService.start(vin, Constants.AID_ICALL + "6");
        icallService.close(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_ICALL + "6");
    }

    @Override
    public void closeIcall(OtaDto otaDto) throws Exception {
        AssertRequired("otaDto", otaDto);
        eventService.start(getVin(otaDto), getCode(otaDto));
        icallService.close(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto));
    }
}