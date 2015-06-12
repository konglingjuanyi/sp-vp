package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IIcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
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
 * 安防 iCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:50
 * modify date
 * @version 0.1, 2015-6-12
 */
@Transactional
public class IcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("icallServiceProxy")
    private IIcallService icallService;

    @Test
    @Rollback(false)
    public void testStartIcall() {
        IcallDto icallDto = new IcallDto();
        icallDto.setAid("904");
        icallDto.setMid(1);
        icallDto.setEventCreateTime(new Date());
        icallDto.setTboxId(1L);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(icallDto.getEventCreateTime());
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
        icallService.startIcall(icallDto, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestIcallStatus() {
        Long tboxId = 1L;
        icallService.requestIcallStatus(tboxId);
    }

    @Test
    @Rollback(false)
    public void testUpdateIcall() {
        IcallDto icallDto = new IcallDto();
        icallDto.setEventCreateTime(new Date());
        icallDto.setAid("904");
        icallDto.setMid(4);
        icallDto.setTboxId(1L);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(icallDto.getEventCreateTime());
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
        icallService.updateIcall(icallDto, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() {
        Long tboxId = 1L;
        Long callRecordId = 24L;
        icallService.requestHangUp(tboxId, callRecordId);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() {
        Long tboxId = 1L;
        Long callId = 23L;
        String callNumber = "4008208888";
        icallService.requestCallBack(tboxId, callId, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() {
        IcallRecordDto icallRecordDto = new IcallRecordDto();
        icallRecordDto.setTboxId(1L);
        icallRecordDto.setAid("904");
        icallRecordDto.setMid(8);
        icallRecordDto.setEventCreateTime(new Date());
        icallRecordDto.setErrorCode("1");
        icallService.responseCallBack(icallRecordDto);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseIcall() {
        Long tboxId = 1L;
        Long callId = 22L;
        icallService.requestCloseIcall(tboxId, callId);
    }

    @Test
    @Rollback(false)
    public void testCloseIcall() {
        IcallDto icallDto = new IcallDto();
        icallDto.setTboxId(1L);
        icallDto.setEventCreateTime(new Date());
        icallDto.setAid("904");
        icallDto.setMid(9);
        icallService.closeIcall(icallDto);
    }

}
