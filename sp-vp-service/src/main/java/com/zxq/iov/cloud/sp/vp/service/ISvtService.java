/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.ISvtService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 被盗追踪接口
 */
public interface ISvtService {

	/**
	 * 车辆报警
	 *
	 * @param tbox        TBOX对象
	 * @param stolenAlarm 被盗警报对象
	 * @param vehiclePos  车辆位置对象
	 * @param eventId     事件ID
	 */
	void alarm(Tbox tbox, StolenAlarm stolenAlarm, VehiclePos vehiclePos, Long eventId) throws ServLayerException;

	/**
	 * 更新追踪点
	 *
	 * @param tbox            TBOX对象
	 * @param vehicleStatuses 车辆状态对象列表
	 * @param vehiclePos      车辆位置对象
	 * @param eventId         事件ID
	 */
	void updateTrack(Tbox tbox, List<VehicleStatus> vehicleStatuses, VehiclePos vehiclePos, Long eventId)
			throws ServLayerException;

	/**
	 * 根据特殊设置的条件组成map，查找符合条件的被盗报警对象
	 * @return
	 */
	List<StolenAlarm> findStolenAlarmByMap(Map<String,Object> map);
}
