package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-15 11:08
 * @version 0.1, 2015-5-15
 */
@Transactional
public class StatusServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;

    @Test
    @Rollback(true)
    public void testRequestVehicleStatus() {
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        Integer statusType = 1;
        statusService.requestVehicleStatus(vehicleInfoDto, statusType);
    }

    @Test
    @Rollback(true)
    public void testUpdateVehicleStatus() {
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setSourceType(1);
        VehiclePosDto vehiclePosDto = new VehiclePosDto();
        vehiclePosDto.setAltitude(1);
        vehiclePosDto.setGpsStatus(1);
        vehiclePosDto.setHdop(1);
        vehiclePosDto.setGpsTime(new Date());
        vehiclePosDto.setHeading(0);
        vehiclePosDto.setLatitude(1);
        vehiclePosDto.setLongitude(1);
        vehiclePosDto.setSatellites(1);
        vehiclePosDto.setSpeed(1);
        vehicleInfoDto.setVehiclePosDto(vehiclePosDto);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();
        vehicleStatusDto.setCode("001");
        vehicleStatusDto.setName("001");
        vehicleStatusDto.setValue(0);
        vehicleStatusDtos.add(vehicleStatusDto);
        vehicleInfoDto.setVehicleStatusDtos(vehicleStatusDtos);
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        VehicleAlertDto vehicleAlertDto = new VehicleAlertDto();
        vehicleAlertDto.setCode("001");
        vehicleAlertDto.setName("001");
        vehicleAlertDto.setValue(0);
        vehicleAlertDtos.add(vehicleAlertDto);
        vehicleInfoDto.setVehicleAlertDtos(vehicleAlertDtos);
        statusService.updateVehicleStatus(vehicleInfoDto);
    }

    @Test
    @Rollback(true)
    public void testGetVehicleStatus() {
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setId(29L);
        vehicleInfoDto = statusService.getVehicleStatus(vehicleInfoDto);
        Assert.assertNotNull(vehicleInfoDto);
        Assert.assertNotNull(vehicleInfoDto.getVehiclePosDto());
        Assert.assertTrue(vehicleInfoDto.getVehicleStatusDtos().size()>=0);
        Assert.assertTrue(vehicleInfoDto.getVehicleAlertDtos().size()>=0);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleAlert() {
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setSourceType(1);
        VehiclePosDto vehiclePosDto = new VehiclePosDto();
        vehiclePosDto.setAltitude(1);
        vehiclePosDto.setGpsStatus(1);
        vehiclePosDto.setHdop(1);
        vehiclePosDto.setGpsTime(new Date());
        vehiclePosDto.setHeading(0);
        vehiclePosDto.setLatitude(1);
        vehiclePosDto.setLongitude(1);
        vehiclePosDto.setSatellites(1);
        vehiclePosDto.setSpeed(1);
        vehicleInfoDto.setVehiclePosDto(vehiclePosDto);
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        VehicleAlertDto vehicleAlertDto = new VehicleAlertDto();
        vehicleAlertDto.setCode("001");
        vehicleAlertDto.setName("001");
        vehicleAlertDto.setValue(0);
        vehicleAlertDtos.add(vehicleAlertDto);
        vehicleInfoDto.setVehicleAlertDtos(vehicleAlertDtos);
        statusService.logVehicleAlert(vehicleInfoDto);
    }
}
