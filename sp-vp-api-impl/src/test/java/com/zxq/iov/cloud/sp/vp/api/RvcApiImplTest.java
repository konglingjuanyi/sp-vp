/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-08-07       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.RvcApiImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.common.util.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 安防服务 远程控制API测试类
 */
@Transactional
public class RvcApiImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RvcApiImplTest.class);

    @Autowired
    private IRvcApi rvcApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testRequestControl() throws Exception {
        String command = "climate_control";
        String requestClient = "mobile";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "ac");
        parameters.put("target_temp", 18);
        Long controlCommandId =  rvcApi.requestControl(requestClient, userId, vin, command, parameters);
        Assert.assertNotNull(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testCancelControl() throws Exception {
        String command = "find_my_car";
        String requestClient = "mobile";
        rvcApi.cancelControl(requestClient, userId, vin, command);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlStatus() throws Exception {
        Integer mid = 2;
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_RVC, mid);
        otaDto.setEventId(241L);
        String rvcStatus = "00";
        VehiclePosDto vehiclePosDto =  new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(new VehicleStatusDto("status", 1));
        rvcApi.updateControlStatus(otaDto, BinaryAndHexUtil.hexStringToByte(rvcStatus),
                null, vehiclePosDto, vehicleStatusDtos);
    }

}
