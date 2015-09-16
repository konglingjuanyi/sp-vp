/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.SvtServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 被盗追踪服务测试类
 */
@Transactional
public class SvtServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SvtServiceImplTest.class);

    @Autowired
    private ISvtService svtService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testAlarm() throws Exception {
        StolenAlarm stolenAlarm = new StolenAlarm();
        stolenAlarm.setAlarmTime(new Date());
        stolenAlarm.setAlarmType(1);
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Long eventId = 1L;
        svtService.alarm(new Tbox(tboxId, vin, userId), stolenAlarm, vehiclePos, eventId);
    }

    @Test
    @Rollback(false)
    public void testUpdateTrack() throws Exception {
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Long eventId = 1L;
        svtService.updateTrack(new Tbox(tboxId, vin, userId), vehicleStatuses, vehiclePos, eventId);
    }

}