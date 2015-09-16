/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 * 2015-08-12       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.TboxConfigServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.util.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * 安防服务 远程配置服务测试类
 */
@Transactional
public class TboxConfigServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TboxConfigServiceImplTest.class);

    @Autowired
    private ITboxConfigService tboxConfigService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testCheckConfigDelta() throws Exception {
        String mcuVersion = "01";
        String mpuVersion = "01";
        String configVersion = "01";
        Integer configDelta = 1;
        String iccid = "1";
        Long tboxId = 1L;
        Long eventId = 1L;
        configDelta = tboxConfigService.checkConfigDelta(tboxId, BinaryAndHexUtil.hexStringToByte(mcuVersion),
                BinaryAndHexUtil.hexStringToByte(mpuVersion), vin, iccid,
                BinaryAndHexUtil.hexStringToByte(configVersion), configDelta, eventId);
        System.out.print(configDelta);
    }

    @Test
    @Rollback(false)
    public void testGetConfigPackage() throws Exception {
        Integer packageId = 1;
        Long eventId = 1L;
        List<TboxConfigSetting> tboxConfigSettings = tboxConfigService.getConfigPackage(tboxId, packageId, eventId);
        Assert.assertNotNull(tboxConfigSettings);
    }

    @Test
    @Rollback(false)
    public void testGenerateAsymmetricKey() throws Exception {
        byte[] modulus = tboxConfigService.generateAsymmetricKey(tboxId);
        byte[] tmp = new byte[129];
        tmp[0] = 0;
        for(int i=0; i< modulus.length; i++) {
            tmp[i+1] = modulus[i];
        }
        BigInteger m = new BigInteger(tmp);
        Assert.assertNotNull(m);
    }

    @Test
    @Rollback(false)
    public void testBindTboxWithSecretKey() throws Exception {
//        String modulus = "125163475082136763811297846029596850610504955299888582273646147017966939756728090616359373212787959305990910500897234112681820675075583228985519225290989678399732309123754999272780735544151165489820178848785819484661828969770254266879024307066897705246976539119997492907397607531121913933742804077791829551351";
//        String secretKey = "1";
//        String tboxSn = "123";
//        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, "65537");
//        String secretKeyWithEnc = RSAUtils.encryptByPublicKey(secretKey, pubKey);
//        String tboxSnWithEnc = RSAUtils.encryptByPublicKey(tboxSn, pubKey);
//        tboxConfigService.bindTboxWithSecretKey(tboxId,secretKeyWithEnc, tboxSnWithEnc);
    }

}