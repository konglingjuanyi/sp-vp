/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-08-12       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.TboxDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 TBOX数据访问测试类
 */
@Transactional
public class TboxDaoImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TboxDaoImplTest.class);

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private ITboxDao tboxDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    @Rollback(false)
    public void testUpdateAsymmetricKey(){
        String modulus = "1";
        String publicExponent = "1";
        String privateExponent = "2";
        tboxDao.updateAsymmetricKey(tboxId, modulus, publicExponent, privateExponent);
        String _publicKey = redisTemplate.opsForValue().get(tboxId+"_publicKey").toString();
        Assert.assertNotNull(_publicKey);
        LOGGER.info("publicKey = " + _publicKey);
    }

}
