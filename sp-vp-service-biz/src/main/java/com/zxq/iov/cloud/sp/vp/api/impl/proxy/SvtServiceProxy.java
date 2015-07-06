package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.ISvtService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.*;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防 被盗追踪服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 10:45
 * modify date 2015-7-6 17:12
 * @version 0.3, 2015-7-6
 */
@Service
@Qualifier("svtServiceProxy")
public class SvtServiceProxy extends BaseProxy implements ISvtService {

    @Autowired
    @Qualifier("svtService")
    private ISvtService svtService;
    @Autowired
    private IEvent event;

    @Override
    public void alarm(OtaDto otaDto, List<StolenAlarmDto> stolenAlarmDtos) {
        event.start(otaDto);
        svtService.alarm(otaDto, stolenAlarmDtos);
        event.end(otaDto);
    }

    @Override
    public void updateTrack(OtaDto otaDto, List<TrackDto> trackDtos) {
        event.start(otaDto);
        svtService.updateTrack(otaDto, trackDtos);
        event.end(otaDto);
    }

    @Override
    public void requestTrackSetting(String vin, Integer trackInterval, Integer tracks) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 3);
        event.start(otaDto);
        svtService.requestTrackSetting(vin, trackInterval, tracks);
        sendQueue(otaDto, new TrackSettingReqDto(trackInterval, tracks));
        event.end(otaDto);
    }

    @Override
    public void requestSingleTrack(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 4);
        event.start(otaDto);
        svtService.requestSingleTrack(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void requestCloseAlarm(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 5);
        event.start(otaDto);
        svtService.requestCloseAlarm(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void responseCloseAlarm(OtaDto otaDto, Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos) {
        event.start(otaDto);
        svtService.responseCloseAlarm(otaDto, allAlarmClosed, stolenAlarmDtos);
        if(allAlarmClosed) {
            event.end(otaDto);
        }
        else {
            event.error(otaDto, 1);
        }
    }

    @Override
    public void requestAuthKey(String vin, Integer keyId) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 7);
        event.start(otaDto);
        svtService.requestAuthKey(vin, keyId);
        sendQueue(otaDto, new AuthKeyReqDto(keyId));
        event.end(otaDto);
    }

    @Override
    public void responseAuthKey(OtaDto otaDto, Boolean keyIsAccepted, Integer failureReason) {
        event.start(otaDto);
        svtService.responseAuthKey(otaDto, keyIsAccepted, failureReason);
        event.end(otaDto);
    }

    @Override
    public void requestImmobilise(String vin, Integer immoStatus) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 9);
        event.start(otaDto);
        svtService.requestImmobilise(vin, immoStatus);
        sendQueue(otaDto, new ImmobiliseReqDto(immoStatus));
        event.end(otaDto);
    }

    @Override
    public void responseImmobilise(OtaDto otaDto, Integer immoStatus, Integer failureReason) {
        event.start(otaDto);
        svtService.responseImmobilise(otaDto, immoStatus, failureReason);
        event.end(otaDto);
    }

    @Override
    public void requestUpdateProtectStrategy(String vin, Date startTime, Date endTime,
                                             List<ProtectStrategySettingDto> protectStrategySettingDtos) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 11);
        event.start(otaDto);
        svtService.requestUpdateProtectStrategy(vin, startTime, endTime, protectStrategySettingDtos);
        sendQueue(otaDto, new UpdateProtectStrategyReqDto(startTime, endTime, protectStrategySettingDtos));
        event.end(otaDto);
    }

    @Override
    public void responseUpdateProtectStrategy() {

    }

    @Override
    public void requestAlarm(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_SVT, 13);
        event.start(otaDto);
        svtService.requestAlarm(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }
}
