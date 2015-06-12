package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IBcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 bCall服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 15:10
 * modify date 2015-6-12 10:05
 * @version 0.2, 2015-6-12
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
    public BcallRecordDto startBcall(BcallDto bcallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        Object result = event.start(bcallDto, BcallRecordDto.class);
        if(null == result) {
            result = bcallService.startBcall(bcallDto, vehicleInfoDtos);
            event.end(bcallDto, result);
            OtaDto otaDto = new OtaDto(bcallDto.getTboxId(), "903", 2);
            event.start(otaDto);
            event.end(otaDto); // 这里的end就看事务引擎是否提供验证云端是否发送成功的接口了，提供的话则在该接口中end
        }
        return (BcallRecordDto)result;
    }

    @Override
    public void requestBcallStatus(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, "903", 3);
        event.start(otaDto);
        bcallService.requestBcallStatus(tboxId);
        event.end(otaDto);
    }

    @Override
    public void updateBcall(BcallDto bcallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        event.start(bcallDto);
        bcallService.updateBcall(bcallDto, vehicleInfoDtos);
        event.end(bcallDto);
    }

    @Override
    public void requestHangUp(Long tboxId, Long callRecordId) {
        OtaDto otaDto = new OtaDto(tboxId, "903", 5);
        event.start(otaDto);
        bcallService.requestHangUp(tboxId, callRecordId);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(Long tboxId, Long callId, String callNumber) {
        OtaDto otaDto = new OtaDto(tboxId, "903", 7);
        event.start(otaDto);
        bcallService.requestCallBack(tboxId, callId, callNumber);
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(BcallRecordDto bcallRecordDto) {
        event.start(bcallRecordDto);
        bcallService.responseCallBack(bcallRecordDto);
        event.end(bcallRecordDto);
    }

    @Override
    public void requestCloseBcall(Long tboxId, Long callId) {
        OtaDto otaDto = new OtaDto(tboxId, "903", 6);
        event.start(otaDto);
        bcallService.requestCloseBcall(tboxId, callId);
        event.end(otaDto);
    }

    @Override
    public void closeBcall(BcallDto bcallDto) {
        event.start(bcallDto);
        bcallService.closeBcall(bcallDto);
        event.end(bcallDto);
    }
}
