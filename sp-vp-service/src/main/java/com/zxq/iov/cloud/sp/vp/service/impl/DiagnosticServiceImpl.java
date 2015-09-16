/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-24       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.DiagnosticServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDao;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import com.zxq.iov.cloud.sp.vp.service.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防服务 远程诊断服务接口实现类
 */
@Service
public class DiagnosticServiceImpl extends BaseService implements IDiagnosticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticServiceImpl.class);

    @Autowired
    private IDiagnosticDao diagnosticDao;

    @Override
    public void requestDiagnostic(Tbox tbox, List<Diagnostic> diagnostics) throws ServLayerException {
        AssertRequired("tboxId,vin,diagnostics", tbox.getTboxId(), tbox.getVin(), diagnostics);
        for(Diagnostic diagnostic : diagnostics) {
            diagnostic.setVin(tbox.getVin());
            diagnostic.setTboxId(tbox.getTboxId());
            diagnosticDao.createDiagnostic(diagnostic);
        }
    }

    @Override
    public void responseDiagnostic(Long tboxId, List<Diagnostic> diagnostics) throws ServLayerException {
        AssertRequired("tboxId,diagnostics", tboxId, diagnostics);
        for(Diagnostic diagnostic : diagnostics) {
            diagnostic = diagnosticDao.findDiagnosticByEventId(diagnostic.getEventId());
            diagnostic.setResult(diagnostic.getResult());
            diagnosticDao.updateDiagnostic(diagnostic);
        }
    }
}