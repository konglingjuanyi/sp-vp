/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-30       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.rvc.RvcStatusDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.rvc;

import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;

/**
 * 安防服务 控制命令状态传输对象装配类
 */
public class RvcStatusDtoAssembler {

	public RvcStatusDto toDto(final ControlCommand controlCommand) {
		return new RvcStatusDto(controlCommand.getCommandStatus(), controlCommand.getFailureType());
	}

}
