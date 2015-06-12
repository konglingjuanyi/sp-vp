package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IEcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
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
 * 安防 eCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-12 13:22
 * modify date
 * @version 0.1, 2015-6-12
 */
@Transactional
public class EcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("ecallServiceProxy")
    private IEcallService ecallService;

    @Test
    @Rollback(false)
    public void testStartEcall() {
        EcallDto ecallDto = new EcallDto();
        ecallDto.setAid("902");
        ecallDto.setMid(1);
        ecallDto.setEventCreateTime(new Date());
        ecallDto.setTboxId(1L);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(ecallDto.getEventCreateTime());
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
        ecallService.startEcall(ecallDto, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestEcallStatus() {
        Long tboxId = 1L;
        ecallService.requestEcallStatus(tboxId);
    }

    @Test
    @Rollback(false)
    public void testUpdateEcall() {
        EcallDto ecallDto = new EcallDto();
        ecallDto.setEventCreateTime(new Date());
        ecallDto.setAid("902");
        ecallDto.setMid(4);
        ecallDto.setTboxId(1L);
        List<VehicleInfoDto> vehicleInfoDtos = new ArrayList<>();
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(ecallDto.getEventCreateTime());
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
        ecallService.updateEcall(ecallDto, vehicleInfoDtos);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() {
        Long tboxId = 1L;
        Long callRecordId = 20L;
        ecallService.requestHangUp(tboxId, callRecordId);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() {
        Long tboxId = 1L;
        Long callId = 21L;
        String callNumber = "4008208888";
        ecallService.requestCallBack(tboxId, callId, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() {
        EcallRecordDto ecallRecordDto = new EcallRecordDto();
        ecallRecordDto.setTboxId(1L);
        ecallRecordDto.setAid("902");
        ecallRecordDto.setMid(8);
        ecallRecordDto.setEventCreateTime(new Date());
        ecallRecordDto.setErrorCode("1");
        ecallService.responseCallBack(ecallRecordDto);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseEcall() {
        Long tboxId = 1L;
        Long callId = 20L;
        ecallService.requestCloseEcall(tboxId, callId);
    }

    @Test
    @Rollback(false)
    public void testCloseEcall() {
        EcallDto ecallDto = new EcallDto();
        ecallDto.setTboxId(1L);
        ecallDto.setEventCreateTime(new Date());
        ecallDto.setAid("902");
        ecallDto.setMid(9);
        ecallService.closeEcall(ecallDto);
    }

}
