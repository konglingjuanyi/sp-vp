package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IDiagnosticApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.diagnostic.DiagnosticDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程诊断服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:06
 * modify date 2015-8-11 10:06
 * @version 0.7, 2015-8-11
 */
@Service
public class DiagnosticApiImpl extends BaseApi implements IDiagnosticApi {

    @Autowired
    private IDiagnosticService diagnosticService;
    @Autowired
    private IEventService eventService;

    @Override
    public void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws ServLayerException {
        AssertRequired("vin,diagnosticDtos", vin, diagnosticDtos);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_DIAGNOSTIC, 1);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            diagnosticService.requestDiagnostic(vin, new DiagnosticDtoAssembler().fromDtoList(diagnosticDtos));
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) throws ServLayerException {
        AssertRequired("otaDto,diagnosticDtos", otaDto, diagnosticDtos);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            diagnosticService.responseDiagnostic(otaDto.getTboxId(),
                    new DiagnosticDtoAssembler().fromDtoList(diagnosticDtos));
            eventService.end(event);
        }
    }
}