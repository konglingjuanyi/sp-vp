/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: IVehicleService.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.service
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.dao;

import com.zxq.iov.cloud.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;

/**
 * 安防 任务步骤持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-4-30 15:48:07
 * @version 0.1, 2015-4-30
 */
public interface ITaskStepDaoService extends BaseService<TaskStep, Long> {

	/**
	 * 创建任务步骤
	 * @param taskStep	任务步骤对象
	 * @return			任务步骤对象
	 */
	TaskStep createTaskStep (TaskStep taskStep);

	/**
	 * 更新任务步骤
	 * @param taskStep	任务步骤对象
	 * @return			任务步骤对象
	 */
	TaskStep updateTaskStep(TaskStep taskStep);

	/**
	 * 根据Task ID得到任务步骤对象
	 * @param taskStepId 	任务步骤ID
	 * @return				任务步骤对象
	 */
	TaskStep findTaskStepById(Long taskStepId);
	
}