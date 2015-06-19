package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IIcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
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
 * modify date
 * @version 0.1, 2015-6-12
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
    public IcallRecordDto startIcall(IcallDto icallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        event.start(icallDto);
        IcallRecordDto icallRecordDto = icallService.startIcall(icallDto, vehicleInfoDtos);
        event.end(icallDto, icallRecordDto);
        OtaDto otaDto = new OtaDto(icallDto.getTboxId(), Constants.AID_ICALL, 2);
        event.start(otaDto);
        event.end(otaDto); // 这里的end就看事务引擎是否提供验证云端是否发送成功的接口了，提供的话则在该接口中end
        return icallRecordDto;
    }

    @Override
    public void requestIcallStatus(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_ICALL, 3);
        event.start(otaDto);
        icallService.requestIcallStatus(tboxId);
        event.end(otaDto);
    }

    @Override
    public void updateIcall(IcallDto icallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        event.start(icallDto);
        icallService.updateIcall(icallDto, vehicleInfoDtos);
        event.end(icallDto);
    }

    @Override
    public void requestHangUp(Long tboxId, Long callRecordId) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_ICALL, 5);
        event.start(otaDto);
        icallService.requestHangUp(tboxId, callRecordId);
        event.end(otaDto);
    }

    @Override
    public void requestCallBack(Long tboxId, Long callId, String callNumber) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_ICALL, 7);
        event.start(otaDto);
        icallService.requestCallBack(tboxId, callId, callNumber);
        event.end(otaDto);
    }

    @Override
    public void responseCallBack(IcallRecordDto icallRecordDto) {
        event.start(icallRecordDto);
        icallService.responseCallBack(icallRecordDto);
        event.end(icallRecordDto);
    }

    @Override
    public void requestCloseIcall(Long tboxId, Long callId) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_ICALL, 6);
        event.start(otaDto);
        icallService.requestCloseIcall(tboxId, callId);
        event.end(otaDto);
    }

    @Override
    public void closeIcall(IcallDto icallDto) {
        event.start(icallDto);
        icallService.closeIcall(icallDto);
        event.end(icallDto);
    }
}
