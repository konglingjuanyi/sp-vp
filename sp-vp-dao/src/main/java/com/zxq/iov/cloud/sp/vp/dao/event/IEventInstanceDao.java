/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.IEventInstanceDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;

import java.util.List;

/**
 * 安防服务 事件实例数据访问接口
 */
public interface IEventInstanceDao extends BaseService<EventInstance, Long> {

	/**
	 * 创建事件实例
	 *
	 * @param eventInstance 事件实例对象
	 * @return 事件实例对象
	 */
	EventInstance createEventInstance(EventInstance eventInstance);

	/**
	 * 更新事件实例
	 *
	 * @param eventInstance 事件实例对象
	 * @return 事件实例对象
	 */
	EventInstance updateEventInstance(EventInstance eventInstance);

	/**
	 * 根据主键删除事件实例
	 *
	 * @param eventInstanceId 事件实例主键
	 */
	void removeEventInstance(Long eventInstanceId);

	/**
	 * 根据主键得到事件实例对象
	 *
	 * @param eventInstanceId 事件实例主键
	 * @return 事件实例对象
	 */
	EventInstance findEventInstanceById(Long eventInstanceId);

	/**
	 * 根据事件定义ID得到事件实例列表
	 *
	 * @param eventDefinitionId 事件定义ID
	 * @param owner             拥有者
	 * @param status            状态
	 * @return 事件实例列表
	 */
	List<EventInstance> listEventInstanceByEventDefinitionId(Long eventDefinitionId, String owner, Integer status);

}