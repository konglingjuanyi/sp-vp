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
 * com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

/**
 * 安防服务 车辆位置信息数据访问接口
 */
public interface IVehiclePosDao extends BaseService<VehiclePos, Long> {

	/**
	 * 创建车辆位置信息
	 *
	 * @param vehiclePos 车辆位置信息对象
	 * @return 车辆位置信息对象
	 */
	VehiclePos createVehiclePos(VehiclePos vehiclePos);

	/**
	 * 更新车辆位置信息
	 *
	 * @param vehiclePos 车辆位置信息对象
	 * @return 车辆位置信息对象
	 */
	VehiclePos updateVehiclePos(VehiclePos vehiclePos);

	/**
	 * 根据主键得到车辆位置信息对象
	 *
	 * @param vehiclePosId 主键
	 * @return 车辆位置信息对象
	 */
	VehiclePos findVehiclePosById(Long vehiclePosId);

	/**
	 * 根据车辆唯一码（VIN）得到车辆最新位置信息
	 *
	 * @param vin 车辆唯一码
	 * @return 车辆位置信息对象
	 */
	VehiclePos findLatestVehiclePos(String vin);

	/**
	 * 根据车辆信息ID得到车辆位置信息
	 *
	 * @param vehicleInfoId 车辆信息ID
	 * @return 车辆位置信息对象
	 */
	VehiclePos findVehiclePosByVehicleInfoId(Long vehicleInfoId);

}