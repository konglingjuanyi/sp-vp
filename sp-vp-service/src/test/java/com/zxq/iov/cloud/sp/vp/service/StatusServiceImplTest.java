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
 * com.zxq.iov.cloud.sp.vp.service.StatusServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态服务测试类
 */
@Transactional
public class StatusServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImplTest.class);

    @Autowired
    private IStatusService statusService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testGetVehicleInfo() throws Exception {
        Long eventId = 230L;
        VehicleInfo vehicleInfo = statusService.getVehicleInfo(vin, eventId);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleInfo() throws Exception {
        Long eventId = 197L;
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus("status", 1));
        List<VehicleStatus> vehicleAlerts = new ArrayList<>();
        vehicleAlerts.add(new VehicleStatus("status", 1));
        statusService.logVehicleInfo(new Tbox(tboxId, vin, userId), null, null, vehiclePos, vehicleStatuses,
                vehicleAlerts, new Date(), eventId);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleAlert() throws Exception {
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        VehicleStatus vehicleAlert = new VehicleStatus("status", 1);
        statusService.logVehicleAlert(new Tbox(tboxId, vin, userId), new Date(), vehiclePos, vehicleAlert);
    }
}