/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-12       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.IEcallService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 eCall服务接口
 */
public interface IEcallService {

	/**
	 * 开始eCall
	 *
	 * @param tbox                 TBOX对象
	 * @param vehiclePoses         车辆位置对象列表
	 * @param ecallType            呼叫方式
	 * @param crashSeverity        严重程度
	 * @param tboxBatteryStatus    TBOX电池状态
	 * @param vehicleBatteryStatus 车辆电池状态
	 * @param ecallTime            eCall发生时间
	 * @return eCall通话对象
	 */
	CallRecord start(Tbox tbox, List<VehiclePos> vehiclePoses, Integer ecallType, Integer crashSeverity,
			Integer tboxBatteryStatus, Integer vehicleBatteryStatus, Date ecallTime) throws ServLayerException;

	/**
	 * 更新eCall状态
	 *
	 * @param tbox                 TBOX对象
	 * @param vehiclePoses         车辆位置对象列表
	 * @param ecallType            呼叫方式
	 * @param crashSeverity        严重程度
	 * @param tboxBatteryStatus    TBOX电池状态
	 * @param vehicleBatteryStatus 车辆电池状态
	 * @param ecallTime            eCall发生时间
	 * @return 呼叫对象
	 */
	Call update(Tbox tbox, List<VehiclePos> vehiclePoses, Integer ecallType, Integer crashSeverity,
			Integer tboxBatteryStatus, Integer vehicleBatteryStatus, Date ecallTime) throws ServLayerException;

	/**
	 * 挂断eCall通话
	 *
	 * @param vin 车辆唯一码
	 */
	void hangUp(String vin) throws ServLayerException;

	/**
	 * 车辆回拨
	 *
	 * @param vin        车辆唯一码
	 * @param callNumber 呼叫号码
	 * @return eCall通话对象
	 */
	CallRecord callBack(String vin, String callNumber) throws ServLayerException;

	/**
	 * 响应车辆回拨
	 *
	 * @param tboxId           TBOX ID
	 * @param callbackAccepted 是否接受回拨
	 * @param rejectReason     拒绝理由
	 */
	void responseCallBack(Long tboxId, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException;

	/**
	 * 结束eCall
	 *
	 * @param vin 车辆唯一码
	 */
	void close(String vin) throws ServLayerException;

	/**
	 * 结束eCall
	 *
	 * @param tboxId TBOX ID
	 */
	void close(Long tboxId) throws ServLayerException;
}
