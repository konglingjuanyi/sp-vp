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

/**
 * 安防 事件持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-4-29 14:57:07
 * @version 0.1, 2015-4-29
 */
public interface IEventDaoService extends BaseService<Event, Long> {

	/**
	 * 创建事件
	 * @param event	事件对象
	 * @return		事件对象
	 */
	Event createEvent(Event event);

	/**
	 * 更新事件
	 * @param event	事件对象
	 * @return		事件对象
	 */
	Event updateEvent(Event event);

	/**
	 * 根据Event ID得到事件对象
	 * @param eventId 	事件ID
	 * @return			事件对象
	 */
	Event findEventById(Long eventId);
	
}