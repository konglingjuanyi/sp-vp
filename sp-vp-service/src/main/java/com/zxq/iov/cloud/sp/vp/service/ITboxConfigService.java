/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-22       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.ITboxConfigService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;

import java.security.interfaces.RSAPrivateKey;
import java.util.List;

/**
 * 安防服务 远程配置接口
 */
public interface ITboxConfigService {

	/**
	 * 检查TBOX配置更新版本，是否需要更新
	 *
	 * @param tboxId        TBOX ID
	 * @param mcuVersion    MCU版本
	 * @param mpuVersion    MPU版本
	 * @param vin           车辆VIN
	 * @param iccid         ICCID
	 * @param configVersion 配置版本
	 * @param configDelta   TBOX上配置更新版本
	 * @param eventId       事件ID
	 * @return 最新配置更新版本
	 */
	Integer checkConfigDelta(Long tboxId, byte[] mcuVersion, byte[] mpuVersion, String vin, String iccid,
			byte[] configVersion, Integer configDelta, Long eventId) throws ServLayerException;

	/**
	 * 获得配置更新包
	 *
	 * @param tboxId    TBOX ID
	 * @param packageId 包ID
	 * @param eventId   事件ID
	 * @return 配置参数对象列表
	 */
	List<TboxConfigSetting> getConfigPackage(Long tboxId, Integer packageId, Long eventId) throws ServLayerException;

	/**
	 * 生成非对称密钥，用以对TBOX密钥加解密
	 *
	 * @param tboxId TBOX ID
	 * @return 公钥模
	 */
	byte[] generateAsymmetricKey(Long tboxId) throws ServLayerException;

	/**
	 * 得到指定tbox的privateKey
	 *
	 * @param tboxId TBOX ID
	 * @return 密钥对象
	 */
	RSAPrivateKey getPrivateKey(Long tboxId) throws ServLayerException;

	/**
	 * 更新密钥
	 * @param tboxId TBOX ID
	 * @param secretKey 密钥
	 */
	void updateSecretKey(Long tboxId, String secretKey);

}
