/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-06-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.status;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防服务 车辆警告信息传输对象
 */
public class VehicleAlertDto extends OtaDto {

	// 报警ID
	private Integer alertId;
	// 报警时间
	private Date alertTime;
	// 车辆位置
	private VehiclePosDto vehiclePosDto;
	// 报警状态
	private Boolean alertStatus;
	// 报警数据
	private Integer alertData;

	public VehicleAlertDto() {
	}

	public VehicleAlertDto(Integer alertId, Date alertTime, VehiclePosDto vehiclePosDto, Boolean alertStatus,
			Integer alertData) {
		this.alertId = alertId;
		this.alertTime = alertTime;
		this.vehiclePosDto = vehiclePosDto;
		this.alertStatus = alertStatus;
		this.alertData = alertData;
	}

	public Integer getAlertId() {
		return alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public Date getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

	public VehiclePosDto getVehiclePosDto() {
		return vehiclePosDto;
	}

	public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
		this.vehiclePosDto = vehiclePosDto;
	}

	public Boolean isAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(Boolean alertStatus) {
		this.alertStatus = alertStatus;
	}

	public Integer getAlertData() {
		return alertData;
	}

	public void setAlertData(Integer alertData) {
		this.alertData = alertData;
	}
}
