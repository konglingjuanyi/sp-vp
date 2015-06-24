package com.zxq.iov.cloud.sp.vp.service.diagnostic;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDaoService;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 远程诊断持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-24 9:43
 * modify date
 * @version 0.1, 2015-6-24
 */
@Transactional
public class DiagnosticDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IDiagnosticDaoService diagnosticDaoService;

    @Test
    @Rollback(false)
    public void testCreateDiagnostic(){
        Long tboxId = 1L;
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setTboxId(tboxId);
        diagnostic.setCanId("1");
        diagnostic.setServiceId("1");
        diagnostic.setParameter("1");
        diagnostic = diagnosticDaoService.createDiagnostic(diagnostic);
        Assert.assertNotNull(diagnostic);
    }

    @Test
    @Rollback(false)
    public void testUpdateRemoteKey(){
        Long diagnosticId = 9L;
        Diagnostic diagnostic = diagnosticDaoService.findDiagnosticById(diagnosticId);
        diagnostic.setEventId(1L);
        diagnostic = diagnosticDaoService.updateDiagnostic(diagnostic);
        Assert.assertNotNull(diagnostic);
    }

    @Test
    @Rollback(false)
    public void testRemoveReomteKey(){
        Long diagnosticId = 8L;
        diagnosticDaoService.removeDiagnostic(diagnosticId);
    }

    @Test
    @Rollback(false)
    public void testFindDiagnosticByEventId(){
        Long eventId = 1L;
        Diagnostic diagnostic = diagnosticDaoService.findDiagnosticByEventId(eventId);
        Assert.assertNotNull(diagnostic);
    }
}
