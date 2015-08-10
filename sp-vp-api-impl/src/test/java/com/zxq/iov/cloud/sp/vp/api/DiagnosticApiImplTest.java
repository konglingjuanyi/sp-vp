package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 远程诊断服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:28
 * modify date 2015-8-5 17:01
 * @version 0.3 2015-8-5
 */
@Transactional
public class DiagnosticApiImplTest extends BaseServiceTestCase {

    @Autowired
    private IDiagnosticApi diagnosticApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestDiagnostic() throws Exception {
        List<DiagnosticDto> diagnosticDtos = new ArrayList<>();
//        diagnosticDtos.add(new DiagnosticDto("1", "1", "1")); 这里一段需求不确定
        diagnosticApi.requestDiagnostic(vin, diagnosticDtos);
    }

    @Test
    @Rollback(false)
    public void testResponseDiagnostic() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_DIAGNOSTIC, 2);
        List<DiagnosticDto> diagnosticDtos = new ArrayList<>();
//        diagnosticDtos.add(new DiagnosticDto("1", "1", "1"));
        diagnosticApi.responseDiagnostic(otaDto, diagnosticDtos);
    }

}
