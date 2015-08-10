package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IDiagnosticApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic.DiagnosticDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程诊断服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:06
 * modify date 2015-8-7 12:59
 * @version 0.6, 2015-8-7
 */
@Service
public class DiagnosticApiImpl extends BaseApi implements IDiagnosticApi {

    @Autowired
    private IDiagnosticService diagnosticService;
    @Autowired
    private IEventService eventService;

    @Override
    public void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws Exception {
        AssertRequired("vin,diagnosticDtos", vin, diagnosticDtos);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_DIAGNOSTIC, 1);
        eventService.start(vin, Constants.AID_DIAGNOSTIC + "1");
        diagnosticService.requestDiagnostic(vin, new DiagnosticDtoAssembler().fromDtoList(diagnosticDtos));
        sendQueue(otaDto);
    }

    @Override
    public void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) throws Exception {
        AssertRequired("otaDto,diagnosticDtos", otaDto, diagnosticDtos);
        eventService.start(getVin(otaDto), getCode(otaDto));
        diagnosticService.responseDiagnostic(otaDto.getTboxId(),
                new DiagnosticDtoAssembler().fromDtoList(diagnosticDtos));
        eventService.end(getVin(otaDto), getCode(otaDto));
    }
}