/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-24       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.diagnostic;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;

/**
 * 安防 远程诊断数据访问接口
 */
public interface IDiagnosticDao extends BaseService<Diagnostic, Long> {

	/**
	 * 创建远程诊断
	 *
	 * @param diagnostic 远程诊断对象
	 * @return 远程诊断对象
	 */
	Diagnostic createDiagnostic(Diagnostic diagnostic);

	/**
	 * 更新远程诊断
	 *
	 * @param diagnostic 远程诊断对象
	 * @return 远程诊断对象
	 */
	Diagnostic updateDiagnostic(Diagnostic diagnostic);

	/**
	 * 删除远程诊断
	 *
	 * @param diagnosticId 远程诊断ID
	 */
	void removeDiagnostic(Long diagnosticId);

	/**
	 * 根据主键得到远程诊断对象
	 *
	 * @param diagnosticId 远程诊断主键
	 * @return 远程诊断对象
	 */
	Diagnostic findDiagnosticById(Long diagnosticId);

	/**
	 * 根据事件实例ID得到远程诊断对象
	 *
	 * @param eventId 事件实例ID
	 * @return 远程诊断对象
	 */
	Diagnostic findDiagnosticByEventId(Long eventId);

}