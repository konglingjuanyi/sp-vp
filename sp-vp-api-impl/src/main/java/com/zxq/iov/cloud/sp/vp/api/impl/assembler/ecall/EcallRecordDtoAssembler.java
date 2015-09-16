/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-12       荣杰         1.0            Initial Version
 * 2015-06-26       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall.EcallRecordDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall;

import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防服务 eCall通话记录传输对象装配类
 */
public class EcallRecordDtoAssembler {

	public EcallRecordDto toDto(final CallRecord callRecord) {
		return new EcallRecordDto(callRecord.getCallNumber());
	}
}
