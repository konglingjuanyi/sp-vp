package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 远程诊断代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 13:21
 * modify date
 * @version 0.1, 2015-6-24
 */
@Service
@Qualifier("diagnosticServiceProxy")
public class DiagnosticServiceProxy implements IDiagnosticService {

    @Autowired
    @Qualifier("diagnosticService")
    private IDiagnosticService diagnosticService;
    @Autowired
    @Qualifier("eventImpl")
    private IEvent event;

    @Override
    public void requestDiagnostic(DiagnosticDto diagnosticDto) {
        diagnosticDto.setAid(Constants.AID_DIAGNOSTIC);
        diagnosticDto.setMid(1);
        event.start(diagnosticDto);
        diagnosticService.requestDiagnostic(diagnosticDto);
        event.end(diagnosticDto);
    }

    @Override
    public void responseDiagnostic(DiagnosticDto diagnosticDto) {
        diagnosticDto.setAid(Constants.AID_DIAGNOSTIC);
        diagnosticDto.setMid(2);
        event.start(diagnosticDto);
        diagnosticService.responseDiagnostic(diagnosticDto);
        event.end(diagnosticDto);
    }
}
