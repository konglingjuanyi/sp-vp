package com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic;

import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;

/**
 * 安防 远程诊断传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:11
 * modify date
 * @version 0.1, 2015-6-24
 */
public class DiagnosticDtoAssembler {

    public Diagnostic fromDto(final DiagnosticDto diagnosticDto) {
        return new Diagnostic(diagnosticDto.getTboxId(), diagnosticDto.getCanId(), diagnosticDto.getServiceId(),
                diagnosticDto.getParameter());
    }

}
