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
 * com.zxq.iov.cloud.sp.vp.api.RemoteKeyApiImplTest
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 电子钥匙API测试类
 */
@Transactional
public class RemoteKeyApiImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyApiImplTest.class);

    @Autowired
    private IRemoteKeyApi remoteKeyApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestWriteKey() throws Exception {
        Integer keyType = 1;
        String keyValue = "01";
        Long keyReference = 1L;
        remoteKeyApi.requestWriteKey(vin, keyType, keyValue, keyReference, new Date(), new Date());
    }

    @Test
    @Rollback(false)
    public void testResponseWriteKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 2);
        remoteKeyApi.responseWriteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestDeleteKey() throws Exception {
        remoteKeyApi.requestDeleteKey(vin, 1L);
    }

    @Test
    @Rollback(false)
    public void testResponseDeleteKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 4);
        remoteKeyApi.responseDeleteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testKeyAlarm() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 5);
        remoteKeyApi.keyAlarm(otaDto);
    }
}
