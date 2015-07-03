package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSONObject;
import com.zxq.iov.cloud.sp.vp.api.IIcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
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
 * 安防 iCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 16:11
 * modify date 2015-6-26 10:17
 * @version 0.3, 2015-6-26
 */
@Service
@Qualifier("icallServiceProxy")
public class IcallServiceProxy implements IIcallService {

    @Autowired
    @Qualifier("icallService")
    private IIcallService icallService;
    @Autowired
    private IEvent event;

    @Override
    public IcallRecordDto startIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                                     List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        IcallRecordDto icallRecordDto = icallService.startIcall(otaDto, vehiclePosDtos, icallType,
                tboxBatteryStatus, vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto, icallRecordDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        return icallRecordDto;
    }

    @Override
    public void requestIcallStatus(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ICALL, 3);
        Long eventId = event.start(otaDto);
        icallService.requestIcallStatus(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "icallStatus");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public Long updateIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        Long callId = icallService.updateIcall(otaDto, vehiclePosDtos, icallType, tboxBatteryStatus,
                vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto, callId);
        return callId;
    }

    @Override
    public void requestHangUp(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ICALL, 5);
        Long eventId = event.start(otaDto);
        icallService.requestHangUp(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "hangUp");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ICALL, 7);
        Long eventId = event.start(otaDto);
        icallService.requestCallBack(vin, callNumber);
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
        icallService.responseCallBack(otaDto, callbackAccepted, rejectReason);
        event.end(otaDto);
    }

    @Override
    public void requestCloseIcall(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ICALL, 6);
        Long eventId = event.start(otaDto);
        icallService.requestCloseIcall(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "closeIcall");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void closeIcall(OtaDto otaDto) {
        event.start(otaDto);
        icallService.closeIcall(otaDto);
        event.end(otaDto);
    }
}
