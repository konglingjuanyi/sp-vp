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
 * com.zxq.iov.cloud.sp.vp.api.DiagnosticApiImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 远程诊断API测试类
 */
@Transactional
public class DiagnosticApiImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticApiImplTest.class);

    @Autowired
    private IDiagnosticApi diagnosticApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestDiagnostic() throws Exception {
        List<DiagnosticDto> diagnosticDtos = new ArrayList<>();
//        diagnosticDtos.add(new DiagnosticDto("1", "1", "1")); 这里一段需求不确定
        diagnosticApi.requestDiagnostic(vin, diagnosticDtos);
    }

    @Test
    @Rollback(false)
    public void testResponseDiagnostic() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_DIAGNOSTIC, 2);
        List<DiagnosticDto> diagnosticDtos = new ArrayList<>();
//        diagnosticDtos.add(new DiagnosticDto("1", "1", "1"));
        diagnosticApi.responseDiagnostic(otaDto, diagnosticDtos);
    }

}
