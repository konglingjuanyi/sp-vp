/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2
 *
 * com.zxq.iov.cloud.sp.vp.service.RemoteKeyServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 智能钥匙服务测试类
 */
@Transactional
public class RemoteKeyServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyServiceImplTest.class);

    @Autowired
    private IRemoteKeyService remoteKeyService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testCreateKey() throws Exception {
        RemoteKey remoteKey = remoteKeyService.createKey(userId, tboxId, vin);
        Assert.assertNotNull(remoteKey);
    }

    @Test
    @Rollback(false)
    public void testGrantKey() throws Exception {
        RemoteKey remoteKey = new RemoteKey(tboxId, vin, new Date(), new Date(), 1, userId);
        remoteKey = remoteKeyService.grantKey(remoteKey);
        Assert.assertNotNull(remoteKey);
    }

    @Test
    @Rollback(false)
    public void testUpdateKey() throws Exception {
        Long keyReference = 27L;
        remoteKeyService.updateKey(keyReference, new Date(), null, 2);
    }

    @Test
    @Rollback(false)
    public void testDisableKey() throws Exception {
        Long keyReference = 28L;
        RemoteKey remoteKey = remoteKeyService.disableKey(keyReference);
        Assert.assertNotNull(remoteKey);
    }

    @Test
    @Rollback(false)
    public void testEnableKey() throws Exception {
        Long keyReference = 28L;
        RemoteKey remoteKey = remoteKeyService.enableKey(keyReference);
        Assert.assertNotNull(remoteKey);
    }

    @Test
    @Rollback(false)
    public void testRemoveKey() throws Exception {
        Long keyReference = 28L;
        remoteKeyService.removeKey(keyReference);
    }

    @Test
    @Rollback(false)
    public void testResponseWriteKey() throws Exception {
        remoteKeyService.responseWriteKey(tboxId, true, null);
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

    @Test
    @Rollback(false)
    public void testListVehicleKey() throws Exception {
        List<RemoteKey> remoteKeys = remoteKeyService.listVehicleKey(vin);
        LOGGER.info("remoteKeys size:" + remoteKeys.size());
        Assert.assertTrue(remoteKeys.size() > 0);
    }
}