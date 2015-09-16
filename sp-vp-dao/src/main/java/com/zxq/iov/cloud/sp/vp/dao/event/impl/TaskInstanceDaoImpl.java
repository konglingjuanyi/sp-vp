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
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.TaskInstanceDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskInstanceDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.ITaskInstanceRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 任务实例数据访问接口实现类
 */
@Service
public class TaskInstanceDaoImpl extends BaseServiceImpl<ITaskInstanceRepository, TaskInstance, Long>
		implements ITaskInstanceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskInstanceDaoImpl.class);

	@Autowired
	public TaskInstanceDaoImpl(ITaskInstanceRepository repo) {
		super(repo);
	}

	public TaskInstance createTaskInstance(TaskInstance taskInstance) {
		if (taskInstance == null) {
			LOGGER.error("taskInstance cannot be null");
		}
		taskInstance.setId(null);
		super.save(taskInstance);
		return taskInstance;
	}

	@Override
	public TaskInstance updateTaskInstance(TaskInstance taskInstance) {
		if (taskInstance == null) {
			LOGGER.error("taskInstance cannot be null");
		}
		super.update(taskInstance);
		return taskInstance;
	}

	@Override
	public void removeTaskInstance(Long taskInstanceId) {
		if (taskInstanceId == null) {
			LOGGER.error("taskInstanceId cannot be null");
		}
		super.delete(taskInstanceId);
	}

	@Override
	public TaskInstance findTaskInstanceById(Long taskInstanceId) {
		if (taskInstanceId == null) {
			LOGGER.error("taskInstanceId cannot be null");
		}
		return super.findOne(taskInstanceId);
	}

	@Override
	public List<TaskInstance> listTaskInstanceByEventInstanceId(Long eventInstanceId, Long taskDefinitionId,
			Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventInstanceId", eventInstanceId);
		paramMap.put("taskDefinitionId", taskDefinitionId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

}