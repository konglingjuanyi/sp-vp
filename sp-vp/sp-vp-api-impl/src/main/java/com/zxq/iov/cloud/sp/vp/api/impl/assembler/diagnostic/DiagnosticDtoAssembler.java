package com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic;

import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;

/**
 * 安防 远程诊断传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:11
 * modify date 2015-7-17 17:39
 * @version 0.3, 2015-7-17
 */
public class DiagnosticDtoAssembler {

    public Diagnostic fromDto(final DiagnosticDto diagnosticDto) {
        return new Diagnostic(BinaryAndHexUtil.bytesToHexString(diagnosticDto.getCanId(), false),
                BinaryAndHexUtil.bytesToHexString(diagnosticDto.getServiceId(), false),
                BinaryAndHexUtil.bytesToHexString(diagnosticDto.getParameter(), false));
    }

}
