package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSONObject;
import com.zxq.iov.cloud.sp.vp.api.IBcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.QueueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 bCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 15:10
 * modify date 2015-6-26 10:16
 * @version 0.5, 2015-6-26
 */
@Service
@Qualifier("bcallServiceProxy")
public class BcallServiceProxy implements IBcallService {

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
        Long eventId = event.start(otaDto);
        bcallService.requestBcallStatus(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "bcallStatus");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
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
        Long eventId = event.start(otaDto);
        bcallService.requestHangUp(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "hangUp");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_BCALL, 7);
        Long eventId = event.start(otaDto);
        bcallService.requestCallBack(vin, callNumber);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "callBack");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
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
        Long eventId = event.start(otaDto);
        bcallService.requestCloseBcall(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "closeBcall");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void closeBcall(OtaDto otaDto) {
        event.start(otaDto);
        bcallService.closeBcall(otaDto);
        event.end(otaDto);
    }

}
