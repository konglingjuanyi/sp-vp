/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-12       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.EcallServiceImplTest
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 eCall服务测试类
 */
@Transactional
public class EcallServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EcallServiceImplTest.class);

    @Autowired
    private IEcallService ecallService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testStart() throws Exception {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallService.start(new Tbox(tboxId, vin, userId), vehiclePoses, 0, 1, 50, 60, new Date());
    }

    @Test
    @Rollback(false)
    public void testUpdate() throws Exception {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallService.update(new Tbox(tboxId, vin, userId), vehiclePoses, 0, 1, 50, 60, new Date());
    }

    @Test
    @Rollback(false)
    public void testHangUp() throws Exception {
        ecallService.hangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testCallBack() throws Exception {
        String callNumber = "4008208888";
        ecallService.callBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() throws Exception {
        ecallService.responseCallBack(tboxId, true, null);
    }

    @Test
    @Rollback(false)
    public void testClose() throws Exception {
        ecallService.close(tboxId);
    }

}