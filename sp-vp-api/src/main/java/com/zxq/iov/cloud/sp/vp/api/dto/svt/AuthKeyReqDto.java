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
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.AuthKeyReqDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 验证钥匙请求传输对象
 */
public class AuthKeyReqDto extends OtaDto {

	// 钥匙ID
	private Integer keyId;

	public AuthKeyReqDto() {
	}

	public AuthKeyReqDto(Integer keyId) {
		this.keyId = keyId;
	}

	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}
}
