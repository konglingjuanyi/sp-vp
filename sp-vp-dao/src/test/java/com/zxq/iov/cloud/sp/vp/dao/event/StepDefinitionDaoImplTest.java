/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 * 2015-06-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.StepDefinitionDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 安防服务 步骤定义数据访问测试类
 */
@Transactional
public class StepDefinitionDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitionDaoImplTest.class);

	@Autowired
	private ITaskDefinitionDao taskDefinitionDao;
	@Autowired
	private IStepDefinitionDao stepDefinitionDao;

	@Test
	@Rollback(true)
	public void testCreateStepDefinition() {
		Long taskDefinitionId = 13L;
		StepDefinition stepDefinition = new StepDefinition();
		stepDefinition.setTaskDefinition(taskDefinitionDao.findTaskDefinitionById(taskDefinitionId));
		stepDefinition.setName("下发锁定车辆命令");
		stepDefinition.setStartCode("1149");
		stepDefinition.setIsRollback(false);
		stepDefinition.setIsLast(true);
		stepDefinition.setSort(0);
		stepDefinition = stepDefinitionDao.createStepDefinition(stepDefinition);
		Assert.assertNotNull(stepDefinition);
	}

	@Test
	@Rollback(true)
	public void testUpdateStepDefinition() {
		Long stepDefinitionId = 8L;
		StepDefinition stepDefinition = stepDefinitionDao.findStepDefinitionById(stepDefinitionId);
		stepDefinition.setName("下发锁定车辆命令2");
		stepDefinition = stepDefinitionDao.updateStepDefinition(stepDefinition);
		Assert.assertNotNull(stepDefinition);
		LOGGER.info("stepDefinition's name = " + stepDefinition.getName());
	}

	@Test
	@Rollback(true)
	public void testRemoveStepDefinition() {
		Long stepDefinitionId = 8L;
		stepDefinitionDao.removeStepDefinition(stepDefinitionId);
	}

	@Test
	@Rollback(true)
	public void testListStepDefinitionByCodeAndEventDefinitionId() {
		String startCode = "1113";
		Long eventDefinitionId = 23L;
		List<StepDefinition> list = stepDefinitionDao
				.listStepDefinitionByStartCodeAndEventDefinitionId(startCode, eventDefinitionId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(true)
	public void testListStepDefinitionByTaskDefinitionId() {
		Long taskDefinitionId = 13L;
		List<StepDefinition> list = stepDefinitionDao.listStepDefinitionByTaskDefinitionId(taskDefinitionId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(true)
	public void testPagingStepDefinition() {
		PageResult<StepDefinition> pageResult = new PageResult<>();
		HashMap<String, Object> paramMap = new HashMap<>();
		pageResult = stepDefinitionDao.pagingStepDefinition(pageResult, paramMap);
		Assert.assertTrue(pageResult.getTotalCount() > 0);
		LOGGER.info("pageResult totalCount = " + pageResult.getTotalCount());
	}
}
