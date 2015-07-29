package com.zxq.iov.cloud.sp.vp.service.config;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxPersonalConfigDaoService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 TBOX个性化配置持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-19 13:24
 * modify date 2015-7-29 11:07
 * @version 0.2, 2015-7-29
 */
@Transactional
public class TboxPersonalConfigDaoServiceImplTest extends BaseServiceTestCase {

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private ITboxPersonalConfigDaoService tboxPersonalConfigDaoService;

    @Test
    @Rollback(false)
    public void testCreateTboxPersonalConfig(){
        TboxPersonalConfig tboxPersonalConfig = new TboxPersonalConfig(tboxId, userId, vin);
        tboxPersonalConfig = tboxPersonalConfigDaoService.createTboxPersonalConfig(tboxPersonalConfig);
        Assert.assertNotNull(tboxPersonalConfig);
    }

    @Test
    @Rollback(false)
    public void testUpdateTboxPersonalConfig(){
        Long tboxPersonalConfigId = 3L;
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDaoService.findTboxPersonalConfigById(tboxPersonalConfigId);
        tboxPersonalConfig.setConfigDelta(2);
        tboxPersonalConfig = tboxPersonalConfigDaoService.updateTboxPersonalConfig(tboxPersonalConfig);
        Assert.assertNotNull(tboxPersonalConfig);
    }

    @Test
    @Rollback(false)
    public void testRemoveTboxPersonalConfig(){
        Long tboxPersonalConfigId = 3L;
        tboxPersonalConfigDaoService.removeTboxPersonalConfig(tboxPersonalConfigId);
    }

    @Test
    @Rollback(false)
    public void testFindTboxPersonalConfigByTboxId(){
        Long tboxId = 1L;
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDaoService.findTboxPersonalConfigByTboxId(tboxId);
        Assert.assertNotNull(tboxPersonalConfig);
    }

    @Test
    @Rollback(false)
    public void testFindTboxPersonalConfigByVin(){
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDaoService.findTboxPersonalConfigByVin(vin);
        Assert.assertNotNull(tboxPersonalConfig);
    }

}
