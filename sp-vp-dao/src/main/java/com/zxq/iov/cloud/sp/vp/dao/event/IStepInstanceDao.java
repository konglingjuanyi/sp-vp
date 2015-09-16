/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-15       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.IStepInstanceDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;

import java.util.List;

/**
 * 安防服务 步骤实例数据访问接口
 */
public interface IStepInstanceDao extends BaseService<StepInstance, Long> {

	/**
	 * 创建步骤实例
	 *
	 * @param stepInstance 步骤实例对象
	 * @return 步骤实例对象
	 */
	StepInstance createStepInstance(StepInstance stepInstance);

	/**
	 * 更新步骤实例
	 *
	 * @param stepInstance 步骤实例对象
	 * @return 步骤实例对象
	 */
	StepInstance updateStepInstance(StepInstance stepInstance);

	/**
	 * 根据主键删除步骤实例
	 *
	 * @param stepIntanceId 步骤实例主键
	 */
	void removeStepInstance(Long stepIntanceId);

	/**
	 * 根据主键得到步骤实例对象
	 *
	 * @param stepInstanceId 步骤实例主键
	 * @return 步骤实例对象
	 */
	StepInstance findStepInstanceById(Long stepInstanceId);

	/**
	 * 根据任务实例ID得到步骤实例列表
	 *
	 * @param taskInstanceId 任务实例ID
	 * @return 步骤实例列表走
	 */
	List<StepInstance> listStepInstanceByTaskInstanceId(Long taskInstanceId);

	/**
	 * 得到指定事件实例下的步骤实例列表
	 *
	 * @param eventInstanceId  事件实例ID
	 * @param stepDefinitionId 步骤定义ID
	 * @param status           状态
	 * @return 步骤实例列表
	 */
	List<StepInstance> listStepInstanceByEventInstanceId(Long eventInstanceId, Long stepDefinitionId, Integer status);

	/**
	 * 得到指定拥有者的步骤实例列表
	 *
	 * @param owner            拥有者
	 * @param stepDefinitionId 步骤定义ID
	 * @param status           状态
	 * @return 步骤实例列表
	 */
	List<StepInstance> listStepInstanceByOwner(String owner, Long stepDefinitionId, Integer status);

}