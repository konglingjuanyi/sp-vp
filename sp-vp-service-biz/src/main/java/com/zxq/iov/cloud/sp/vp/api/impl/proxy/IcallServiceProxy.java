package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IIcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 iCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 16:11
 * modify date 2015-7-24 10:54
 * @version 0.6, 2015-7-24
 */
@Service
@Qualifier("icallServiceProxy")
public class IcallServiceProxy extends BaseProxy implements IIcallService {

    @Autowired
    @Qualifier("icallService")
    private IIcallService icallService;
    @Autowired
    private IEvent event;

    @Override
    public IcallRecordDto startIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws Exception {
        event.start(otaDto);
        IcallRecordDto icallRecordDto = icallService.startIcall(otaDto, vehiclePosDtos, icallType,
                tboxBatteryStatus, vehicleBatteryStatus);
        event.end(otaDto, icallRecordDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        return icallRecordDto;
    }

    @Override
    public void requestIcallStatus(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 3);
        event.start(otaDto);
        icallService.requestIcallStatus(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public Long updateIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus) throws Exception {
        event.start(otaDto);
        Long callId = icallService.updateIcall(otaDto, vehiclePosDtos, icallType, tboxBatteryStatus,
                vehicleBatteryStatus);
        event.end(otaDto, callId);
        return callId;
    }

    @Override
    public void requestHangUp(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 5);
        event.start(otaDto);
        icallService.requestHangUp(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 7);
        event.start(otaDto);
        icallService.requestCallBack(vin, callNumber);
        sendQueue(otaDto, new IcallRecordDto(callNumber));
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) throws Exception {
        event.start(otaDto);
        icallService.responseCallBack(otaDto, callbackAccepted, rejectReason);
        event.end(otaDto);
    }

    @Override
    public void requestCloseIcall(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ICALL, 6);
        event.start(otaDto);
        icallService.requestCloseIcall(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void closeIcall(OtaDto otaDto) throws Exception {
        event.start(otaDto);
        icallService.closeIcall(otaDto);
        event.end(otaDto);
    }
}