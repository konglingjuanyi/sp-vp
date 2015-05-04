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
import com.zxq.iov.cloud.sp.vp.entity.event.TaskParameter;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;

/**
 * 安防 任务参数持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-4 9:39:07
 * @version 0.1, 2015-5-4
 */
public interface ITaskParameterDaoService extends BaseService<TaskParameter, Long> {

	/**
	 * 创建任务参数
	 * @param taskParameter		任务参数对象
	 * @return					任务参数对象
	 */
	TaskParameter createTaskParameter(TaskParameter taskParameter);

	/**
	 * 更新任务参数
	 * @param taskParameter		任务参数对象
	 * @return					任务参数对象
	 */
	TaskParameter updateTaskParameter(TaskParameter taskParameter);

	/**
	 * 根据任务参数ID得到任务步骤对象
	 * @param taskParameterId 	任务参数ID
	 * @return					任务参数对象
	 */
	TaskParameter findTaskParameterById(Long taskParameterId);
	
}