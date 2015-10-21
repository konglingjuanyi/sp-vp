/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-08-12       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.IRemoteKeyApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 智能钥匙API
 */
public interface IRemoteKeyApi {

	/**
	 * 创建钥匙
	 * 一般在车主绑定车辆时同时创建该车辆的智能钥匙
	 *
	 * @param ownerId 车主ID
	 * @param vin     车辆唯一码
	 */
	void createKey(Long ownerId, String vin) throws ApiException;

	/**
	 * 授权钥匙
	 *
	 * @param ownerId   车主ID
	 * @param vin       车辆唯一码
	 * @param mobile    被授权用户手机
	 * @param startTime 钥匙有效开始时间
	 * @param endTime   钥匙有效结束时间
	 * @param privilege 钥匙权限
	 * @throws ApiException
	 */
	void grantKey(Long ownerId, String vin, String mobile, Date startTime, Date endTime, Integer privilege)
			throws ApiException;

	/**
	 * 修改钥匙
	 *
	 * @param ownerId      车主ID
	 * @param keyReference 钥匙ID
	 * @param startTime    钥匙有效开始时间
	 * @param endTime      钥匙有效结束时间
	 * @param privilege    钥匙权限
	 */
	void updateKey(Long ownerId, Long keyReference, Date startTime, Date endTime, Integer privilege)
			throws ApiException;

	/**
	 * 禁用钥匙
	 *
	 * @param ownerId      车主ID
	 * @param keyReference 钥匙ID
	 */
	void disableKey(Long ownerId, Long keyReference) throws ApiException;

	/**
	 * 启用钥匙
	 *
	 * @param ownerId      车主ID
	 * @param keyReference 钥匙ID
	 */
	void enableKey(Long ownerId, Long keyReference) throws ApiException;

	/**
	 * 删除钥匙
	 *
	 * @param ownerId      车主ID
	 * @param keyReference 钥匙ID
	 */
	void removeKey(Long ownerId, Long keyReference) throws ApiException;

	/**
	 * 响应写入钥匙请求
	 *
	 * @param otaDto             OTA传输对象
	 * @param writeSuccess       是否写入成功
	 * @param writeFailureReason 写入失败原因
	 */
	void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason) throws ApiException;

	/**
	 * 响应删除钥匙请求
	 *
	 * @param otaDto              OTA传输对象
	 * @param deleteSuccess       是否删除成功
	 * @param deleteFailureReason 删除失败原因
	 */
	void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess, Integer deleteFailureReason) throws ApiException;

	/**
	 * 钥匙异常报警
	 *
	 * @param otaDto OTA传输对象
	 */
	void keyAlarm(OtaDto otaDto) throws ApiException;

	/**
	 * 获得用户钥匙列表
	 *
	 * @param userId 用户ID
	 * @return 蓝牙钥匙列表
	 */
	List<RemoteKeyDto> listUserKey(Long userId) throws ApiException;

	/**
	 * 获得指定车辆上的钥匙列表
	 *
	 * @param ownerId 车主ID
	 * @param vin     车辆唯一码
	 * @return 蓝牙钥匙列表
	 */
	List<RemoteKeyDto> listVehicleKey(Long ownerId, String vin) throws ApiException;

}
