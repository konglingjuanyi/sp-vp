/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-30       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.service.EventServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 事件服务测试类
 */
@Transactional
public class EventServiceImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImplTest.class);

    @Autowired
    private IEventService eventService;

	private String vin = "11111111111111111";

    @Test
    @Rollback(false)
    public void testDispatchAck() throws Exception {
	    Long stepId = 289L;
        eventService.dispatchAck(vin, stepId);
    }

}
