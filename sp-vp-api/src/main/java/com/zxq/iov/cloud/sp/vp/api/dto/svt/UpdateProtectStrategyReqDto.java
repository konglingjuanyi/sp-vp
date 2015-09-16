/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-06       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.UpdateProtectStrategyReqDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 固定车辆请求传输对象
 */
public class UpdateProtectStrategyReqDto extends OtaDto {

	// 策略开始时间
	private Date startTime;
	// 策略结束时间
	private Date endTime;
	// 策略设置列表
	private List<ProtectStrategySettingDto> protectStrategySettingDtos;

	public UpdateProtectStrategyReqDto() {
	}

	public UpdateProtectStrategyReqDto(Date startTime, Date endTime,
			List<ProtectStrategySettingDto> protectStrategySettingDtos) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.protectStrategySettingDtos = protectStrategySettingDtos;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<ProtectStrategySettingDto> getProtectStrategySettingDtos() {
		return protectStrategySettingDtos;
	}

	public void setProtectStrategySettingDtos(List<ProtectStrategySettingDto> protectStrategySettingDtos) {
		this.protectStrategySettingDtos = protectStrategySettingDtos;
	}
}
