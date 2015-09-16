/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-08-12       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.RemoteKeyServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 电子钥匙服务测试类
 */
@Transactional
public class RemoteKeyServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyServiceImplTest.class);

    @Autowired
    private IRemoteKeyService remoteKeyService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestWriteKey() throws Exception {
        Integer keyType = 1;
        String keyValue = "1";
        Long keyReference = 1L;
        remoteKeyService.requestWriteKey(new Tbox(tboxId, vin), keyType, keyValue, keyReference, new Date(), new Date());
    }

    @Test
    @Rollback(false)
    public void testResponseWriteKey() throws Exception {
        remoteKeyService.responseWriteKey(tboxId, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestDeleteKey() throws Exception {
        remoteKeyService.requestDeleteKey(vin, 1L);
    }

    @Test
    @Rollback(false)
    public void testResponseDeleteKey() throws Exception {
        remoteKeyService.responseDeleteKey(tboxId, true, null);
    }

    @Test
    @Rollback(false)
    public void testKeyAlarm() throws Exception {
        remoteKeyService.keyAlarm(tboxId);
    }
}