package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSONObject;
import com.zxq.iov.cloud.sp.vp.api.IEcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
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
 * 安防 eCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 13:45
 * modify date 2015-6-26 10:17
 * @version 0.3, 2015-6-26
 */
@Service
@Qualifier("ecallServiceProxy")
public class EcallServiceProxy implements IEcallService {

    @Autowired
    @Qualifier("ecallService")
    private IEcallService ecallService;
    @Autowired
    private IEvent event;

    @Override
    public EcallRecordDto startEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                                     List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        EcallRecordDto ecallRecordDto = ecallService.startEcall(otaDto, vehiclePosDtos, ecallType,
                tboxBatteryStatus, vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        return ecallRecordDto;
    }

    @Override
    public void requestEcallStatus(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ECALL, 3);
        Long eventId = event.start(otaDto);
        ecallService.requestEcallStatus(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "ecallStatus");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public Long updateEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                            List<VehicleAlertDto> vehicleAlertDtos) {
        event.start(otaDto);
        Long callId = ecallService.updateEcall(otaDto, vehiclePosDtos, ecallType, tboxBatteryStatus,
                vehicleBatteryStatus, vehicleAlertDtos);
        event.end(otaDto);
        return callId;
    }

    @Override
    public void requestHangUp(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ECALL, 5);
        Long eventId = event.start(otaDto);
        ecallService.requestHangUp(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "hangUp");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ECALL, 7);
        Long eventId = event.start(otaDto);
        ecallService.requestCallBack(vin, callNumber);
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
        ecallService.responseCallBack(otaDto, callbackAccepted, rejectReason);
        event.end(otaDto);
    }

    @Override
    public void requestCloseEcall(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_ECALL, 6);
        Long eventId = event.start(otaDto);
        ecallService.requestCloseEcall(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "closeEcall");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void closeEcall(OtaDto otaDto) {
        event.start(otaDto);
        ecallService.closeEcall(otaDto);
        event.end(otaDto);
    }
}
