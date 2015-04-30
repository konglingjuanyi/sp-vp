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
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.IEventRepository;
import com.zxq.iov.cloud.sp.vp.dao.repo.ITaskRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 任务持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-30 14:51:07
 * @version 0.1, 2015-4-30
 */
@Service
public class TaskDaoServiceImpl extends BaseServiceImpl<ITaskRepository, Task, Long> implements ITaskDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskDaoServiceImpl.class);

    @Autowired
	public TaskDaoServiceImpl(ITaskRepository repo){
		super(repo);
	}

	public Task createTask(Task task) {
		if (task == null){
			LOGGER.error("Task cannot be null");
		}
		task.setId(null);
		super.save(task);
		return task;
	}

	@Override
	public Task updateTask(Task task) {
		if (task == null){
			LOGGER.error("Task cannot be null");
		}
		super.update(task);
		return task;
	}

	@Override
	public Task findTaskById(Long taskId) {
		if (taskId == null){
			LOGGER.error("Task ID cannot be null");
		}
		return super.findOne(taskId);
	}
}