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
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.ImmobiliseReqDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 固定车辆请求传输对象
 */
public class ImmobiliseReqDto extends OtaDto {

	// 固化状态
	private Integer immoStatus;

	public ImmobiliseReqDto() {
	}

	public ImmobiliseReqDto(Integer immoStatus) {
		this.immoStatus = immoStatus;
	}

	public Integer getImmoStatus() {
		return immoStatus;
	}

	public void setImmoStatus(Integer immoStatus) {
		this.immoStatus = immoStatus;
	}
}
