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
 * modify date
 * @version 0.1, 2015-6-19
 */
@Transactional
public class TboxPersonalConfigDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITboxPersonalConfigDaoService tboxPersonalConfigDaoService;

    @Test
    @Rollback(false)
    public void testCreateTboxPersonalConfig(){
        Long tboxId = 1L;
        TboxPersonalConfig tboxPersonalConfig = new TboxPersonalConfig();
        tboxPersonalConfig.setTboxId(tboxId);
        tboxPersonalConfig.setConfigDelta(1);
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

}
