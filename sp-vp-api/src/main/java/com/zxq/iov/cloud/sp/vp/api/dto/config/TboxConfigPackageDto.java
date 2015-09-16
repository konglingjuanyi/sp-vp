/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-22       荣杰         1.0            Initial Version
 * 2015-07-27       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.config;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.List;

/**
 * 安防服务 配置包传输对象
 */
public class TboxConfigPackageDto extends OtaDto {

	// TBOX配置设置传输对象
	private List<TboxConfigSettingDto> tboxConfigSettingDtos;
	// 具体包ID
	private Integer packageId;

	public List<TboxConfigSettingDto> getTboxConfigSettingDtos() {
		return tboxConfigSettingDtos;
	}

	public void setTboxConfigSettingDtos(List<TboxConfigSettingDto> tboxConfigSettingDtos) {
		this.tboxConfigSettingDtos = tboxConfigSettingDtos;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

}
