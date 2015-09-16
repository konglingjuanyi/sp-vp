/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-08       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.TaskInstanceDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 任务实例数据访问测试类
 */
@Transactional
public class TaskInstanceDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskInstanceDaoImplTest.class);

	@Autowired
	private ITaskInstanceDao taskInstanceDao;

	@Test
	@Rollback(true)
	public void testCreateTaskInstance() {
		Long taskDefinitionId = 13L;
		Long eventInstanceId = 9L;
		TaskInstance taskInstance = new TaskInstance();
		taskInstance.setTaskDefinitionId(taskDefinitionId);
		taskInstance.setEventInstanceId(eventInstanceId);
		taskInstance = taskInstanceDao.createTaskInstance(taskInstance);
		Assert.assertNotNull(taskInstance);
	}

	@Test
	@Rollback(true)
	public void testUpdateTaskInstance() {
		Long taskInstanceId = 5L;
		TaskInstance taskInstance = taskInstanceDao.findTaskInstanceById(taskInstanceId);
		taskInstance.setStartTime(new Date());
		taskInstance = taskInstanceDao.updateTaskInstance(taskInstance);
		Assert.assertNotNull(taskInstance);
		LOGGER.info("taskInstance's startTime = " + taskInstance.getStartTime());
	}

	@Test
	@Rollback(true)
	public void testRemoveTaskInstance() {
		Long taskInstanceId = 5L;
		taskInstanceDao.removeTaskInstance(taskInstanceId);
	}

	@Test
	@Rollback(true)
	public void testListTaskInstanceByEventInstanceId() {
		Long eventInstanceId = 9L;
		Long taskDefinitionId = 13L;
		Integer status = 1;
		List<TaskInstance> list = taskInstanceDao
				.listTaskInstanceByEventInstanceId(eventInstanceId, taskDefinitionId, status);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}
}
