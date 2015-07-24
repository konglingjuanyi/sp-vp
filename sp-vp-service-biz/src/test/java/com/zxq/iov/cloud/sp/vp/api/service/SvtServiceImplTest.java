package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.ISvtService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ProtectStrategySettingDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.TrackDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 被盗追踪服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-15 16:49
 * modify date 2015-6-26 11:11
 * @version 0.3, 2015-6-26
 */
@Transactional
public class SvtServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("svtServiceProxy")
    private ISvtService svtService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testAlarm() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 1);
        List<StolenAlarmDto> stolenAlarmDtos = new ArrayList<>();
        StolenAlarmDto stolenAlarmDto = new StolenAlarmDto(1, true, true,
                new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1), new Date(), "");
        stolenAlarmDtos.add(stolenAlarmDto);
        svtService.alarm(otaDto, stolenAlarmDtos);
    }

    @Test
    @Rollback(false)
    public void testUpdateTrack() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 2);
        List<TrackDto> trackDtos = new ArrayList<>();
        trackDtos.add(new TrackDto(new Date(), new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1),
                1, true, true, true, 1, 1, 1, 1, 1, 1, 1, true, new Date(), 1));
        svtService.updateTrack(otaDto, trackDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestTrackSetting() throws Exception {
        Integer trackInterval = 5;
        Integer tracks = 10;
        svtService.requestTrackSetting(vin, trackInterval, tracks);
    }

    @Test
    @Rollback(false)
    public void testRequestSingleTrack() throws Exception {
        svtService.requestSingleTrack(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseAlarm() throws Exception {
        svtService.requestCloseAlarm(vin);
    }

    @Test
    @Rollback(false)
    public void testResponseCloseAlarm() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 6);
        svtService.responseCloseAlarm(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestAuthKey() throws Exception {
        Integer keyId = 1;
        svtService.requestAuthKey(vin, keyId);
    }

    @Test
    @Rollback(false)
    public void testResponseAuthKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 8);
        svtService.responseAuthKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestImmobilise() throws Exception {
        Integer immoStatus = 1;
        svtService.requestImmobilise(vin, immoStatus);
    }

    @Test
    @Rollback(false)
    public void testResponseImmobilise() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 10);
        Integer immoStatus = 1;
        svtService.responseImmobilise(otaDto, immoStatus, null);
    }

    @Test
    @Rollback(false)
    public void testRequestUpdateProtectStrategy() throws Exception {
        List<ProtectStrategySettingDto> protectStrategySettingDtos = new ArrayList<>();
        svtService.requestUpdateProtectStrategy(vin, new Date(), new Date(), protectStrategySettingDtos);
    }

    @Test
    @Rollback(false)
    public void testResponseUpdateProtectStrategy() throws Exception {
        svtService.responseUpdateProtectStrategy();
    }

    @Test
    @Rollback(false)
    public void testRequestAlarm() throws Exception {
        svtService.requestAlarm(vin);
    }
}
