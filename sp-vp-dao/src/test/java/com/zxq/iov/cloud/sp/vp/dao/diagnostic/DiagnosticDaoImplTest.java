package com.zxq.iov.cloud.sp.vp.dao.diagnostic;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
 * modify date 2015-8-3 15:56
 * @version 0.2, 2015-8-3
 */
@Transactional
public class DiagnosticDaoImplTest extends BaseServiceTestCase {

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private IDiagnosticDao diagnosticDao;

    @Test
    @Rollback(false)
    public void testCreateDiagnostic(){
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setTboxId(tboxId);
        diagnostic.setCanId("1");
        diagnostic.setServiceId("1");
        diagnostic.setParameter("1");
        diagnostic.setVin(vin);
        diagnostic = diagnosticDao.createDiagnostic(diagnostic);
        Assert.assertNotNull(diagnostic);
    }

    @Test
    @Rollback(false)
    public void testUpdateRemoteKey(){
        Long diagnosticId = 9L;
        Diagnostic diagnostic = diagnosticDao.findDiagnosticById(diagnosticId);
        diagnostic.setEventId(1L);
        diagnostic = diagnosticDao.updateDiagnostic(diagnostic);
        Assert.assertNotNull(diagnostic);
    }

    @Test
    @Rollback(false)
    public void testRemoveReomteKey(){
        Long diagnosticId = 8L;
        diagnosticDao.removeDiagnostic(diagnosticId);
    }

    @Test
    @Rollback(false)
    public void testFindDiagnosticByEventId(){
        Long eventId = 1L;
        Diagnostic diagnostic = diagnosticDao.findDiagnosticByEventId(eventId);
        Assert.assertNotNull(diagnostic);
    }
}
