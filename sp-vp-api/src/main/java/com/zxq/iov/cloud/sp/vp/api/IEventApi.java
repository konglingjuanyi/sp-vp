/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-29       荣杰         1.0            Initial Version
 * 2015-08-07       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.IEventApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;

/**
 * 安防服务 事件API
 */
public interface IEventApi {

	/**
	 * Dispatch超时
	 *
	 * @param stepInstanceId 步骤实例ID
	 */
	void dispatchTimeout(Long stepInstanceId) throws ApiException;

	/**
	 * Dispatch确认
	 *
	 * @param tboxId         TBOX ID
	 * @param stepInstanceId 步骤实例ID
	 */
	void dispatchAck(Long tboxId, Long stepInstanceId) throws ApiException;

	/**
	 * 检查超时步骤、任务及事件
	 */
	void checkTimeout() throws ApiException;
}
