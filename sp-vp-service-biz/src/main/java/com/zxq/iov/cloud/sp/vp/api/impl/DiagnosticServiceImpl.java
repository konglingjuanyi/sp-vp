package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic.DiagnosticDtoAssembler;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDaoService;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 远程诊断服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:06
 * modify date
 * @version 0.1, 2015-6-24
 */
@Service
@Qualifier("diagnosticService")
public class DiagnosticServiceImpl implements IDiagnosticService {

    @Autowired
    private IDiagnosticDaoService diagnosticDaoService;

    @Override
    public void requestDiagnostic(DiagnosticDto diagnosticDto) {
        Diagnostic diagnostic = new DiagnosticDtoAssembler().fromDto(diagnosticDto);
        diagnosticDaoService.createDiagnostic(diagnostic);
    }

    @Override
    public void responseDiagnostic(DiagnosticDto diagnosticDto) {
        Diagnostic diagnostic = diagnosticDaoService.findDiagnosticByEventId(diagnosticDto.getEventId());
        diagnostic.setResult(diagnosticDto.getResult());
        diagnosticDaoService.updateDiagnostic(diagnostic);
    }
}
