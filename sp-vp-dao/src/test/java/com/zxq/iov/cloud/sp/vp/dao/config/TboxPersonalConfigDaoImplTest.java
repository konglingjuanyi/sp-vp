/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 * 2015-07-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.TboxPersonalConfigDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 TBOX个性化配置数据访问测试类
 */
@Transactional
public class TboxPersonalConfigDaoImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TboxPersonalConfigDaoImplTest.class);

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private ITboxPersonalConfigDao tboxPersonalConfigDao;

    @Test
    @Rollback(false)
    public void testCreateTboxPersonalConfig(){
        TboxPersonalConfig tboxPersonalConfig = new TboxPersonalConfig(tboxId, userId, vin);
        tboxPersonalConfig = tboxPersonalConfigDao.createTboxPersonalConfig(tboxPersonalConfig);
        Assert.assertNotNull(tboxPersonalConfig);
    }

    @Test
    @Rollback(false)
    public void testUpdateTboxPersonalConfig(){
        Long tboxPersonalConfigId = 3L;
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDao.findTboxPersonalConfigById(tboxPersonalConfigId);
        tboxPersonalConfig.setConfigDelta(2);
        tboxPersonalConfig = tboxPersonalConfigDao.updateTboxPersonalConfig(tboxPersonalConfig);
        Assert.assertNotNull(tboxPersonalConfig);
        LOGGER.info("tboxPersonalConfig's configDelta = " + tboxPersonalConfig.getConfigDelta());
    }

    @Test
    @Rollback(false)
    public void testRemoveTboxPersonalConfig(){
        Long tboxPersonalConfigId = 3L;
        tboxPersonalConfigDao.removeTboxPersonalConfig(tboxPersonalConfigId);
    }

    @Test
    @Rollback(false)
    public void testFindTboxPersonalConfigByTboxId(){
        Long tboxId = 1L;
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDao.findTboxPersonalConfigByTboxId(tboxId);
        Assert.assertNotNull(tboxPersonalConfig);
    }

    @Test
    @Rollback(false)
    public void testFindTboxPersonalConfigByVin(){
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDao.findTboxPersonalConfigByVin(vin);
        Assert.assertNotNull(tboxPersonalConfig);
    }

}
