/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.IRvcService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程控制接口
 */
public interface IRvcService {

	/**
	 * 请求控制
	 *
	 * @param requestClient 请求终端
	 * @param userId        用户ID
	 * @param tbox          TBOX对象
	 * @param command       命令代码
	 * @param parameters    命令参数
	 * @param eventId       事件ID
	 * @return 控制命令ID
	 */
	ControlCommand requestControl(String requestClient, Long userId, Tbox tbox, String command,
			Map<String, Object> parameters, Long eventId) throws ServLayerException;

	/**
	 * 取消控制
	 *
	 * @param requestClient 请求终端
	 * @param userId        用户ID
	 * @param tbox          TBOX对象
	 * @param command       命令代码
	 */
	void cancelControl(String requestClient, Long userId, Tbox tbox, String command) throws ServLayerException;

	/**
	 * 更新控制请求状态
	 *
	 * @param tbox            TBOX对象
	 * @param rvcStatus       控制状态
	 * @param failureType     错误类型
	 * @param vehiclePos      车辆位置对象
	 * @param vehicleStatuses 车辆状态对象列表
	 * @param eventId         事件ID
	 */
	ControlCommand updateControlStatus(Tbox tbox, byte[] rvcStatus, Integer failureType, VehiclePos vehiclePos,
			List<VehicleStatus> vehicleStatuses, Long eventId) throws ServLayerException;

	/**
	 * 根据控制命令ID得到命令状态传输对象
	 *
	 * @param controlCommandId 控制命令ID
	 * @param userId           用户ID
	 * @param tbox             TBOX对象
	 * @return 控制命令状态对象
	 */
	ControlCommand getControlStatus(Long controlCommandId, Long userId, Tbox tbox) throws ServLayerException;

}
