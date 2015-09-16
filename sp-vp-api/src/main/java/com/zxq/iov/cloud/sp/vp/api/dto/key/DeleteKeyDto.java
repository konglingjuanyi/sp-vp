/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-06       荣杰         1.0            Initial Version
 * 2015-08-12       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.key.DeleteKeyDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.key;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 删除电子钥匙传输对象
 */
public class DeleteKeyDto extends OtaDto {

	// 钥匙引用
	private Long keyReference;

	public DeleteKeyDto() {
	}

	public DeleteKeyDto(Long keyReference) {
		this.keyReference = keyReference;
	}

	public Long getKeyReference() {
		return keyReference;
	}

	public void setKeyReference(Long keyReference) {
		this.keyReference = keyReference;
	}

}
