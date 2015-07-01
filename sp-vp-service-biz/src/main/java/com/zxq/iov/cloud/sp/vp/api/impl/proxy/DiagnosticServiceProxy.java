package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSONObject;
import com.zxq.iov.cloud.sp.vp.api.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEventCallback;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.QueueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程诊断代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 13:21
 * modify date 2015-6-30 15:26
 * @version 0.2, 2015-6-30
 */
@Service
@Qualifier("diagnosticServiceProxy")
public class DiagnosticServiceProxy implements IDiagnosticService, IEventCallback {

    @Autowired
    @Qualifier("diagnosticService")
    private IDiagnosticService diagnosticService;
    @Autowired
    @Qualifier("eventImpl")
    private IEvent event;

    @Override
    public void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_DIAGNOSTIC, 1);
        Long eventId = event.start(otaDto);
        diagnosticService.requestDiagnostic(vin, diagnosticDtos);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "diagnostic");
        msg.put("command", diagnosticDtos);
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
    }

    @Override
    public void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) {
        event.start(otaDto);
        diagnosticService.responseDiagnostic(otaDto, diagnosticDtos);
        event.end(otaDto);
    }

    @Override
    public void timeout() {

    }
}
