/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-08       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.ITaskInstanceDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;

import java.util.List;

/**
 * 安防服务 任务实例数据访问接口
 */
public interface ITaskInstanceDao extends BaseService<TaskInstance, Long> {

	/**
	 * 创建任务实例
	 *
	 * @param taskInstance 任务实例对象
	 * @return 任务实例对象
	 */
	TaskInstance createTaskInstance(TaskInstance taskInstance);

	/**
	 * 更新任务实例
	 *
	 * @param taskInstance 任务实例对象
	 * @return 任务实例对象
	 */
	TaskInstance updateTaskInstance(TaskInstance taskInstance);

	/**
	 * 根据主键删除任务实例
	 *
	 * @param taskInstanceId 任务实例主键
	 */
	void removeTaskInstance(Long taskInstanceId);

	/**
	 * 根据主键得到任务实例对象
	 *
	 * @param taskInstanceId 任务实例主键
	 * @return 任务实例对象
	 */
	TaskInstance findTaskInstanceById(Long taskInstanceId);

	/**
	 * 根据事件实例ID得到任务实例列表
	 *
	 * @param eventInstanceId  事件实例ID
	 * @param taskDefinitionId 任务定义ID
	 * @param status           状态
	 * @return 任务实例列表
	 */
	List<TaskInstance> listTaskInstanceByEventInstanceId(Long eventInstanceId, Long taskDefinitionId, Integer status);

}