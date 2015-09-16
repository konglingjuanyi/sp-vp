/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-16       荣杰         1.0            Initial Version
 * 2015-07-10       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.ProtectStrategySettingDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 阻止车辆启动传输对象
 */
public class ProtectStrategySettingDto extends OtaDto {

	// 报警类型
	private Integer alarmTypeId;
	// 是否还有报警
	private Boolean alarmEnabled;

	public ProtectStrategySettingDto() {
	}

	public ProtectStrategySettingDto(Integer alarmTypeId, Boolean alarmEnabled) {
		this.alarmTypeId = alarmTypeId;
		this.alarmEnabled = alarmEnabled;
	}

	public Integer getAlarmTypeId() {
		return alarmTypeId;
	}

	public void setAlarmTypeId(Integer alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}

	public Boolean isAlarmEnabled() {
		return alarmEnabled;
	}

	public void setAlarmEnabled(Boolean alarmEnabled) {
		this.alarmEnabled = alarmEnabled;
	}
}
