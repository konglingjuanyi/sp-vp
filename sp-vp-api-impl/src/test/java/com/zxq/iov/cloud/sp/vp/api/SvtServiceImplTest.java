package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ProtectStrategySettingDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.TrackDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
 * modify date 2015-8-6 10:47
 * @version 0.4, 2015-8-6
 */
@Transactional
public class SvtServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ISvtApi svtApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testAlarm() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 1);
        List<StolenAlarmDto> stolenAlarmDtos = new ArrayList<>();
        StolenAlarmDto stolenAlarmDto = new StolenAlarmDto(1, true, true,
                new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1), new Date(), "");
        stolenAlarmDtos.add(stolenAlarmDto);
        svtApi.alarm(otaDto, stolenAlarmDtos);
    }

    @Test
    @Rollback(false)
    public void testUpdateTrack() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 2);
        List<TrackDto> trackDtos = new ArrayList<>();
        trackDtos.add(new TrackDto(new Date(), new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1),
                1, true, true, true, 1, 1, 1, 1, 1, 1, 1, true, new Date(), 1));
        svtApi.updateTrack(otaDto, trackDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestTrackSetting() throws Exception {
        Integer trackInterval = 5;
        Integer tracks = 10;
        svtApi.requestTrackSetting(vin, trackInterval, tracks);
    }

    @Test
    @Rollback(false)
    public void testRequestSingleTrack() throws Exception {
        svtApi.requestSingleTrack(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseAlarm() throws Exception {
        svtApi.requestCloseAlarm(vin);
    }

    @Test
    @Rollback(false)
    public void testResponseCloseAlarm() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 6);
        svtApi.responseCloseAlarm(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestAuthKey() throws Exception {
        Integer keyId = 1;
        svtApi.requestAuthKey(vin, keyId);
    }

    @Test
    @Rollback(false)
    public void testResponseAuthKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 8);
        svtApi.responseAuthKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestImmobilise() throws Exception {
        Integer immoStatus = 1;
        svtApi.requestImmobilise(vin, immoStatus);
    }

    @Test
    @Rollback(false)
    public void testResponseImmobilise() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_SVT, 10);
        Integer immoStatus = 1;
        svtApi.responseImmobilise(otaDto, immoStatus, null);
    }

    @Test
    @Rollback(false)
    public void testRequestUpdateProtectStrategy() throws Exception {
        List<ProtectStrategySettingDto> protectStrategySettingDtos = new ArrayList<>();
        svtApi.requestUpdateProtectStrategy(vin, new Date(), new Date(), protectStrategySettingDtos);
    }

    @Test
    @Rollback(false)
    public void testResponseUpdateProtectStrategy() throws Exception {
        svtApi.responseUpdateProtectStrategy();
    }

    @Test
    @Rollback(false)
    public void testRequestAlarm() throws Exception {
        svtApi.requestAlarm(vin);
    }
}
