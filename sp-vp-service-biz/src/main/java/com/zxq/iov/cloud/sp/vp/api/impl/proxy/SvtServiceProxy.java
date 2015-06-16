package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.ISvtService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ImmoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.KeyAuthDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 被盗追踪服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 10:45
 * modify date
 * @version 0.1, 2015-6-16
 */
@Service
@Qualifier("svtServiceProxy")
public class SvtServiceProxy implements ISvtService {

    @Autowired
    @Qualifier("svtService")
    private ISvtService svtService;
    @Autowired
    private IEvent event;

    @Override
    public void alarm(List<StolenAlarmDto> stolenAlarmDtos, List<VehicleInfoDto> vehicleInfoDtos) {
        event.start(stolenAlarmDtos.get(0));
        svtService.alarm(stolenAlarmDtos, vehicleInfoDtos);
        event.end(stolenAlarmDtos.get(0));
    }

    @Override
    public void updateTrack(VehicleInfoDto vehicleInfoDto) {
        event.start(vehicleInfoDto);
        svtService.updateTrack(vehicleInfoDto);
        event.end(vehicleInfoDto);
    }

    @Override
    public void requestTrackSetting(Long tboxId, Integer trackInterval, Integer tracks) {
        OtaDto otaDto = new OtaDto(tboxId, "114", 3);
        event.start(otaDto);
        svtService.requestTrackSetting(tboxId, trackInterval, tracks);
        event.end(otaDto);
    }

    @Override
    public void requestSingleTrack(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, "114", 4);
        event.start(otaDto);
        svtService.requestSingleTrack(tboxId);
        event.end(otaDto);
    }

    @Override
    public void requestCloseAlarm(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, "114", 5);
        event.start(otaDto);
        svtService.requestCloseAlarm(tboxId);
        event.end(otaDto);
    }

    @Override
    public void responseCloseAlarm(Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos,
                                   List<VehicleInfoDto> vehicleInfoDtos) {
        event.start(stolenAlarmDtos.get(0));
        svtService.responseCloseAlarm(allAlarmClosed, stolenAlarmDtos, vehicleInfoDtos);
        if(allAlarmClosed) {
            event.end(stolenAlarmDtos.get(0));
        }
        else {
            event.error(stolenAlarmDtos.get(0), 1);
        }
    }

    @Override
    public void requestAuthKey(Long tboxId, Long keyId) {
        OtaDto otaDto = new OtaDto(tboxId, "114", 7);
        event.start(otaDto);
        svtService.requestAuthKey(tboxId, keyId);
        event.end(otaDto);
    }

    @Override
    public void responseAuthKey(KeyAuthDto keyAuthDto) {
        event.start(keyAuthDto);
        svtService.responseAuthKey(keyAuthDto);
        event.end(keyAuthDto);
    }

    @Override
    public void requestImmobilise(Long tboxId, Integer requestStatus) {
        OtaDto otaDto = new OtaDto(tboxId, "114", 9);
        event.start(otaDto);
        svtService.requestImmobilise(tboxId, requestStatus);
        event.end(otaDto);
    }

    @Override
    public void responseImmobilise(ImmoDto immoDto) {
        event.start(immoDto);
        svtService.responseImmobilise(immoDto);
        event.end(immoDto);
    }

    @Override
    public void requestUpdateProtectStrategy(Long tboxId) {

    }

    @Override
    public void responseUpdateProtectStrategy() {

    }

    @Override
    public void requestAlarm(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, "114", 13);
        event.start(otaDto);
        svtService.requestAlarm(tboxId);
        event.end(otaDto);
    }
}
