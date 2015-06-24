package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IDiagnosticService;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 远程诊断服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:28
 * modify date
 * @version 0.1, 2015-6-24
 */
@Transactional
public class DiagnosticServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("diagnosticServiceProxy")
    private IDiagnosticService diagnosticService;

    @Test
    @Rollback(false)
    public void testRequestDiagnostic() {
        Long tboxId = 1L;
        String canId = "1";
        String serviceId = "1";
        String parameter = "1";
        DiagnosticDto diagnosticDto = new DiagnosticDto(canId, serviceId, parameter);
        diagnosticDto.setTboxId(tboxId);
        diagnosticService.requestDiagnostic(diagnosticDto);
    }

    @Test
    @Rollback(false)
    public void testResponseDiagnostic() {
        Long tboxId = 1L;
        DiagnosticDto diagnosticDto = new DiagnosticDto();
        diagnosticDto.setTboxId(tboxId);
        diagnosticService.responseDiagnostic(diagnosticDto);
    }

}
