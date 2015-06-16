package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.ISvtService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ImmoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.KeyAuthDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
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
 * modify date 2015-6-16 11:13
 * @version 0.2, 2015-6-16
 */
@Transactional
public class SvtServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("svtServiceProxy")
    private ISvtService svtService;

    @Test
    @Rollback(false)
    public void testAlarm() {
        String aid = "114";
        Integer mid = 1;
        Long tboxId = 1L;
        List<StolenAlarmDto> stolenAlarmDtos = new ArrayList<>();
        StolenAlarmDto stolenAlarmDto = new StolenAlarmDto();
        stolenAlarmDto.setAlarmTime(new Date());
        stolenAlarmDto.setAlarmType(1);
        stolenAlarmDto.setTboxId(tboxId);
        stolenAlarmDto.setAid(aid);
        stolenAlarmDto.setMid(mid);
        stolenAlarmDto.setEventCreateTime(new Date());
        stolenAlarmDtos.add(stolenAlarmDto);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDtos.add(vehicleInfoDto);
        svtService.alarm(stolenAlarmDtos, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testUpdateTrack() {
        String aid = "114";
        Integer mid = 2;
        Long tboxId = 1L;
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setTboxId(tboxId);
        vehicleInfoDto.setAid(aid);
        vehicleInfoDto.setMid(mid);
        vehicleInfoDto.setEventCreateTime(new Date());
        svtService.updateTrack(vehicleInfoDto);
    }

    @Test
    @Rollback(false)
    public void testRequestTrackSetting() {
        Integer trackInterval = 1;
        Integer tracks = 5;
        Long tboxId = 1L;
        svtService.requestTrackSetting(tboxId, trackInterval, tracks);
    }

    @Test
    @Rollback(false)
    public void testRequestSingleTrack() {
        Long tboxId = 1L;
        svtService.requestSingleTrack(tboxId);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseAlarm() {
        Long tboxId = 1L;
        svtService.requestCloseAlarm(tboxId);
    }

    @Test
    @Rollback(false)
    public void testResponseCloseAlarm() {
        String aid = "114";
        Integer mid = 6;
        Long tboxId = 1L;
        List<StolenAlarmDto> stolenAlarmDtos = new ArrayList<>();
        StolenAlarmDto stolenAlarmDto = new StolenAlarmDto();
        stolenAlarmDto.setAlarmTime(new Date());
        stolenAlarmDto.setAlarmType(1);
        stolenAlarmDto.setTboxId(tboxId);
        stolenAlarmDto.setAid(aid);
        stolenAlarmDto.setMid(mid);
        stolenAlarmDto.setEventCreateTime(new Date());
        stolenAlarmDtos.add(stolenAlarmDto);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDtos.add(vehicleInfoDto);
        svtService.responseCloseAlarm(true, stolenAlarmDtos, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestAuthKey() {
        Long keyId = 1L;
        Long tboxId = 1L;
        svtService.requestAuthKey(tboxId, keyId);
    }

    @Test
    @Rollback(false)
    public void testResponseAuthKey() {
        String aid = "114";
        Integer mid = 8;
        Long tboxId = 1L;
        KeyAuthDto keyAuthDto = new KeyAuthDto();
        keyAuthDto.setTboxId(tboxId);
        keyAuthDto.setAid(aid);
        keyAuthDto.setMid(mid);
        keyAuthDto.setEventCreateTime(new Date());
        svtService.responseAuthKey(keyAuthDto);
    }

    @Test
    @Rollback(false)
    public void testRequestImmobilise() {
        Long tboxId = 1L;
        Integer immoStatus = 1;
        svtService.requestImmobilise(tboxId, immoStatus);
    }

    @Test
    @Rollback(false)
    public void testResponseImmobilise() {
        String aid = "114";
        Integer mid = 10;
        Long tboxId = 1L;
        Integer immoStatus = 1;
        ImmoDto immoDto = new ImmoDto();
        immoDto.setTboxId(tboxId);
        immoDto.setAid(aid);
        immoDto.setMid(mid);
        immoDto.setEventCreateTime(new Date());
        immoDto.setImmoStatus(immoStatus);
        svtService.responseImmobilise(immoDto);
    }

    @Test
    @Rollback(false)
    public void testRequestUpdateProtectStrategy() {
        Long ownerId = 1L;
        Long keyId = 1L;
        String aid = "112";
        Long tboxId = 1L;
        Integer immoStatus = 1;
        svtService.requestUpdateProtectStrategy(tboxId);
    }

    @Test
    @Rollback(false)
    public void testResponseUpdateProtectStrategy() {
        Long ownerId = 1L;
        Long keyId = 1L;
        String aid = "112";
        Long tboxId = 1L;
        Integer immoStatus = 1;
        svtService.responseUpdateProtectStrategy();
    }

    @Test
    @Rollback(false)
    public void testRequestAlarm() {
        Long tboxId = 1L;
        svtService.requestAlarm(tboxId);
    }
}
