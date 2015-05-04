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
import com.zxq.iov.cloud.sp.vp.dao.ITaskParameterDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskStepDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.ITaskParameterRepository;
import com.zxq.iov.cloud.sp.vp.dao.repo.ITaskStepRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskParameter;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 任务参数持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-5-4 9:47:07
 * @version 0.1, 2015-5-4
 */
@Service
public class TaskParameterDaoServiceImpl extends BaseServiceImpl<ITaskParameterRepository, TaskParameter, Long> implements ITaskParameterDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskParameterDaoServiceImpl.class);

    @Autowired
	public TaskParameterDaoServiceImpl(ITaskParameterRepository repo){
		super(repo);
	}

	public TaskParameter createTaskParameter(TaskParameter taskParameter) {
		if (taskParameter == null){
			LOGGER.error("TaskParameter cannot be null");
		}
		taskParameter.setId(null);
		super.save(taskParameter);
		return taskParameter;
	}

	@Override
	public TaskParameter updateTaskParameter(TaskParameter taskParameter) {
		if (taskParameter == null){
			LOGGER.error("TaskParameter cannot be null");
		}
		super.update(taskParameter);
		return taskParameter;
	}

	@Override
	public TaskParameter findTaskParameterById(Long taskParameterId) {
		if (taskParameterId == null){
			LOGGER.error("taskParameterId cannot be null");
		}
		return super.findOne(taskParameterId);
	}
}