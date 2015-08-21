package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
 * modify date 2015-8-6 9:50
 * @version 0.6, 2015-8-6
 */
@Transactional
public class StatusServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IStatusApi statusApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestVehicleStatus() throws Exception {
        Integer statusType = 1;
        Long eventId = statusApi.requestVehicleStatus(vin, statusType);
        System.out.print(eventId);
    }

    @Test
    @Rollback(false)
    public void testResponseVehicleStatus() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_STATUS, 2);
        otaDto.setEventId(229L);
        VehiclePosDto vehiclePosDto = new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(new VehicleStatusDto("status", 1));
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        vehicleAlertDtos.add(new VehicleAlertDto(1, new Date(),
                new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1), false, 1));
        statusApi.responseVehicleStatus(otaDto, new Date(), vehiclePosDto, vehicleStatusDtos,
                vehicleAlertDtos);
    }

    @Test
    @Rollback(false)
    public void testGetVehicleStatus() throws Exception {
        Long eventId = 230L;
        VehicleInfoDto vehicleInfoDto = statusApi.getVehicleStatus(vin, eventId);
        Assert.assertNotNull(vehicleInfoDto);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleAlert() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_STATUS, 3);
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        vehicleAlertDtos.add(new VehicleAlertDto(1, new Date(),
                new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1), false, 1));
        statusApi.logVehicleAlert(otaDto, vehicleAlertDtos);
    }
}
