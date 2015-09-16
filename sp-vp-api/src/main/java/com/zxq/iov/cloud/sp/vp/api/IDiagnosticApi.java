/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-07-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.IDiagnosticApi
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;

import java.util.List;

/**
 * 安防服务 远程诊断API
 */
public interface IDiagnosticApi {

	/**
	 * 请求远程诊断
	 *
	 * @param vin            车辆唯一码
	 * @param diagnosticDtos 远程诊断传输对象列表
	 */
	void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws ApiException;

	/**
	 * 响应远程诊断请求
	 *
	 * @param otaDto         OTA传输对象
	 * @param diagnosticDtos 远程诊断传输对象列表
	 */
	void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) throws ApiException;

}
