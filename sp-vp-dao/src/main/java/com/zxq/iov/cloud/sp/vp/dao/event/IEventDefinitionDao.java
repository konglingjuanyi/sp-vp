/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.IEventDefinitionDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;

import java.util.Map;

/**
 * 安防 事件定义数据访问接口
 */
public interface IEventDefinitionDao extends BaseService<EventDefinition, Long> {

	/**
	 * 创建事件定义
	 *
	 * @param eventDefinition 事件定义对象
	 * @return 事件定义对象
	 */
	EventDefinition createEventDefinition(EventDefinition eventDefinition);

	/**
	 * 更新事件定义
	 *
	 * @param eventDefinition 事件定义对象
	 * @return 事件定义对象
	 */
	EventDefinition updateEventDefinition(EventDefinition eventDefinition);

	/**
	 * 根据主键删除事件定义
	 *
	 * @param eventDefinitionId 事件定义主键
	 */
	void removeEventDefinition(Long eventDefinitionId);

	/**
	 * 根据主键得到事件定义对象
	 *
	 * @param eventDefinitionId 事件定义主键
	 * @return 事件定义对象
	 */
	EventDefinition findEventDefinitionById(Long eventDefinitionId);

	/**
	 * 根据参数MAP得到分页结果
	 *
	 * @param pageResult 分页
	 * @param paramMap   参数MAP
	 * @return 分页结果
	 */
	PageResult<EventDefinition> pagingEventDefinition(PageResult<EventDefinition> pageResult,
			Map<String, Object> paramMap);

}