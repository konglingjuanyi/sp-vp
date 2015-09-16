/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 * 2015-06-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall.BcallRecordDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防服务 bCall通话记录传输对象装配类
 */
public class BcallRecordDtoAssembler {

	public BcallRecordDto toDto(final CallRecord callRecord) {
		return new BcallRecordDto(callRecord.getCallNumber());
	}
}
