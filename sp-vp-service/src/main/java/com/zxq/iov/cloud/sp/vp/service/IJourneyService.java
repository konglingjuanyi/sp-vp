/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-09       荣杰         1.0            Initial Version
 * 2015-08-07       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.IJourneyService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.Date;

/**
 * 安防服务 车辆行程接口
 */
public interface IJourneyService {

	/**
	 * 开始车辆行程
	 *
	 * @param tbox          TBOX对象
	 * @param startTime     行程开始时间
	 * @param tboxJourneyId TBOX行程ID
	 * @param keyId         钥匙ID
	 */
	void start(Tbox tbox, Date startTime, Integer tboxJourneyId, Integer keyId) throws ServLayerException;

	/**
	 * 更新车辆行程状态
	 *
	 * @param tbox                TBOX对象
	 * @param tboxJourneyId       TBOX行程ID
	 * @param instFuelConsumption 瞬时油耗
	 * @param vehiclePos          车辆位置对象
	 */
	void update(Tbox tbox, Integer tboxJourneyId, Integer instFuelConsumption, VehiclePos vehiclePos)
			throws ServLayerException;

	/**
	 * 结束车辆行程
	 *
	 * @param tbox            TBOX对象
	 * @param startVehiclePos 开始车辆位置对象
	 * @param endVehiclePos   结束车辆位置对象
	 * @param tboxJourneyId   TBOX行程ID
	 * @param distance        行驶距离
	 * @param avgSpeed        平均速度
	 * @param fuelEco         平均油耗
	 * @param odometer        流程表数据
	 * @param fuelLevelPrc    燃油剩余量
	 * @param fuelLevelDisp   剩余燃油刻度
	 * @param fuelRange       剩余燃油可行驶距离
	 */
	void end(Tbox tbox, VehiclePos startVehiclePos, VehiclePos endVehiclePos, Integer tboxJourneyId, Integer distance,
			Integer avgSpeed, Integer fuelEco, Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp,
			Integer fuelRange) throws ServLayerException;

	/**
	 * 根据TBOX ID和TBOX行程ID得到行程对象
	 *
	 * @param tboxJourneyId TBOX行程ID
	 * @param tboxId        TBOX ID
	 * @return 行程对象
	 * @throws ServLayerException
	 */
	Journey getByIdAndTboxJourneyId(Integer tboxJourneyId, Long tboxId) throws ServLayerException;

}
