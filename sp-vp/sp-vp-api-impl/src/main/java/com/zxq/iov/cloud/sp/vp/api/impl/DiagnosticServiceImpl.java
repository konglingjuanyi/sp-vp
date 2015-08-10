package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic.DiagnosticDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDaoService;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程诊断服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:06
 * modify date 2015-7-24 13:02
 * @version 0.4, 2015-7-24
 */
@Service
@Qualifier("diagnosticService")
public class DiagnosticServiceImpl extends BaseService implements IDiagnosticService {

    @Autowired
    private IDiagnosticDaoService diagnosticDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;

    @Override
    public void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws Exception {
        AssertRequired("vin", vin);
        DiagnosticDtoAssembler diagnosticDtoAssembler = new DiagnosticDtoAssembler();
        Long tboxId = tboxDaoService.findTboxIdByVin(vin);
        Diagnostic diagnostic;
        for(DiagnosticDto diagnosticDto : diagnosticDtos) {
            diagnostic = diagnosticDtoAssembler.fromDto(diagnosticDto);
            diagnostic.setVin(vin);
            diagnostic.setTboxId(tboxId);
            diagnosticDaoService.createDiagnostic(diagnostic);
        }
    }

    @Override
    public void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) {
        Diagnostic diagnostic;
        for(DiagnosticDto diagnosticDto : diagnosticDtos) {
            diagnostic = diagnosticDaoService.findDiagnosticByEventId(diagnosticDto.getEventId());
            diagnostic.setResult(BinaryAndHexUtil.bytesToHexString(diagnosticDto.getResult(), false));
            diagnosticDaoService.updateDiagnostic(diagnostic);
        }
    }
}
