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
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic.DiagnosticDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic;

import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.common.util.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 远程诊断传输对象装配类
 */
public class DiagnosticDtoAssembler {

    public Diagnostic fromDto(final DiagnosticDto diagnosticDto) {
        return new Diagnostic(BinaryAndHexUtil.bytesToHexString(diagnosticDto.getCanId(), false),
                BinaryAndHexUtil.bytesToHexString(diagnosticDto.getServiceId(), false),
                BinaryAndHexUtil.bytesToHexString(diagnosticDto.getParameter(), false));
    }

    public List<Diagnostic> fromDtoList(final List<DiagnosticDto> diagnosticDtos) {
        List<Diagnostic> diagnostics = new ArrayList<>();
        for(DiagnosticDto diagnosticDto : diagnosticDtos) {
            diagnostics.add(fromDto(diagnosticDto));
        }
        return diagnostics;
    }

}
