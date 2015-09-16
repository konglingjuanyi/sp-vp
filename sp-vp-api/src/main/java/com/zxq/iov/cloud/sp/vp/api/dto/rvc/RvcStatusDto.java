/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-29       荣杰         1.0            Initial Version
 * 2015-07-30       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.rvc;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 远程控制命令状态传输对象
 */
public class RvcStatusDto extends OtaDto {

	// 命令状态
	private String commandStatus;
	// 失败类型
	private Integer failureType;

	public RvcStatusDto() {
	}

	public RvcStatusDto(String commandStatus, Integer failureType) {
		this.commandStatus = commandStatus;
		this.failureType = failureType;
	}

	public String getCommandStatus() {
		return commandStatus;
	}

	public void setCommandStatus(String commandStatus) {
		this.commandStatus = commandStatus;
	}

	public Integer getFailureType() {
		return failureType;
	}

	public void setFailureType(Integer failureType) {
		this.failureType = failureType;
	}
}
