/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-24       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.DiagnosticServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 远程诊断服务测试类
 */
@Transactional
public class DiagnosticServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticServiceImplTest.class);

    @Autowired
    private IDiagnosticService diagnosticService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestDiagnostic() throws Exception {
        List<Diagnostic> diagnostics = new ArrayList<>();
        diagnostics.add(new Diagnostic("1", "1", "1"));
        diagnosticService.requestDiagnostic(new Tbox(tboxId, vin), diagnostics);
    }

    @Test
    @Rollback(false)
    public void testResponseDiagnostic() throws Exception {
        List<Diagnostic> diagnostics = new ArrayList<>();
        diagnostics.add(new Diagnostic("1", "1", "1"));
        diagnosticService.responseDiagnostic(tboxId, diagnostics);
    }

}