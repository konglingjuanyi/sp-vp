package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IJourneyService;
import com.zxq.iov.cloud.sp.vp.api.dto.journey.JourneyDto;
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
 * 安防 行程服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-9 16:10
 * modify date 2015-6-12 10:00
 * @version 0.3, 2015-6-12
 */
@Transactional
public class JourneyServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("journeyServiceProxy")
    private IJourneyService journeyService;

    @Test
    @Rollback(false)
    public void testStartJourney() {
        Integer tboxJourneyId = 10;
        Long ownerId = 1L;
        Long keyId = 1L;
        String aid = "112";
        Integer mid = 1;
        Long tboxId = 1L;
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setEventCreateTime(new Date());
        journeyDto.setAid(aid);
        journeyDto.setMid(mid);
        journeyDto.setTboxId(tboxId);
        journeyDto.setTboxJourneyId(tboxJourneyId);
        journeyDto.setOwnerId(ownerId);
        journeyDto.setStartTime(new Date());
        journeyDto.setKeyId(keyId);
        journeyService.startJourney(journeyDto);
    }

    @Test
    @Rollback(false)
    public void testUpdateJourney() {
        Integer tboxJourneyId = 10;
        Long ownerId = 1L;
        Long keyId = 1L;
        String aid = "112";
        Integer mid = 2;
        Long tboxId = 1L;
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setEventCreateTime(new Date());
        journeyDto.setTboxJourneyId(tboxJourneyId);
        journeyDto.setOwnerId(ownerId);
        journeyDto.setKeyId(keyId);
        journeyDto.setAid(aid);
        journeyDto.setMid(mid);
        journeyDto.setTboxId(tboxId);
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setStatusTime(new Date());
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
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("瞬时油耗");
        vehicleStatusDto.setCode("instFuelConsumption");
        vehicleStatusDto.setValue(13);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleInfoDto.setVehicleStatusDtos(vehicleStatusDtos);
        journeyService.updateJourney(journeyDto, vehicleInfoDto);
    }

    @Test
    @Rollback(false)
    public void testEndJourney() {
        Integer tboxJourneyId = 10;
        Long ownerId = 1L;
        Long keyId = 1L;
        String aid = "112";
        Integer mid = 3;
        Long tboxId = 1L;
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setEventCreateTime(new Date());
        journeyDto.setTboxJourneyId(tboxJourneyId);
        journeyDto.setOwnerId(ownerId);
        journeyDto.setKeyId(keyId);
        journeyDto.setAid(aid);
        journeyDto.setMid(mid);
        journeyDto.setTboxId(tboxId);
        journeyDto.setEndTime(new Date());
        VehicleInfoDto startVehicleInfoDto = new VehicleInfoDto();
        VehiclePosDto startVehiclePosDto = new VehiclePosDto();
        startVehiclePosDto.setAltitude(1);
        startVehiclePosDto.setGpsStatus(1);
        startVehiclePosDto.setGpsTime(new Date());
        startVehiclePosDto.setHdop(1);
        startVehiclePosDto.setHeading(1);
        startVehiclePosDto.setLatitude(1);
        startVehiclePosDto.setLongitude(1);
        startVehiclePosDto.setSatellites(1);
        startVehiclePosDto.setSpeed(1);
        startVehicleInfoDto.setVehiclePosDto(startVehiclePosDto);
        VehicleInfoDto endVehicleInfoDto = new VehicleInfoDto();
        VehiclePosDto endVehiclePosDto = new VehiclePosDto();
        endVehiclePosDto.setAltitude(1);
        endVehiclePosDto.setGpsStatus(1);
        endVehiclePosDto.setGpsTime(new Date());
        endVehiclePosDto.setHdop(1);
        endVehiclePosDto.setHeading(1);
        endVehiclePosDto.setLatitude(1);
        endVehiclePosDto.setLongitude(1);
        endVehiclePosDto.setSatellites(1);
        endVehiclePosDto.setSpeed(1);
        endVehicleInfoDto.setVehiclePosDto(endVehiclePosDto);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("燃油剩余量");
        vehicleStatusDto.setCode("fuelLevelPrc");
        vehicleStatusDto.setValue(60);
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setName("剩余燃油可行驶距离");
        vehicleStatusDto.setCode("fuelRange");
        vehicleStatusDto.setValue(300);
        vehicleStatusDtos.add(vehicleStatusDto);
        endVehicleInfoDto.setVehicleStatusDtos(vehicleStatusDtos);
        journeyService.endJourney(journeyDto, startVehicleInfoDto, endVehicleInfoDto);
    }

}
