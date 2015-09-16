/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-24       荣杰         1.0            Initial Version
 * 2015-08-03       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.diagnostic.DiagnosticDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.diagnostic;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 远程诊断数据访问测试类
 */
@Transactional
public class DiagnosticDaoImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticDaoImplTest.class);

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
        LOGGER.info("diagnostic's eventId = " + diagnostic.getEventId());
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
