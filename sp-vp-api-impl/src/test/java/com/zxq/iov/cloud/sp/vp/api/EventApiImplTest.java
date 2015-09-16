/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-30       荣杰         1.0            Initial Version
 * 2015-08-07       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.EventApiImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 事件API测试类
 */
@Transactional
public class EventApiImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventApiImplTest.class);

	@Autowired
	private IEventApi eventService;

	@Test
	@Rollback(false)
	public void testDispatchAck() throws Exception {
		Long stepId = 535L;
		Long tboxId = 1L;
		eventService.dispatchAck(tboxId, stepId);
	}

}
