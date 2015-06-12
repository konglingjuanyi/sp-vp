package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IBcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 bCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-11 12:56
 * modify date 2015-6-12 10:46
 * @version 0.2, 2015-6-12
 */
@Transactional
public class BcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("bcallServiceProxy")
    private IBcallService bcallService;

    @Test
    @Rollback(false)
    public void testStartBcall() {
        BcallDto bcallDto = new BcallDto();
        bcallDto.setAid("903");
        bcallDto.setMid(1);
        bcallDto.setEventCreateTime(new Date());
        bcallDto.setTboxId(1L);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(bcallDto.getEventCreateTime());
        VehiclePosDto vehiclePosDto = new VehiclePosDto();
        vehiclePosDto.setAltitude(1);
        vehiclePosDto.setGpsStatus(1);
        vehiclePosDto.setGpsTime(new Date());
        vehiclePosDto.setHdop(1);
        vehiclePosDto.setHeading(1);
        vehiclePosDto.setLatitude(1);
        vehiclePosDto.setLongitude(1);
        vehiclePosDto.setSatellites(1);
        vehiclePosDto.setSpeed(1);
        vehicleInfoDto.setVehiclePosDto(vehiclePosDto);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("TBOX剩余电量");
        vehicleStatusDto.setCode("tboxInternalBatteryStatus");
        vehicleStatusDto.setValue(65);
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("车辆剩余电量");
        vehicleStatusDto.setCode("vehicleBatteryStatus");
        vehicleStatusDto.setValue(75);
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleInfoDto.setVehicleStatusDtos(vehicleStatusDtos);
        vehicleInfoDtos.add(vehicleInfoDto);
        bcallService.startBcall(bcallDto, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestBcallStatus() {
        Long tboxId = 1L;
        bcallService.requestBcallStatus(tboxId);
    }

    @Test
    @Rollback(false)
    public void testUpdateBcall() {
        BcallDto bcallDto = new BcallDto();
        bcallDto.setEventCreateTime(new Date());
        bcallDto.setAid("903");
        bcallDto.setMid(4);
        bcallDto.setTboxId(1L);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(bcallDto.getEventCreateTime());
        VehiclePosDto vehiclePosDto = new VehiclePosDto();
        vehiclePosDto.setAltitude(1);
        vehiclePosDto.setGpsStatus(1);
        vehiclePosDto.setGpsTime(new Date());
        vehiclePosDto.setHdop(1);
        vehiclePosDto.setHeading(1);
        vehiclePosDto.setLatitude(1);
        vehiclePosDto.setLongitude(1);
        vehiclePosDto.setSatellites(1);
        vehiclePosDto.setSpeed(1);
        vehicleInfoDto.setVehiclePosDto(vehiclePosDto);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("TBOX剩余电量");
        vehicleStatusDto.setCode("tboxInternalBatteryStatus");
        vehicleStatusDto.setValue(65);
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("车辆剩余电量");
        vehicleStatusDto.setCode("vehicleBatteryStatus");
        vehicleStatusDto.setValue(75);
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleInfoDto.setVehicleStatusDtos(vehicleStatusDtos);
        vehicleInfoDtos.add(vehicleInfoDto);
        bcallService.updateBcall(bcallDto, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() {
        Long tboxId = 1L;
        Long callRecordId = 16L;
        bcallService.requestHangUp(tboxId, callRecordId);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() {
        Long tboxId = 1L;
        Long callId = 19L;
        String callNumber = "4008208888";
        bcallService.requestCallBack(tboxId, callId, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() {
        BcallRecordDto bcallRecordDto = new BcallRecordDto();
        bcallRecordDto.setTboxId(1L);
        bcallRecordDto.setAid("903");
        bcallRecordDto.setMid(8);
        bcallRecordDto.setEventCreateTime(new Date());
        bcallRecordDto.setErrorCode("1");
        bcallService.responseCallBack(bcallRecordDto);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseBcall() {
        Long tboxId = 1L;
        Long callId = 19L;
        bcallService.requestCloseBcall(tboxId, callId);
    }

    @Test
    @Rollback(false)
    public void testCloseBcall() {
        BcallDto bcallDto = new BcallDto();
        bcallDto.setTboxId(1L);
        bcallDto.setEventCreateTime(new Date());
        bcallDto.setAid("903");
        bcallDto.setMid(9);
        bcallService.closeBcall(bcallDto);
    }

}
