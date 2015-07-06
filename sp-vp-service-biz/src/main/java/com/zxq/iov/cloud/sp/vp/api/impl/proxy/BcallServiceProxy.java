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
 * modify date 2015-7-6 16:02
 * @version 0.6, 2015-7-6
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
                                     List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        BcallRecordDto bcallRecordDto = bcallService.startBcall(otaDto, vehiclePosDtos, bcallType,
                tboxBatteryStatus, vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto, bcallRecordDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        return bcallRecordDto;
    }

    @Override
    public void requestBcallStatus(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_BCALL, 3);
        event.start(otaDto);
        bcallService.requestBcallStatus(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public Long updateBcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer bcallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        Long callId = bcallService.updateBcall(otaDto, vehiclePosDtos, bcallType, tboxBatteryStatus,
                vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto, callId);
        return callId;
    }

    @Override
    public void requestHangUp(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_BCALL, 5);
        event.start(otaDto);
        bcallService.requestHangUp(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_BCALL, 7);
        event.start(otaDto);
        bcallService.requestCallBack(vin, callNumber);
        sendQueue(otaDto, new BcallRecordDto(callNumber));
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) {
        event.start(otaDto);
        bcallService.responseCallBack(otaDto, callbackAccepted, rejectReason);
        event.end(otaDto);
    }

    @Override
    public void requestCloseBcall(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_BCALL, 6);
        event.start(otaDto);
        bcallService.requestCloseBcall(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void closeBcall(OtaDto otaDto) {
        event.start(otaDto);
        bcallService.closeBcall(otaDto);
        event.end(otaDto);
    }

}
