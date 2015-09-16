/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.RvcServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 安防服务 远程控制服务测试类
 */
@Transactional
public class RvcServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RvcServiceImplTest.class);

    @Autowired
    private IRvcService rvcService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testRequestControl() throws Exception {
        Long eventId = 1L;
        String command = "find_my_car";
        String requestClient = "mobile";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("light", true);
        parameters.put("horn", "low");
        parameters.put("horn_length", 30);
        ControlCommand controlCommand = rvcService.requestControl(requestClient, userId, new Tbox(tboxId, vin, userId), command,
                parameters, eventId);
        Assert.assertNotNull(controlCommand);
    }

    @Test
    @Rollback(false)
    public void testCancelControl() throws Exception {
        String command = "5";
        String requestClient = "mobile";
        rvcService.cancelControl(requestClient, userId, new Tbox(tboxId, vin, userId), command);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlStatus() throws Exception {
        Long eventId = 1L;
        String rvcStatus = "3";
        VehiclePos vehiclePos =  new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus("status", 1));
        rvcService.updateControlStatus(new Tbox(tboxId, vin, userId), rvcStatus.getBytes(),
                null, vehiclePos, vehicleStatuses, eventId);
    }

}