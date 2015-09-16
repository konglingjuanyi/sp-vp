/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

import java.util.List;

/**
 * 安防服务 车辆信息数据访问接口
 */
public interface IVehicleInfoDao extends BaseService<VehicleInfo, Long> {

	/**
	 * 创建车辆信息快照
	 *
	 * @param vehicleInfo 车辆信息快照对象
	 * @return 车辆信息快照对象
	 */
	VehicleInfo createVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 更新车辆信息快照
	 *
	 * @param vehicleInfo 车辆信息快照对象
	 * @return 车辆信息快照对象
	 */
	VehicleInfo updateVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 写入车辆最新信息缓存
	 *
	 * @param vehicleInfo 车辆信息快照对象
	 * @return 车辆信息快照对象
	 */
	VehicleInfo writeVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 读取车辆最新信息缓存
	 *
	 * @param vin 车辆唯一码
	 * @return 车辆信息快照对象
	 */
	VehicleInfo readVehicleInfo(String vin);

	/**
	 * 根据主键得到车辆信息对象
	 *
	 * @param vehicleInfoId 主键
	 * @return 车辆信息对象
	 */
	VehicleInfo findVehicleInfoById(Long vehicleInfoId);

	/**
	 * 根据车辆唯一码（VIN）得到车辆最新信息
	 *
	 * @param vin 车辆唯一码
	 * @return 车辆信息对象
	 */
	VehicleInfo findLatestVehicleInfo(String vin);

	/**
	 * 根据事件ID得到车辆状态快照列表
	 *
	 * @param eventId 事件ID
	 * @return 车辆状态快照列表
	 */
	List<VehicleInfo> listVehicleInfoByEventId(Long eventId);

}