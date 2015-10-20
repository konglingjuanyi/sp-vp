/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2            增加手机端相关接口
 *
 * com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 电子钥匙接口
 */
public interface IRemoteKeyService {

	/**
	 * 创建钥匙
	 *
	 * @param userId 用户ID
	 * @param tboxId TBOX ID
	 * @param vin    车辆唯一码
	 * @return 钥匙对象
	 * @throws ServLayerException
	 */
	RemoteKey createKey(Long userId, Long tboxId, String vin) throws ServLayerException;

	/**
	 * 授权钥匙
	 *
	 * @param remoteKey 智能钥匙对象
	 * @return 钥匙对象
	 * @throws ServLayerException
	 */
	RemoteKey grantKey(RemoteKey remoteKey) throws ServLayerException;

	/**
	 * 修改钥匙
	 *
	 * @param keyReference 钥匙ID
	 * @param startTime    有效开始时间
	 * @param endTime      有效结束时间
	 * @param privilege    权限
	 * @throws ServLayerException
	 */
	RemoteKey updateKey(Long keyReference, Date startTime, Date endTime, Integer privilege) throws ServLayerException;

	/**
	 * 禁用钥匙
	 *
	 * @param keyReference 钥匙ID
	 * @return 钥匙对象
	 * @throws ServLayerException
	 */
	RemoteKey disableKey(Long keyReference) throws ServLayerException;

	/**
	 * 启用钥匙
	 *
	 * @param keyReference 钥匙ID
	 * @return 钥匙对象
	 * @throws ServLayerException
	 */
	RemoteKey enableKey(Long keyReference) throws ServLayerException;

	/**
	 * 删除钥匙
	 *
	 * @param keyReference 钥匙ID
	 * @throws ServLayerException
	 */
	void removeKey(Long keyReference) throws ServLayerException;

	/**
	 * 响应写入钥匙请求
	 *
	 * @param tboxId             TBOX ID
	 * @param writeSuccess       是否写入成功
	 * @param writeFailureReason 写入失败原因
	 */
	void responseWriteKey(Long tboxId, Boolean writeSuccess, Integer writeFailureReason) throws ServLayerException;

	/**
	 * 响应删除钥匙请求
	 *
	 * @param tboxId              TBOX ID
	 * @param deleteSuccess       是否删除成功
	 * @param deleteFailureReason 删除失败原因
	 */
	void responseDeleteKey(Long tboxId, Boolean deleteSuccess, Integer deleteFailureReason) throws ServLayerException;

	/**
	 * 钥匙异常报警
	 *
	 * @param tboxId OTA传输对象
	 */
	void keyAlarm(Long tboxId) throws ServLayerException;

	/**
	 * 根据ID得到钥匙对象
	 *
	 * @param reference 钥匙ID
	 * @return 钥匙对象
	 */
	RemoteKey findKeyByReference(Long reference);

	/**
	 * 获得用户钥匙列表
	 *
	 * @param userId 用户ID
	 * @return 钥匙对象列表
	 */
	List<RemoteKey> listUserKey(Long userId);

	/**
	 * 根据车辆钥匙列表
	 *
	 * @param vin 车辆唯一码
	 * @return 钥匙对象列表
	 */
	List<RemoteKey> listVehicleKey(String vin);

}
