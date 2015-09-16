/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-10       荣杰         1.0            Initial Version
 * 2015-06-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 bCall通话传输对象
 */
public class BcallRecordDto extends OtaDto {

	// 呼叫中心电话号码
	private String callNumber;

	public BcallRecordDto() {
	}

	public BcallRecordDto(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

}
