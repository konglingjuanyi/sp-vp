/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: VehicleServiceImpl.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.service
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.dao.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskStepDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.ITaskRepository;
import com.zxq.iov.cloud.sp.vp.dao.repo.ITaskStepRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 任务步骤持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-30 15:54:07
 * @version 0.1, 2015-4-30
 */
@Service
public class TaskStepDaoServiceImpl extends BaseServiceImpl<ITaskStepRepository, TaskStep, Long> implements ITaskStepDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskStepDaoServiceImpl.class);

    @Autowired
	public TaskStepDaoServiceImpl(ITaskStepRepository repo){
		super(repo);
	}

	public TaskStep createTaskStep(TaskStep taskStep) {
		if (taskStep == null){
			LOGGER.error("TaskStep cannot be null");
		}
		taskStep.setId(null);
		super.save(taskStep);
		return taskStep;
	}

	@Override
	public TaskStep updateTaskStep(TaskStep taskStep) {
		if (taskStep == null){
			LOGGER.error("taskStep cannot be null");
		}
		super.update(taskStep);
		return taskStep;
	}

	@Override
	public TaskStep findTaskStepById(Long taskStepId) {
		if (taskStepId == null){
			LOGGER.error("taskStepId cannot be null");
		}
		return super.findOne(taskStepId);
	}
}