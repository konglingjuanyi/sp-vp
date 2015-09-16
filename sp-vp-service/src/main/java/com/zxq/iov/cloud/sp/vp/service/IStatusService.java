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
 * com.zxq.iov.cloud.sp.vp.service.IStatusService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态接口
 */
public interface IStatusService {

	/**
	 * 得到车辆信息快照
	 * 存在事件ID，则返回事件ID对应的状态快照，不存在则返回最新状态
	 *
	 * @param vin     车辆唯一码
	 * @param eventId 事件ID
	 * @return 车辆状态快照传输对象
	 */
	VehicleInfo getVehicleInfo(String vin, Long eventId) throws ServLayerException;

	/**
	 * 记录车辆信息快照
	 *
	 * @param tbox            TBOX对象
	 * @param sourceType      来源类型
	 * @param sourceId        来源ID
	 * @param vehiclePos      车辆位置对象
	 * @param vehicleStatuses 车辆状态对象列表
	 * @param vehicleAlerts   车辆报警对象列表
	 * @param statusTime      快照时间
	 * @param eventId         事件ID
	 * @return 车辆信息快照ID
	 */
	VehicleInfo logVehicleInfo(Tbox tbox, Integer sourceType, Long sourceId, VehiclePos vehiclePos,
			List<VehicleStatus> vehicleStatuses, List<VehicleStatus> vehicleAlerts, Date statusTime, Long eventId)
			throws ServLayerException;

	/**
	 * 记录车辆警告信息
	 *
	 * @param tbox         TBOX对象
	 * @param alertTime    报警时间
	 * @param vehiclePos   车辆位置信息对象
	 * @param vehicleAlert 车辆报警信息对象
	 * @return 车辆信息快照ID
	 */
	VehicleInfo logVehicleAlert(Tbox tbox, Date alertTime, VehiclePos vehiclePos, VehicleStatus vehicleAlert)
			throws ServLayerException;

}
