/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 * 2015-06-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 任务定义数据访问接口
 */
public interface ITaskDefinitionDao extends BaseService<TaskDefinition, Long> {

	/**
	 * 创建任务定义
	 *
	 * @param taskDefinition 任务定义对象
	 * @return 任务定义对象
	 */
	TaskDefinition createTaskDefinition(TaskDefinition taskDefinition);

	/**
	 * 更新任务定义
	 *
	 * @param taskDefinition 任务定义对象
	 * @return 任务定义对象
	 */
	TaskDefinition updateTaskDefinition(TaskDefinition taskDefinition);

	/**
	 * 根据主键删除任务定义
	 *
	 * @param taskDefinitionId 任务定义主键
	 */
	void removeTaskDefinition(Long taskDefinitionId);

	/**
	 * 根据主键得到任务定义对象
	 *
	 * @param taskDefinitionId 任务定义主键
	 * @return 任务定义对象
	 */
	TaskDefinition findTaskDefinitionById(Long taskDefinitionId);

	/**
	 * 根据事件定义ID得到任务定义列表
	 *
	 * @param eventDefinitionId 事件定义ID
	 * @return 任务定义列表
	 */
	List<TaskDefinition> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId);

	/**
	 * 根据参数MAP得到分页结果
	 *
	 * @param pageResult 分页
	 * @param paramMap   参数MAP
	 * @return 分页结果
	 */
	PageResult<TaskDefinition> pagingTaskDefinition(PageResult<TaskDefinition> pageResult,
			Map<String, Object> paramMap);

}