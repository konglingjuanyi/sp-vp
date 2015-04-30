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
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;

/**
 * 安防 任务持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-4-30 14:46:07
 * @version 0.1, 2015-4-30
 */
public interface ITaskDaoService extends BaseService<Task, Long> {

	/**
	 * 创建任务
	 * @param task	任务对象
	 * @return		任务对象
	 */
	Task createTask(Task task);

	/**
	 * 更新任务
	 * @param task	任务对象
	 * @return		任务对象
	 */
	Task updateTask(Task task);

	/**
	 * 根据Task ID得到任务对象
	 * @param taskId 	任务ID
	 * @return			任务对象
	 */
	Task findTaskById(Long taskId);
	
}