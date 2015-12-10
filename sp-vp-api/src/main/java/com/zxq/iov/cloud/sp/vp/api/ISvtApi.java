/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       叶荣杰       1.0            Initial Version
 * 2015-12-10       叶荣杰       1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.ISvtApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ProtectStrategySettingDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.TrackDto;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 被盗追踪API
 */
public interface ISvtApi {

	/**
	 * 请求触发报警
	 * 通常由客户联系呼叫中心请求触发车辆被盗报警，这种情况下用户身份已由CC核实，无需在程序内部验证
	 *
	 * @param vin 车辆唯一码
	 * @throws ApiException
	 */
	void requestAlarm(String vin) throws ApiException;

	/**
	 * 车辆产生报警
	 * 所有出现报警的类型会通过各自的stolenAlarm传上来
	 *
	 * @param otaDto          OTA传输对象
	 * @param stolenAlarmDtos 被盗警报传输对象列表
	 */
	void alarm(OtaDto otaDto, List<StolenAlarmDto> stolenAlarmDtos) throws ApiException;

	/**
	 * 更新追踪点
	 *
	 * @param otaDto    OTA传输对象
	 * @param trackDtos 追踪点传输对象列表
	 */
	void updateTrack(OtaDto otaDto, List<TrackDto> trackDtos) throws ApiException;

	/**
	 * 请求手动修改追踪设置
	 *
	 * @param vin           车辆唯一码
	 * @param trackInterval 追踪点间隔时间（秒）
	 * @param tracks        追踪点数量
	 */
	void requestTrackSetting(String vin, Integer trackInterval, Integer tracks) throws ApiException;

	/**
	 * 请求单次跟踪
	 *
	 * @param vin 车辆唯一码
	 */
	void requestSingleTrack(String vin) throws ApiException;

	/**
	 * 请求关闭警报
	 *
	 * @param vin 车辆唯一码
	 */
	void requestCloseAlarm(String vin) throws ApiException;

	/**
	 * 响应警报关闭状态请求
	 *
	 * @param otaDto          OTA传输对象
	 * @param allAlarmClosed  所有警报是否关闭
	 * @param stolenAlarmDtos 被盗警报传输对象列表
	 */
	void responseCloseAlarm(OtaDto otaDto, Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos)
			throws ApiException;

	/**
	 * 请求验证钥匙
	 *
	 * @param vin   车辆唯一码
	 * @param keyId 钥匙ID
	 */
	void requestAuthKey(String vin, Integer keyId) throws ApiException;

	/**
	 * 响应钥匙验证请求
	 *
	 * @param otaDto        OTA传输对象
	 * @param keyIsAccepted 钥匙是否接受
	 * @param failureReason 失败原因
	 */
	void responseAuthKey(OtaDto otaDto, Boolean keyIsAccepted, Integer failureReason) throws ApiException;

	/**
	 * 请求更改车辆固定状态
	 *
	 * @param vin        车辆唯一码
	 * @param immoStatus 固定状态
	 */
	void requestImmobilise(String vin, Integer immoStatus) throws ApiException;

	/**
	 * 响应更改车辆固定状态请求
	 *
	 * @param otaDto        OTA传输对象
	 * @param immoStatus    固定状态
	 * @param failureReason 失败原因
	 */
	void responseImmobilise(OtaDto otaDto, Integer immoStatus, Integer failureReason) throws ApiException;

	/**
	 * 请求修改保护策略
	 *
	 * @param vin                        车辆唯一码
	 * @param startTime                  策略开始时间
	 * @param endTime                    策略结束时间
	 * @param protectStrategySettingDtos 策略配置列表
	 */
	void requestUpdateProtectStrategy(String vin, Date startTime, Date endTime,
			List<ProtectStrategySettingDto> protectStrategySettingDtos) throws ApiException;

	/**
	 * 响应修改保护策略请求
	 */
	void responseUpdateProtectStrategy() throws ApiException;

	/**
	 * 得到车辆被盗状态
	 *
	 * @param vin 车辆唯一码
	 * @return 被盗状态传输对象
	 * @throws ApiException
	 */
	StolenStatusDto getStolenStatus(String vin) throws ApiException;

	/**
	 * 列出车辆最近轨迹
	 *
	 * @param vin        车辆唯一码
	 * @param trackCount 轨迹数量
	 * @return 车辆轨迹传输对象
	 * @throws ApiException
	 */
	List<TrackDto> listVehicleLastTrack(String vin, int trackCount) throws ApiException;
}
