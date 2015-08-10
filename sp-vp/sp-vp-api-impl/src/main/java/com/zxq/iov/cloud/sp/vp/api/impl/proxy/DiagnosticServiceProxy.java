package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEventCallback;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程诊断代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 13:21
 * modify date 2015-7-24 11:19
 * @version 0.4, 2015-7-24
 */
@Service
@Qualifier("diagnosticServiceProxy")
public class DiagnosticServiceProxy extends BaseProxy implements IDiagnosticService, IEventCallback {

    @Autowired
    @Qualifier("diagnosticService")
    private IDiagnosticService diagnosticService;
    @Autowired
    @Qualifier("eventImpl")
    private IEvent event;

    @Override
    public void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_DIAGNOSTIC, 1);
        event.start(otaDto);
        diagnosticService.requestDiagnostic(vin, diagnosticDtos);
        sendQueue(otaDto);
    }

    @Override
    public void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) throws Exception {
        event.start(otaDto);
        diagnosticService.responseDiagnostic(otaDto, diagnosticDtos);
        event.end(otaDto);
    }

    @Override
    public void timeout() {

    }
}
