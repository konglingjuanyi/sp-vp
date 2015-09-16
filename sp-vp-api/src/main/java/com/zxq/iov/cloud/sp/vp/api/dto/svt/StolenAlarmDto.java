/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 * 2015-06-26       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;

import java.util.Date;

/**
 * 安防服务 被盗警报传输对象
 */
public class StolenAlarmDto extends OtaDto {

	// 报警类型
	private Integer alarmTypeId;
	// 报警是否被触发过
	private Boolean trigAlarmSts;
	// 当前是否还在报警
	private Boolean currentAlarmSts;
	// 车辆位置
	private VehiclePosDto vehiclePosDto;
	// 报警事件
	private Date alarmTime;
	// 报警数据
	private String alarmData;

	public StolenAlarmDto() {
	}

	public StolenAlarmDto(Integer alarmTypeId, Boolean trigAlarmSts, Boolean currentAlarmSts,
			VehiclePosDto vehiclePosDto, Date alarmTime, String alarmData) {
		this.alarmTypeId = alarmTypeId;
		this.trigAlarmSts = trigAlarmSts;
		this.currentAlarmSts = currentAlarmSts;
		this.vehiclePosDto = vehiclePosDto;
		this.alarmTime = alarmTime;
		this.alarmData = alarmData;
	}

	public Integer getAlarmTypeId() {
		return alarmTypeId;
	}

	public void setAlarmTypeId(Integer alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}

	public Boolean isTrigAlarmSts() {
		return trigAlarmSts;
	}

	public void setTrigAlarmSts(Boolean trigAlarmSts) {
		this.trigAlarmSts = trigAlarmSts;
	}

	public Boolean isCurrentAlarmSts() {
		return currentAlarmSts;
	}

	public void setCurrentAlarmSts(Boolean currentAlarmSts) {
		this.currentAlarmSts = currentAlarmSts;
	}

	public VehiclePosDto getVehiclePosDto() {
		return vehiclePosDto;
	}

	public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
		this.vehiclePosDto = vehiclePosDto;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmData() {
		return alarmData;
	}

	public void setAlarmData(String alarmData) {
		this.alarmData = alarmData;
	}

}
