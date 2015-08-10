package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 远程配置服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-19 14:17
 * modify date 2015-8-6 11:17
 * @version 0.5, 2015-8-6
 */
@Transactional
public class TboxConfigServiceImplTest extends BaseServiceTestCase {

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
        String key = tboxConfigService.generateAsymmetricKey(tboxId);
        Assert.assertNotNull(key);
    }

    @Test
    @Rollback(false)
    public void testBindTboxWithSecretKey() throws Exception {
        String secretKeyWithEnc = "1";
        String tboxSnWithEnc = "1";
        tboxConfigService.bindTboxWithSecretKey(tboxId, BinaryAndHexUtil.hexStringToByte(secretKeyWithEnc),
                BinaryAndHexUtil.hexStringToByte(tboxSnWithEnc));
    }

}