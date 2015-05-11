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
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;

/**
 * 安防 事件参数持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-4 9:39:07
 * modify date 2015-5-11 10:26
 * @version 0.3, 2015-5-11
 */
public interface IEventParameterDaoService extends BaseService<EventParameter, Long> {

	/**
	 * 创建事件参数
	 * @param eventParameter	事件参数对象
	 * @return					事件参数对象
	 */
	EventParameter createEventParameter(EventParameter eventParameter);

	/**
	 * 更新事件参数
	 * @param eventParameter	事件参数对象
	 * @return					事件参数对象
	 */
	EventParameter updateEventParameter(EventParameter eventParameter);

	/**
	 * 根据事件参数ID得到事件参数对象
	 * @param eventParameterId 	事件参数ID
	 * @return					事件参数对象
	 */
	EventParameter findEventParameterById(Long eventParameterId);

	/**
	 * 根据参数名称和相关ID得到事件参数对象
	 * @param name     			参数名称
	 * @param eventId	        事件ID
	 * @param taskId           	任务ID
	 * @param stepId        	步骤ID
	 * @return       			事件参数对象
	 */
	EventParameter findEventParameterByName(String name, Long eventId, Long taskId, Long stepId);
	
}