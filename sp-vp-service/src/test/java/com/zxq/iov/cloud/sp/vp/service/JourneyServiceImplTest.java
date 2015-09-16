/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-09       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.JourneyServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 行程服务测试类
 */
@Transactional
public class JourneyServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyServiceImplTest.class);

    @Autowired
    private IJourneyService journeyService;

    private Long tboxId = 1L;
    private String vin = "11111111111111111";
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testStart() throws Exception {
        Integer tboxJourneyId = 12;
        Integer keyId = 1;
        journeyService.start(new Tbox(tboxId, vin), new Date(), tboxJourneyId, keyId);
    }

    @Test
    @Rollback(false)
    public void testUpdate() throws Exception {
        Integer tboxJourneyId = 12;
        Integer instFuelConsumption = 1;
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        journeyService.update(new Tbox(tboxId, vin, userId), tboxJourneyId, instFuelConsumption, vehiclePos);
    }

    @Test
    @Rollback(false)
    public void testEnd() throws Exception {
        VehiclePos startVehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        VehiclePos endVehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Integer tboxJourneyId = 12;
        Integer distance = 1;
        Integer avgSpeed = 1;
        Integer fuelEco = 1;
        Integer odometer = 1;
        Integer fuelLevelPrc = 1;
        Integer fuelLevelDisp = 1;
        Integer fuelRange = 1;
        journeyService.end(new Tbox(tboxId, vin, userId), startVehiclePos, endVehiclePos, tboxJourneyId,
                distance, avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
    }

}