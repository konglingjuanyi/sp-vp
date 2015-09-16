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
 * com.zxq.iov.cloud.sp.vp.api.dto.config.ReadConfigReqDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.config;

/**
 * 安防服务 验证钥匙请求传输对象
 */
public class ReadConfigReqDto {

	// TBOX配置设置IDs
	private Long[] tboxConfigsettingIds;

	public ReadConfigReqDto() {
	}

	public ReadConfigReqDto(Long[] tboxConfigsettingIds) {
		this.tboxConfigsettingIds = tboxConfigsettingIds;
	}

	public Long[] getTboxConfigsettingIds() {
		return tboxConfigsettingIds;
	}

	public void setTboxConfigsettingIds(Long[] tboxConfigsettingIds) {
		this.tboxConfigsettingIds = tboxConfigsettingIds;
	}
}
