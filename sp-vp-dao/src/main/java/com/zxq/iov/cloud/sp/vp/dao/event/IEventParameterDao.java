/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-08       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.IEventParameterDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;

/**
 * 安防服务 事件参数数据访问接口
 */
public interface IEventParameterDao extends BaseService<EventParameter, Long> {

	/**
	 * 创建事件参数
	 *
	 * @param eventParameter 事件参数对象
	 * @return 事件参数对象
	 */
	EventParameter createEventParameter(EventParameter eventParameter);

	/**
	 * 更新事件参数
	 *
	 * @param eventParameter 事件参数对象
	 * @return 事件参数对象
	 */
	EventParameter updateEventParameter(EventParameter eventParameter);

	/**
	 * 根据主键删除事件参数
	 *
	 * @param eventParameterId 事件参数主键
	 */
	void removeEventParameter(Long eventParameterId);

	/**
	 * 根据主键得到事件参数对象
	 *
	 * @param eventParameterId 事件参数主键
	 * @return 事件参数对象
	 */
	EventParameter findEventParameterById(Long eventParameterId);

	/**
	 * 根据类型、步骤实例ID、名称得到事件参数对象
	 *
	 * @param type           类型
	 * @param stepInstanceId 步骤实例ID
	 * @param name           名称
	 * @return 事件参数对象
	 */
	EventParameter findEventParameterByTypeAndStepIdAndName(Integer type, Long stepInstanceId, String name);

}