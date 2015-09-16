/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-07-30       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.IRvcApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程控制API
 */
public interface IRvcApi {

	/**
	 * 请求控制
	 *
	 * @param requestClient 请求终端
	 * @param userId        用户ID
	 * @param vin           车辆唯一码
	 * @param command       命令代码
	 * @param parameters    命令参数
	 * @return 控制命令ID
	 */
	Long requestControl(String requestClient, Long userId, String vin, String command, Map<String, Object> parameters)
			throws ApiException;

	/**
	 * 取消控制
	 *
	 * @param requestClient 请求终端
	 * @param userId        用户ID
	 * @param vin           车辆唯一码
	 * @param command       命令代码
	 */
	void cancelControl(String requestClient, Long userId, String vin, String command) throws ApiException;

	/**
	 * 更新控制请求状态
	 *
	 * @param otaDto            OTA传输对象
	 * @param rvcStatus         控制状态
	 * @param failureType       错误类型
	 * @param vehiclePosDto     车辆位置传输对象
	 * @param vehicleStatusDtos 车辆状态传输对象列表
	 */
	void updateControlStatus(OtaDto otaDto, byte[] rvcStatus, Integer failureType, VehiclePosDto vehiclePosDto,
			List<VehicleStatusDto> vehicleStatusDtos) throws ApiException;

	/**
	 * 根据控制命令ID得到命令状态传输对象
	 *
	 * @param controlCommandId 控制命令ID
	 * @param vin              车辆唯一码
	 * @param userId           用户ID
	 * @return 控制命令状态传输对象
	 */
	RvcStatusDto getControlStatus(Long controlCommandId, String vin, Long userId) throws ApiException;

}
