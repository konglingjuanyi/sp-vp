/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-06       荣杰         1.0            Initial Version
 * 2015-07-30       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.rvc;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Map;

/**
 * 安防服务 远程控制命令传输对象
 */
public class RvcDto extends OtaDto {

	// 控制命令
	private byte[] command;
	// 控制参数
	private Map<Integer, byte[]> parameters;

	public RvcDto() {
	}

	public RvcDto(byte[] command, Map<Integer, byte[]> parameters) {
		this.command = command;
		this.parameters = parameters;
	}

	public byte[] getCommand() {
		return command;
	}

	public void setCommand(byte[] command) {
		this.command = command;
	}

	public Map<Integer, byte[]> getParameters() {
		return parameters;
	}

	public void setParameters(Map<Integer, byte[]> parameters) {
		this.parameters = parameters;
	}
}
