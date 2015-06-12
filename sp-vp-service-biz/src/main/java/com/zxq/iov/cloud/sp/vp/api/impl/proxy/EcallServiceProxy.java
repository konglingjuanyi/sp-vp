package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IEcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 eCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 13:45
 * modify date
 * @version 0.1, 2015-6-12
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
    public EcallRecordDto startEcall(EcallDto ecallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        Object result = event.start(ecallDto, EcallRecordDto.class);
        if(null == result) {
            result = ecallService.startEcall(ecallDto, vehicleInfoDtos);
            event.end(ecallDto, result);
            OtaDto otaDto = new OtaDto(ecallDto.getTboxId(), "902", 2);
            event.start(otaDto);
            event.end(otaDto); // 这里的end就看事务引擎是否提供验证云端是否发送成功的接口了，提供的话则在该接口中end
        }
        return (EcallRecordDto)result;
    }

    @Override
    public void requestEcallStatus(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, "902", 3);
        event.start(otaDto);
        ecallService.requestEcallStatus(tboxId);
        event.end(otaDto);
    }

    @Override
    public void updateEcall(EcallDto ecallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        event.start(ecallDto);
        ecallService.updateEcall(ecallDto, vehicleInfoDtos);
        event.end(ecallDto);
    }

    @Override
    public void requestHangUp(Long tboxId, Long callRecordId) {
        OtaDto otaDto = new OtaDto(tboxId, "902", 5);
        event.start(otaDto);
        ecallService.requestHangUp(tboxId, callRecordId);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(Long tboxId, Long callId, String callNumber) {
        OtaDto otaDto = new OtaDto(tboxId, "902", 7);
        event.start(otaDto);
        ecallService.requestCallBack(tboxId, callId, callNumber);
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(EcallRecordDto ecallRecordDto) {
        event.start(ecallRecordDto);
        ecallService.responseCallBack(ecallRecordDto);
        event.end(ecallRecordDto);
    }

    @Override
    public void requestCloseEcall(Long tboxId, Long callId) {
        OtaDto otaDto = new OtaDto(tboxId, "902", 6);
        event.start(otaDto);
        ecallService.requestCloseEcall(tboxId, callId);
        event.end(otaDto);
    }

    @Override
    public void closeEcall(EcallDto ecallDto) {
        event.start(ecallDto);
        ecallService.closeEcall(ecallDto);
        event.end(ecallDto);
    }
}
