/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-22       荣杰         1.0            Initial Version
 * 2015-07-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.ITboxConfigApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigSettingDto;

import java.util.List;

/**
 * 安防服务 远程配置API
 */
public interface ITboxConfigApi {

	/**
	 * 请求TBOX检查配置更新
	 *
	 * @param vin 车辆唯一码
	 */
	void requestConfigUpdate(String vin) throws ApiException;

	/**
	 * TBOX响应是否接受后台的配置更新请求
	 *
	 * @param otaDto     OTA传输对象
	 * @param isAccepted 是否接受
	 */
	void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) throws ApiException;

	/**
	 * 检查TBOX配置更新版本，是否需要更新
	 *
	 * @param otaDto        OTA传输对象
	 * @param mcuVersion    MCU版本
	 * @param mpuVersion    MPU版本
	 * @param vin           车辆VIN
	 * @param iccid         ICCID
	 * @param configVersion 配置版本
	 * @param configDelta   TBOX上配置更新版本
	 * @return 配置信息
	 */
	TboxConfigDto checkConfigDelta(OtaDto otaDto, byte[] mcuVersion, byte[] mpuVersion, String vin, String iccid,
			byte[] configVersion, Integer configDelta) throws ApiException;

	/**
	 * 获得配置更新包
	 *
	 * @param otaDto    OTA传输对象
	 * @param packageId 包ID
	 * @return 配置更新包
	 */
	TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) throws ApiException;

	/**
	 * TBOX配置更新结束
	 *
	 * @param otaDto        OTA传输对象
	 * @param result        TBOX更新是否成功
	 * @param mcuVersion    MCU版本
	 * @param mpuVersion    MPU版本
	 * @param configVersion 配置版本
	 * @param configDelta   TBOX上配置更新版本
	 */
	void closeConfigUpdate(OtaDto otaDto, Boolean result, byte[] mcuVersion, byte[] mpuVersion, byte[] configVersion,
			Integer configDelta) throws ApiException;

	/**
	 * 请求读取TBOX配置参数
	 *
	 * @param vin                  车辆唯一码
	 * @param tboxConfigsettingIds TBOX配置参数ID列表
	 */
	void requestReadConfig(String vin, Long[] tboxConfigsettingIds) throws ApiException;

	/**
	 * 响应读取TBOX配置参数请求
	 *
	 * @param otaDto                OTA传输对象
	 * @param tboxConfigSettingDtos TBOX配置参数
	 */
	void responseReadConfig(OtaDto otaDto, List<TboxConfigSettingDto> tboxConfigSettingDtos) throws ApiException;

	/**
	 * 生成非对称密钥，用以对TBOX密钥加解密
	 *
	 * @param otaDto OTA传输对象
	 * @return 密钥传输对象
	 */
	KeyDto generateAsymmetricKey(OtaDto otaDto) throws ApiException;

	/**
	 * 将TBOX密钥与TBOX ID绑定
	 *
	 * @param otaDto           OTA传输对象
	 * @param secretKeyWithEnc 加密过的TBOX密钥
	 * @param tboxSnWithEnc    加密过的TBOX序列号
	 * @return 密钥传输对象
	 */
	KeyDto bindTboxWithSecretKey(OtaDto otaDto, byte[] secretKeyWithEnc, byte[] tboxSnWithEnc) throws ApiException;

}
