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
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.TaskDefinitionDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.ITaskDefinitionRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 任务定义数据访问接口实现类
 */
@Service
public class TaskDefinitionDaoImpl extends BaseServiceImpl<ITaskDefinitionRepository, TaskDefinition, Long>
		implements ITaskDefinitionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskDefinitionDaoImpl.class);

	@Autowired
	public TaskDefinitionDaoImpl(ITaskDefinitionRepository repo) {
		super(repo);
	}

	public TaskDefinition createTaskDefinition(TaskDefinition taskDefinition) {
		if (taskDefinition == null) {
			LOGGER.error("taskDefinition cannot be null");
		}
		taskDefinition.setId(null);
		super.save(taskDefinition);
		return taskDefinition;
	}

	@Override
	public TaskDefinition updateTaskDefinition(TaskDefinition taskDefinition) {
		if (taskDefinition == null) {
			LOGGER.error("taskDefinition cannot be null");
		}
		super.update(taskDefinition);
		return taskDefinition;
	}

	@Override
	public void removeTaskDefinition(Long taskDefinitionId) {
		if (taskDefinitionId == null) {
			LOGGER.error("taskDefinitionId cannot be null");
		}
		super.delete(taskDefinitionId);
	}

	@Override
	public TaskDefinition findTaskDefinitionById(Long taskDefinitionId) {
		if (taskDefinitionId == null) {
			LOGGER.error("taskDefinitionId cannot be null");
		}
		return super.findOne(taskDefinitionId);
	}

	@Override
	public List<TaskDefinition> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventDefinitionId", eventDefinitionId);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public PageResult<TaskDefinition> pagingTaskDefinition(PageResult<TaskDefinition> pageResult,
			Map<String, Object> paramMap) {
		return super.getPagedListViaBatis(pageResult, paramMap);
	}
}