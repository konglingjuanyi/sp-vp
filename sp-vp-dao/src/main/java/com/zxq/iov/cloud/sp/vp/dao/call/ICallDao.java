/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 * 2015-08-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.call.ICallDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;

import java.util.List;

/**
 * 安防 呼叫数据访问接口
 */
public interface ICallDao extends BaseService<Call, Long> {

	/**
	 * 创建呼叫
	 *
	 * @param call 呼叫对象
	 * @return 呼叫对象
	 */
	Call createCall(Call call);

	/**
	 * 更新呼叫
	 *
	 * @param call 呼叫对象
	 * @return 呼叫对象
	 */
	Call updateCall(Call call);

	/**
	 * 删除呼叫
	 *
	 * @param callId 呼叫主键
	 */
	void removeCall(Long callId);

	/**
	 * 根据主键得到呼叫对象
	 *
	 * @param callId 呼叫主键
	 * @return 呼叫对象
	 */
	Call findCallById(Long callId);

	/**
	 * 根据TBOX ID得到呼叫对象列表
	 *
	 * @param tboxId TBOX ID
	 * @param status 状态
	 * @return 呼叫对象列表
	 */
	List<Call> listCallByTboxId(Long tboxId, Integer status);

	/**
	 * 根据车辆唯一码得到呼叫对象列表
	 *
	 * @param vin    车辆唯一码
	 * @param status 状态
	 * @return 呼叫对象列表
	 */
	List<Call> listCallByVin(String vin, Integer status);

	/**
	 * 根据车辆唯一码得到呼叫对象列表
	 *
	 * @param vin    车辆唯一码
	 * @param type   类型
	 * @param status 状态
	 * @return 呼叫对象列表
	 */
	List<Call> listCallByVinAndType(String vin, Integer type, Integer status);

}