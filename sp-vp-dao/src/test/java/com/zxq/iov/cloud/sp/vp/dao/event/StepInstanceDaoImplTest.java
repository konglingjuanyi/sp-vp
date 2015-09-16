/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-15       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.StepInstanceDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 步骤实例数据访问测试类
 */
@Transactional
public class StepInstanceDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepInstanceDaoImplTest.class);

	@Autowired
	private IStepInstanceDao stepInstanceDao;

	@Test
	@Rollback(true)
	public void testCreateStepInstance() {
		Long stepDefinitionId = 9L;
		Long taskInstanceId = 6L;
		StepInstance stepInstance = new StepInstance();
		stepInstance.setStepDefinitionId(stepDefinitionId);
		stepInstance.setTaskInstanceId(taskInstanceId);
		stepInstance.setRetryCount(3);
		stepInstance = stepInstanceDao.createStepInstance(stepInstance);
		Assert.assertNotNull(stepInstance);
	}

	@Test
	@Rollback(true)
	public void testUpdateStepInstance() {
		Long stepInstanceId = 8L;
		StepInstance stepInstance = stepInstanceDao.findStepInstanceById(stepInstanceId);
		stepInstance.setStartTime(new Date());
		stepInstance = stepInstanceDao.updateStepInstance(stepInstance);
		Assert.assertNotNull(stepInstance);
		LOGGER.info("stepInstance's startTime = " + stepInstance.getStartTime());
	}

	@Test
	@Rollback(true)
	public void testRemoveStepInstance() {
		Long stepInstanceId = 8L;
		stepInstanceDao.removeStepInstance(stepInstanceId);
	}

	@Test
	@Rollback(true)
	public void testListStepInstanceByTaskInstanceId() {
		Long taskInstanceId = 6L;
		List<StepInstance> list = stepInstanceDao.listStepInstanceByTaskInstanceId(taskInstanceId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(true)
	public void testListStepInstanceByEventInstanceId() {
		Long eventInstanceId = 9L;
		Long stepDefinitionId = 9L;
		Integer status = 1;
		List<StepInstance> list = stepInstanceDao
				.listStepInstanceByEventInstanceId(eventInstanceId, stepDefinitionId, status);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(true)
	public void testListStepInstanceByOwner() {
		String owner = "1";
		Long stepDefinitionId = 47L;
		Integer status = 2;
		List<StepInstance> list = stepInstanceDao.listStepInstanceByOwner(owner, stepDefinitionId, status);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

}
