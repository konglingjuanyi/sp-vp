/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-22       荣杰         1.0            Initial Version
 * 2015-07-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.config;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 TBOX配置传输对象
 */
public class TboxConfigDto extends OtaDto {

	// 配置包的数量
	private Integer packageCount;
	// 配置版本
	private Integer configDelta;

	public TboxConfigDto() {
		this.packageCount = 0;
	}

	public Integer getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}

	public Integer getConfigDelta() {
		return configDelta;
	}

	public void setConfigDelta(Integer configDelta) {
		this.configDelta = configDelta;
	}

}
