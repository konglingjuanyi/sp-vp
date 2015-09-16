/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.IDiagnosticService
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;

import java.util.List;

/**
 * 安防服务 远程诊断接口
 */
public interface IDiagnosticService {

	/**
	 * 请求远程诊断
	 *
	 * @param tbox        TBOX对象
	 * @param diagnostics 远程诊断对象列表
	 */
	void requestDiagnostic(Tbox tbox, List<Diagnostic> diagnostics) throws ServLayerException;

	/**
	 * 响应远程诊断请求
	 *
	 * @param tboxId      TBOX ID
	 * @param diagnostics 远程诊断对象列表
	 */
	void responseDiagnostic(Long tboxId, List<Diagnostic> diagnostics) throws ServLayerException;

}
