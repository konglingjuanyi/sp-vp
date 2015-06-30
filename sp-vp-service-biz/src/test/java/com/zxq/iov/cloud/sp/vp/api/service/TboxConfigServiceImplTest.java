package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 远程配置服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-19 14:17
 * modify date 2015-6-29 13:07
 * @version 0.3, 2015-6-29
 */
@Transactional
public class TboxConfigServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("tboxConfigServiceProxy")
    private ITboxConfigService tboxConfigService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestConfigUpdate() {
        tboxConfigService.requestConfigUpdate(vin);
    }

    @Test
    @Rollback(false)
    public void testResponseConfigUpdate() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 4);
        tboxConfigService.responseConfigUpdate(otaDto, true);
    }

    @Test
    @Rollback(false)
    public void testCheckConfigDelta() {
        String mcuVersion = "1";
        String mpuVersion = "1";
        String configVersion = "1";
        Integer configDelta = 1;
        String iccid = "1";
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 1);
        tboxConfigService.checkConfigDelta(otaDto, mcuVersion, mpuVersion, vin, iccid, configVersion, configDelta);
    }

    @Test
    @Rollback(false)
    public void testGetConfigPackage() {
        Integer packageId = 1;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 6);
        TboxConfigPackageDto tboxConfigPackageDto = tboxConfigService.getConfigPackage(otaDto, packageId);
        Assert.assertNotNull(tboxConfigPackageDto);
    }

    @Test
    @Rollback(false)
    public void testCloseConfigUpdate() {
        Boolean result = true;
        String mcuVersion = "1";
        String mpuVersion = "1";
        String configVersion = "1";
        Integer configDelta = 1;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 5);
        tboxConfigService.closeConfigUpdate(otaDto, result, mcuVersion, mpuVersion, configVersion, configDelta);
    }

    @Test
    @Rollback(false)
    public void testRequestReadConfig() {
        Long[] tboxConfigsettingIds = {1L, 2L};
        tboxConfigService.requestReadConfig(vin, tboxConfigsettingIds);
    }

    @Test
    @Rollback(false)
    public void testResponseReadConfig() {
        String tboxConfigSettings = "";
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 9);
        tboxConfigService.responseReadConfig(otaDto, tboxConfigSettings);
    }

    @Test
    @Rollback(false)
    public void testGenerateAsymmetricKey() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 10);
        tboxConfigService.generateAsymmetricKey(otaDto);
    }

    @Test
    @Rollback(false)
    public void testBindTboxWithSecretKey() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 12);
        String secretKeyWithEnc = "1";
        String tboxSnWithEnc = "1";
        tboxConfigService.bindTboxWithSecretKey(otaDto, secretKeyWithEnc, tboxSnWithEnc);
    }

}
