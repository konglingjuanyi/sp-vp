package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IBcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 bCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 15:10
 * modify date 2015-7-27 17:21
 * @version 0.9, 2015-7-27
 */
@Service
@Qualifier("bcallServiceProxy")
public class BcallServiceProxy extends BaseProxy implements IBcallService {

    @Autowired
    @Qualifier("bcallService")
    private IBcallService bcallService;
    @Autowired
    private IEvent event;

    @Override
    public BcallRecordDto startBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                                     List<VehicleAlertDto> vehicleAlertDtos) throws Exception {
        event.start(otaDto);
        BcallRecordDto bcallRecordDto = bcallService.startBcall(otaDto, vehiclePosDtos, bcallType,
                tboxBatteryStatus, vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto, bcallRecordDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        bcallRecordDto.setMid(otaDto.getMid());
        return bcallRecordDto;
    }

    @Override
    public void requestBcallStatus(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 3);
        event.start(otaDto);
        bcallService.requestBcallStatus(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public Long updateBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) throws Exception {
        event.start(otaDto);
        Long callId = bcallService.updateBcall(otaDto, vehiclePosDtos, bcallType, tboxBatteryStatus,
                vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto, callId);
        return callId;
    }

    @Override
    public void requestHangUp(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 5);
        event.start(otaDto);
        bcallService.requestHangUp(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 7);
        event.start(otaDto);
        bcallService.requestCallBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted,
                                 Integer rejectReason) throws Exception {
        event.start(otaDto);
        bcallService.responseCallBack(otaDto, callbackAccepted, rejectReason);
        event.end(otaDto);
    }

    @Override
    public void requestCloseBcall(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_BCALL, 6);
        event.start(otaDto);
        bcallService.requestCloseBcall(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void closeBcall(OtaDto otaDto) throws Exception {
        event.start(otaDto);
        bcallService.closeBcall(otaDto);
        event.end(otaDto);
    }

}
