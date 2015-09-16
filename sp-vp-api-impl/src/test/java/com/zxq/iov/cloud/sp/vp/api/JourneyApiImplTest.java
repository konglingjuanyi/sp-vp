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
 * com.zxq.iov.cloud.sp.vp.api.JourneyApiImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 行程API测试类
 */
@Transactional
public class JourneyApiImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyApiImplTest.class);

    @Autowired
    private IJourneyApi journeyApi;

    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartJourney() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 1);
        Integer tboxJourneyId = 15;
        Integer keyId = 1;
        journeyApi.startJourney(otaDto, new Date(), tboxJourneyId, keyId);
    }

    @Test
    @Rollback(false)
    public void testUpdateJourney() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 2);
        Integer tboxJourneyId = 15;
        Integer instFuelConsumption = 1;
        VehiclePosDto vehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        journeyApi.updateJourney(otaDto, tboxJourneyId, instFuelConsumption, vehiclePosDto);
    }

    @Test
    @Rollback(false)
    public void testEndJourney() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 3);
        VehiclePosDto startVehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        VehiclePosDto endVehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Integer tboxJourneyId = 15;
        Integer distance = 1;
        Integer avgSpeed = 1;
        Integer fuelEco = 1;
        Integer odometer = 1;
        Integer fuelLevelPrc = 1;
        Integer fuelLevelDisp = 1;
        Integer fuelRange = 1;
        journeyApi.endJourney(otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId,
                distance, avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
    }

}
