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
 * modify date 2015-6-23 10:02
 * @version 0.2, 2015-6-23
 */
@Transactional
public class TboxConfigServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("tboxConfigServiceProxy")
    private ITboxConfigService tboxConfigService;

    @Test
    @Rollback(false)
    public void testRequestConfigUpdate() {
        Long tboxId = 1L;
        tboxConfigService.requestConfigUpdate(tboxId);
    }

    @Test
    @Rollback(false)
    public void testResponseConfigUpdate() {
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 4);
        tboxConfigService.responseConfigUpdate(otaDto, true);
    }

    @Test
    @Rollback(false)
    public void testCheckConfigDelta() {
        Integer mcuVersion = 1;
        Integer mpuVersion = 1;
        Integer configVersion = 1;
        Integer configDelta = 1;
        String iccid = "1";
        String vin = "1";
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 1);
        tboxConfigService.checkConfigDelta(mcuVersion, mpuVersion, configVersion, configDelta,
                iccid, vin, otaDto);
    }

    @Test
    @Rollback(false)
    public void testGetConfigPackage() {
        Long tboxId = 1L;
        Integer packageId = 1;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 6);
        TboxConfigPackageDto tboxConfigPackageDto = tboxConfigService.getConfigPackage(otaDto, packageId);
        Assert.assertNotNull(tboxConfigPackageDto);
    }

    @Test
    @Rollback(false)
    public void testCloseConfigUpdate() {
        Long tboxId = 1L;
        Boolean result = true;
        Integer mcuVersion = 1;
        Integer mpuVersion = 1;
        Integer configVersion = 1;
        Integer configDelta = 1;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 5);
        tboxConfigService.closeConfigUpdate(otaDto, result, mcuVersion, mpuVersion, configVersion, configDelta);
    }

    @Test
    @Rollback(false)
    public void testRequestReadConfig() {
        Long tboxId = 1L;
        Long[] tboxConfigsettingIds = {1L, 2L};
        tboxConfigService.requestReadConfig(tboxId, tboxConfigsettingIds);
    }

    @Test
    @Rollback(false)
    public void testResponseReadConfig() {
        Long tboxId = 1L;
        String tboxConfigSettings = "";
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 9);
        tboxConfigService.responseReadConfig(otaDto, tboxConfigSettings);
    }

    @Test
    @Rollback(false)
    public void testGenerateAsymmetricKey() {
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 10);
        tboxConfigService.generateAsymmetricKey(otaDto);
    }

    @Test
    @Rollback(false)
    public void testBindTboxWithSecretKey() {
        Long tboxId = 1L;
        KeyDto keyDto = new KeyDto();
        keyDto.setTboxId(tboxId);
        keyDto.setAid(Constants.AID_CONFIGURATION);
        keyDto.setMid(12);
        keyDto.setEventCreateTime(new Date());
        tboxConfigService.bindTboxWithSecretKey(keyDto);
    }

}
