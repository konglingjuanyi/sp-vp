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
 * modify date 2015-8-6 14:53
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
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        BcallRecordDto bcallRecordDto = new BcallRecordDtoAssembler().toDto(bcallService.start(otaDto.getTboxId(),
                new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos), bcallType, tboxBatteryStatus,
                vehicleBatteryStatus, new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos),
                otaDto.getEventCreateTime()));
        eventService.end(getVin(otaDto), getCode(otaDto), bcallRecordDto);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto));
        eventService.end(getVin(otaDto), getCode(otaDto));
        bcallRecordDto.setEventId(eventId);
        bcallRecordDto.setAid(otaDto.getAid());
        bcallRecordDto.setMid(otaDto.getMid());
        return bcallRecordDto;
    }

    @Override
    public void requestBcallStatus(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 3);
        eventService.start(vin, Constants.AID_BCALL + "3");
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_BCALL + "3");
    }

    @Override
    public Long updateBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) throws ServLayerException {
        AssertRequired("otaDto,vehiclePosDtos,bcallType,tboxBatteryStatus,vehicleBatteryStatus", otaDto,
                vehiclePosDtos, bcallType, tboxBatteryStatus, vehicleBatteryStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto));
        Call call = bcallService.update(otaDto.getTboxId(), new VehiclePosDtoAssembler().fromDtoList(vehiclePosDtos),
                bcallType, tboxBatteryStatus, vehicleBatteryStatus,
                new VehicleAlertDtoAssembler().fromDtoList(vehicleAlertDtos), otaDto.getEventCreateTime());
        eventService.end(getVin(otaDto), getCode(otaDto), call.getId());
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 5);
        eventService.start(vin, Constants.AID_BCALL + "5");
        bcallService.hangUp(vin);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_BCALL + "5");
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 7);
        eventService.start(vin, Constants.AID_BCALL + "7");
        bcallService.callBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        eventService.end(vin, Constants.AID_BCALL + "7");
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException {
        AssertRequired("otaDto,callbackAcdepted", otaDto, callbackAccepted);
        eventService.start(getVin(otaDto), getCode(otaDto));
        bcallService.responseCallBack(otaDto.getTboxId(), callbackAccepted, rejectReason);
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestCloseBcall(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 6);
        eventService.start(vin, Constants.AID_BCALL + "6");
        bcallService.close(vin);
        Long stepId = eventService.findInstance(vin, Constants.AID_BCALL + "6").getId();
        sendQueue(otaDto, null, stepId.toString());
    }

    @Override
    public void closeBcall(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        eventService.start(getVin(otaDto), getCode(otaDto));
        bcallService.close(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto));
    }
}
