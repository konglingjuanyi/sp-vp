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
 * com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.Date;

/**
 * 安防服务 电子钥匙接口
 */
public interface IRemoteKeyService {

	/**
	 * 请求写入钥匙
	 *
	 * @param tbox                 TBOX对象
	 * @param keyType              钥匙类型
	 * @param keyValue             钥匙
	 * @param keyReference         钥匙引用
	 * @param keyValidityStartTime 有效开始时间
	 * @param keyValidityEndTime   有效结束时间
	 */
	void requestWriteKey(Tbox tbox, Integer keyType, String keyValue, Long keyReference, Date keyValidityStartTime,
			Date keyValidityEndTime) throws ServLayerException;

	/**
	 * 响应写入钥匙请求
	 *
	 * @param tboxId             TBOX ID
	 * @param writeSuccess       是否写入成功
	 * @param writeFailureReason 写入失败原因
	 */
	void responseWriteKey(Long tboxId, Boolean writeSuccess, Integer writeFailureReason) throws ServLayerException;

	/**
	 * 请求删除钥匙
	 *
	 * @param vin          OTA传输对象
	 * @param keyReference 钥匙引用
	 */
	void requestDeleteKey(String vin, Long keyReference) throws ServLayerException;

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

}
