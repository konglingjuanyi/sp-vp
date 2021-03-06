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
 * com.zxq.iov.cloud.sp.vp.dao.diagnostic.impl.DiagnosticDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.diagnostic.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDao;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.repo.IDiagnosticRepository;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程诊断数据访问接口实现类
 */
@Service
public class DiagnosticDaoImpl extends BaseServiceImpl<IDiagnosticRepository, Diagnostic, Long>
		implements IDiagnosticDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticDaoImpl.class);

	@Autowired
	public DiagnosticDaoImpl(IDiagnosticRepository repo) {
		super(repo);
	}

	@Override
	public Diagnostic createDiagnostic(Diagnostic diagnostic) {
		if (diagnostic == null) {
			LOGGER.error("diagnostic cannot be null");
		}
		diagnostic.setId(null);
		super.save(diagnostic);
		return diagnostic;
	}

	@Override
	public Diagnostic updateDiagnostic(Diagnostic diagnostic) {
		if (diagnostic == null) {
			LOGGER.error("diagnostic cannot be null");
		}
		super.update(diagnostic);
		return diagnostic;
	}

	@Override
	public void removeDiagnostic(Long diagnosticId) {
		if (diagnosticId == null) {
			LOGGER.error("diagnosticId cannot be null");
		}
		super.delete(diagnosticId);
	}

	@Override
	public Diagnostic findDiagnosticById(Long diagnosticId) {
		if (diagnosticId == null) {
			LOGGER.error("diagnosticId cannot be null");
		}
		return super.findOne(diagnosticId);
	}

	@Override
	public Diagnostic findDiagnosticByEventId(Long eventId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		List<Diagnostic> list = super.findListViaBatis(paramMap);
		return list.size() > 0 ? list.get(0) : null;
	}
}