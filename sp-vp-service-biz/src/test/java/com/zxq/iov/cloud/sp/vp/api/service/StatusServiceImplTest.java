package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
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
 * 安防 车辆状态服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-15 11:08
 * modify date 2015-6-29 9:29
 * @version 0.4, 2015-6-29
 */
@Transactional
public class StatusServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("statusServiceProxy")
    private IStatusService statusService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestVehicleStatus() {
        Integer statusType = 1;
        statusService.requestVehicleStatus(vin, statusType);
    }

    @Test
    @Rollback(false)
    public void testResponseVehicleStatus() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_STATUS, 2);
        otaDto.setEventId(122L);
        VehiclePosDto vehiclePosDto = new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(new VehicleStatusDto("status", 1));
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        vehicleAlertDtos.add(new VehicleAlertDto(1, new Date(),
                new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1), false, 1));
        statusService.responseVehicleStatus(otaDto, new Date(), vehiclePosDto, vehicleStatusDtos,
                vehicleAlertDtos);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleAlert() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_STATUS, 3);
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        vehicleAlertDtos.add(new VehicleAlertDto(1, new Date(),
                new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1), false, 1));
        statusService.logVehicleAlert(otaDto, vehicleAlertDtos);
    }
}
