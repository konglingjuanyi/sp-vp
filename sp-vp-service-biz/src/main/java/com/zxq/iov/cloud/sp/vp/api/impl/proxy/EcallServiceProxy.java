package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IEcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 eCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 13:45
 * modify date 2015-7-27 17:21
 * @version 0.7, 2015-7-27
 */
@Service
@Qualifier("ecallServiceProxy")
public class EcallServiceProxy extends BaseProxy implements IEcallService {

    @Autowired
    @Qualifier("ecallService")
    private IEcallService ecallService;
    @Autowired
    private IEvent event;

    @Override
    public EcallRecordDto startEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                                     Integer crashSeverity, Integer tboxBatteryStatus,
                                     Integer vehicleBatteryStatus) throws Exception {
        event.start(otaDto);
        EcallRecordDto ecallRecordDto = ecallService.startEcall(otaDto, vehiclePosDtos, ecallType, crashSeverity,
                tboxBatteryStatus, vehicleBatteryStatus);
        event.end(otaDto, ecallRecordDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        ecallRecordDto.setMid(otaDto.getMid());
        return ecallRecordDto;
    }

    @Override
    public void requestEcallStatus(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 3);
        event.start(otaDto);
        ecallService.requestEcallStatus(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public Long updateEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                            Integer crashSeverity, Integer tboxBatteryStatus,
                            Integer vehicleBatteryStatus) throws Exception {
        event.start(otaDto);
        Long callId = ecallService.updateEcall(otaDto, vehiclePosDtos, ecallType, crashSeverity, tboxBatteryStatus,
                vehicleBatteryStatus);
        event.end(otaDto, callId);
        return callId;
    }

    @Override
    public void requestHangUp(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 5);
        event.start(otaDto);
        ecallService.requestHangUp(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 7);
        event.start(otaDto);
        ecallService.requestCallBack(vin, callNumber);
        sendQueue(otaDto, new EcallRecordDto(callNumber));
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted,
                                 Integer rejectReason) throws Exception {
        event.start(otaDto);
        ecallService.responseCallBack(otaDto, callbackAccepted, rejectReason);
        event.end(otaDto);
    }

    @Override
    public void requestCloseEcall(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_ECALL, 6);
        event.start(otaDto);
        ecallService.requestCloseEcall(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void closeEcall(OtaDto otaDto) throws Exception {
        event.start(otaDto);
        ecallService.closeEcall(otaDto);
        event.end(otaDto);
    }
}
