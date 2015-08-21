package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigSettingDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 远程配置服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-19 14:17
 * modify date 2015-7-22 18:04
 * @version 0.4, 2015-7-22
 */
@Transactional
public class TboxConfigApimplTest extends BaseServiceTestCase {

    @Autowired
    private ITboxConfigApi tboxConfigApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestConfigUpdate() throws Exception {
        tboxConfigApi.requestConfigUpdate(vin);
    }

    @Test
    @Rollback(false)
    public void testResponseConfigUpdate() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 4);
        tboxConfigApi.responseConfigUpdate(otaDto, true);
    }

    @Test
    @Rollback(false)
    public void testCheckConfigDelta() throws Exception {
        String mcuVersion = "01";
        String mpuVersion = "01";
        String configVersion = "01";
        Integer configDelta = 1;
        String iccid = "1";
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 1);
        //otaDto.setEventId(1L);
        TboxConfigDto tboxConfigDto = tboxConfigApi.checkConfigDelta(otaDto, BinaryAndHexUtil.hexStringToByte(mcuVersion),
                BinaryAndHexUtil.hexStringToByte(mpuVersion), vin, iccid,
                BinaryAndHexUtil.hexStringToByte(configVersion), configDelta);
        System.out.print(tboxConfigDto.getPackageCount());
    }

    @Test
    @Rollback(false)
    public void testGetConfigPackage() throws Exception {
        Integer packageId = 1;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 6);
        otaDto.setEventId(1L);
        TboxConfigPackageDto tboxConfigPackageDto = tboxConfigApi.getConfigPackage(otaDto, packageId);
        Assert.assertNotNull(tboxConfigPackageDto);
    }

    @Test
    @Rollback(false)
    public void testCloseConfigUpdate() throws Exception {
        Boolean result = true;
        String mcuVersion = "1";
        String mpuVersion = "1";
        String configVersion = "1";
        Integer configDelta = 1;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 5);
        tboxConfigApi.closeConfigUpdate(otaDto, result, BinaryAndHexUtil.hexStringToByte(mcuVersion),
                BinaryAndHexUtil.hexStringToByte(mpuVersion),
                BinaryAndHexUtil.hexStringToByte(configVersion), configDelta);
    }

    @Test
    @Rollback(false)
    public void testRequestReadConfig() throws Exception {
        Long[] tboxConfigsettingIds = {1L, 2L};
        tboxConfigApi.requestReadConfig(vin, tboxConfigsettingIds);
    }

    @Test
    @Rollback(false)
    public void testResponseReadConfig() throws Exception {
        List<TboxConfigSettingDto> tboxConfigSettingDtos = new ArrayList<>();
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 9);
        tboxConfigApi.responseReadConfig(otaDto, tboxConfigSettingDtos);
    }

    @Test
    @Rollback(false)
    public void testGenerateAsymmetricKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 10);
        tboxConfigApi.generateAsymmetricKey(otaDto);
    }

    @Test
    @Rollback(false)
    public void testBindTboxWithSecretKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 12);
        String secretKeyWithEnc = "1";
        String tboxSnWithEnc = "1";
        tboxConfigApi.bindTboxWithSecretKey(otaDto, BinaryAndHexUtil.hexStringToByte(secretKeyWithEnc),
                BinaryAndHexUtil.hexStringToByte(tboxSnWithEnc));
    }

}
