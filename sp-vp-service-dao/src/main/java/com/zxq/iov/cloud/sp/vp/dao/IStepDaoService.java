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
import com.zxq.iov.cloud.sp.vp.entity.event.Step;

/**
 * 安防 步骤持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-4-30 15:48:07
 * modify date 2015-5-8 15:31
 * @version 0.2, 2015-5-8
 */
public interface IStepDaoService extends BaseService<Step, Long> {

	/**
	 * 创建任务步骤
	 * @param step		任务步骤对象
	 * @return			任务步骤对象
	 */
	Step createStep (Step step);

	/**
	 * 更新任务步骤
	 * @param step		任务步骤对象
	 * @return			任务步骤对象
	 */
	Step updateStep(Step step);

	/**
	 * 根据Task ID得到任务步骤对象
	 * @param stepId 	任务步骤ID
	 * @return			任务步骤对象
	 */
	Step findStepById(Long stepId);
	
}