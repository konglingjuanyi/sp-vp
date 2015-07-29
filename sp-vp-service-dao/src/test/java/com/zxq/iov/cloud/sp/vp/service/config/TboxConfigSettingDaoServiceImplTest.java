package com.zxq.iov.cloud.sp.vp.service.config;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxConfigSettingDaoService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 TBOX配置参数持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-7-29 12:29
 * modify date
 * @version 0.1, 2015-7-29
 */
@Transactional
public class TboxConfigSettingDaoServiceImplTest extends BaseServiceTestCase {

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private ITboxConfigSettingDaoService tboxConfigSettingDaoService;

    @Test
    @Rollback(false)
    public void testCreateTboxConfigSetting(){
        Long tboxConfigId = 1L;
        Integer keyId = 2;
        String value = "02";
        Integer configDelter = 1;
        TboxConfigSetting tboxConfigSetting = new TboxConfigSetting(tboxConfigId, tboxId, keyId, value, configDelter);
        tboxConfigSetting = tboxConfigSettingDaoService.createTboxConfigSetting(tboxConfigSetting);
        Assert.assertNotNull(tboxConfigSetting);
    }

    @Test
    @Rollback(false)
    public void testUpdateTboxConfigSetting(){
        Long tboxConfigSettingId = 3L;
        TboxConfigSetting tboxConfigSetting = tboxConfigSettingDaoService.findTboxConfigSettingById(tboxConfigSettingId);
        tboxConfigSetting.setConfigDelta(2);
        tboxConfigSetting = tboxConfigSettingDaoService.updateTboxConfigSetting(tboxConfigSetting);
        Assert.assertNotNull(tboxConfigSetting);
    }

    @Test
    @Rollback(false)
    public void testRemoveTboxConfigSetting(){
        Long tboxConfigSettingId = 3L;
        tboxConfigSettingDaoService.removeTboxConfigSetting(tboxConfigSettingId);
    }

    @Test
    @Rollback(false)
    public void testListTboxConfigSettingByTboxConfigId(){
        Long tboxConfigId = 1L;
        PageResult<TboxConfigSetting> pageResult = new PageResult<>();
        List<TboxConfigSetting> list = tboxConfigSettingDaoService.listTboxConfigSettingByTboxConfigId(pageResult,
                tboxConfigId).getResult();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(false)
    public void testListTboxConfigSettingByTboxId(){
        System.out.println(23%10);
        List<TboxConfigSetting> list = tboxConfigSettingDaoService.listTboxConfigSettingByTboxId(tboxId);
        Assert.assertTrue(list.size() > 0);
    }

}
