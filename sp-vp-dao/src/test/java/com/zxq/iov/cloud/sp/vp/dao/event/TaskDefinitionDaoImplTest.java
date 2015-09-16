/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 * 2015-06-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.TaskDefinitionDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 安防服务 任务定义数据访问测试类
 */
@Transactional
public class TaskDefinitionDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskDefinitionDaoImplTest.class);

	@Autowired
	private ITaskDefinitionDao taskDefinitionDao;
	@Autowired
	private IEventDefinitionDao eventDefinitionDao;

	@Test
	@Rollback(true)
	public void testCreateTaskDefinition() {
		Long eventDefinitionId = 8L;
		TaskDefinition taskDefinition = new TaskDefinition();
		taskDefinition.setEventDefinition(eventDefinitionDao.findEventDefinitionById(eventDefinitionId));
		taskDefinition.setName("TBOX发起被盗警告");
		taskDefinition.setCycleLimit(1);
		taskDefinition.setIsExclusive(true);
		taskDefinition.setIsContinue(true);
		taskDefinition.setIsRollback(false);
		taskDefinition.setIsLast(false);
		taskDefinition.setSort(0);
		taskDefinition = taskDefinitionDao.createTaskDefinition(taskDefinition);
		Assert.assertNotNull(taskDefinition);
	}

	@Test
	@Rollback(true)
	public void testUpdateTaskDefinition() {
		Long taskDefinitionId = 12L;
		TaskDefinition taskDefinition = taskDefinitionDao.findTaskDefinitionById(taskDefinitionId);
		taskDefinition.setName("TBOX发起被盗警告2");
		taskDefinition = taskDefinitionDao.updateTaskDefinition(taskDefinition);
		Assert.assertNotNull(taskDefinition);
		LOGGER.info("taskDefinition's name = " + taskDefinition.getName());
	}

	@Test
	@Rollback(true)
	public void testRemoveTaskDefinition() {
		Long taskDefinitionId = 12L;
		taskDefinitionDao.removeTaskDefinition(taskDefinitionId);
	}

	@Test
	@Rollback(true)
	public void testListTaskDefinitionByEventDefinitionId() {
		Long eventDefinitionId = 8L;
		List<TaskDefinition> list = taskDefinitionDao.listTaskDefinitionByEventDefinitionId(eventDefinitionId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(true)
	public void testPagingTaskDefinition() {
		PageResult<TaskDefinition> pageResult = new PageResult<>();
		HashMap<String, Object> paramMap = new HashMap<>();
		pageResult = taskDefinitionDao.pagingTaskDefinition(pageResult, paramMap);
		Assert.assertTrue(pageResult.getTotalCount() > 0);
		LOGGER.info("pageResult totalCount = " + pageResult.getTotalCount());
	}
}
