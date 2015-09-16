/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-05-14       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.List;

/**
 * 安防服务 车辆状态信息数据访问接口
 */
public interface IVehicleStatusDao extends BaseService<VehicleStatus, Long> {

	/**
	 * 创建车辆状态信息
	 *
	 * @param vehicleStatus 车辆状态信息对象
	 * @return 车辆状态信息对象
	 */
	VehicleStatus createVehicleStatus(VehicleStatus vehicleStatus);

	/**
	 * 更新车辆状态信息
	 *
	 * @param vehicleStatus 车辆状态信息对象
	 * @return 车辆状态信息对象
	 */
	VehicleStatus updateVehicleStatus(VehicleStatus vehicleStatus);

	/**
	 * 根据主键得到车辆状态信息对象
	 *
	 * @param vehicleStatusId 主键
	 * @return 车辆状态信息对象
	 */
	VehicleStatus findVehicleStatusById(Long vehicleStatusId);

	/**
	 * 根据车辆唯一码（VIN）得到车辆状态信息列表
	 *
	 * @param vin  车辆唯一码
	 * @param type 状态类型
	 * @return 车辆状态信息列表
	 */
	List<VehicleStatus> findLatestVehicleStatus(String vin, Integer type);

	/**
	 * 根据车辆信息ID得到车辆位置信息列表
	 *
	 * @param vehicleInfoId 车辆信息ID
	 * @param type          状态类型，0状态信息，1报警信息
	 * @return 车辆状态信息列表
	 */
	List<VehicleStatus> findVehicleStatusByVehicleInfoId(Long vehicleInfoId, Integer type);
}