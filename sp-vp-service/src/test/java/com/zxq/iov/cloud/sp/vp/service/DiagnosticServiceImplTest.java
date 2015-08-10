package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 远程诊断服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:28
 * modify date 2015-8-5 16:46
 * @version 0.3 2015-8-5
 */
@Transactional
public class DiagnosticServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IDiagnosticService diagnosticService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestDiagnostic() throws Exception {
        List<Diagnostic> diagnostics = new ArrayList<>();
        diagnostics.add(new Diagnostic("1", "1", "1"));
        diagnosticService.requestDiagnostic(vin, diagnostics);
    }

    @Test
    @Rollback(false)
    public void testResponseDiagnostic() throws Exception {
        List<Diagnostic> diagnostics = new ArrayList<>();
        diagnostics.add(new Diagnostic("1", "1", "1"));
        diagnosticService.responseDiagnostic(tboxId, diagnostics);
    }

}