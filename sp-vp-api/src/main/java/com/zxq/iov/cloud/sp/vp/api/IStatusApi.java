/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.IStatusApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态API
 */
public interface IStatusApi {

	/**
	 * 请求车辆状态信息
	 *
	 * @param vin        车辆唯一码
	 * @param statusType 请求车辆状态类别
	 * @return 事件ID
	 */
	Long requestVehicleStatus(String vin, Integer statusType) throws ApiException;

	/**
	 * TBOX响应车辆状态请求
	 *
	 * @param otaDto            OTA传输对象
	 * @param statusTime        状态时间
	 * @param vehiclePosDto     车辆位置传输对象
	 * @param vehicleStatusDtos 车辆状态传输对象列表
	 * @param vehicleAlertDtos  车辆报警传输对象列表
	 */
	void responseVehicleStatus(OtaDto otaDto, Date statusTime, VehiclePosDto vehiclePosDto,
			List<VehicleStatusDto> vehicleStatusDtos, List<VehicleAlertDto> vehicleAlertDtos) throws ApiException;

	/**
	 * 得到车辆状态信息快照
	 * 存在事件ID，则返回事件ID对应的状态快照，不存在则返回最新状态
	 *
	 * @param vin     车辆唯一码
	 * @param eventId 事件ID
	 * @return 车辆状态快照传输对象
	 */
	VehicleInfoDto getVehicleStatus(String vin, Long eventId) throws ApiException;

	/**
	 * 记录车辆警告信息
	 *
	 * @param otaDto           OTA传输对象
	 * @param vehicleAlertDtos 车辆报警信息传输对象列表
	 */
	void logVehicleAlert(OtaDto otaDto, List<VehicleAlertDto> vehicleAlertDtos) throws ApiException;

}
