/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-07-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.rvc.ControlCommandDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.rvc;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 控制命令数据访问测试类
 */
@Transactional
public class ControlCommandDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControlCommandDaoImplTest.class);

	private Long tboxId = 1L;
	private String vin = "11111111111111111";

	@Autowired
	private IControlCommandDao controlCommandDao;

	@Test
	@Rollback(false)
	public void testCreateControlCommand() {
		Long eventId = 1L;
		String requestClient = "mobile";
		ControlCommand controlCommand = new ControlCommand(tboxId, vin, requestClient,
				Constants.RVC_CMD.get("find_my_car"), Constants.RVC_CMD_CODE.get("find_my_car"), null);
		controlCommand.setEventId(eventId);
		controlCommand = controlCommandDao.createControlCommand(controlCommand);
		Assert.assertNotNull(controlCommand);
	}

	@Test
	@Rollback(false)
	public void testUpdateControlCommand() {
		Long controlCommandId = 8L;
		ControlCommand controlCommand = controlCommandDao.findControlCommandById(controlCommandId);
		controlCommand.setParameter("1");
		controlCommand = controlCommandDao.updateControlCommand(controlCommand);
		Assert.assertNotNull(controlCommand);
		LOGGER.info("controlCommand's parameter = " + controlCommand.getParameter());
	}

	@Test
	@Rollback(false)
	public void testRemoveControlCommand() {
		Long controlCommandId = 8L;
		controlCommandDao.removeControlCommand(controlCommandId);
	}

	@Test
	@Rollback(false)
	public void testFindControlCommandByEventId() {
		Long eventId = 1L;
		ControlCommand controlCommand = controlCommandDao.findControlCommandByEventId(eventId);
		Assert.assertNotNull(controlCommand);
	}

}
