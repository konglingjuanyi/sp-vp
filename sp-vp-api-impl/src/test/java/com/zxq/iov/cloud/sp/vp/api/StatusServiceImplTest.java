/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-15       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.StatusServiceImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态API测试类
 */
@Transactional
public class StatusServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImplTest.class);

    @Autowired
    private IStatusApi statusApi;

    private String vin = "123456";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestVehicleStatus() throws Exception {
        Integer statusType = 1;
        Long eventId = statusApi.requestVehicleStatus(vin, statusType);
        LOGGER.info("eventId = " + eventId);
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

	/**
     * 测试获得TBOX尚未响应的车辆状态
     * @throws Exception
     */
    @Test
    @Rollback(false)
    public void testGetVehicleStatusNoTboxResponse() throws Exception {
        Long eventId = 69117L;
        VehicleInfoDto vehicleInfoDto = statusApi.getVehicleStatus(vin, eventId);
        Assert.assertNull(vehicleInfoDto);
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
