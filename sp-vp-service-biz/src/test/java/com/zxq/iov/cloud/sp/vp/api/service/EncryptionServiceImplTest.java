package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.TboxDto;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 加密服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-5 14:10:07
 * @version 0.1, 2015-5-5
 */
@Transactional
public class EncryptionServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("encryptionServiceProxy")
    private IEncryptionService encryptionService;

    @Test
    @Rollback(false)
    public void testGenerateAsymmetricKey() {
        TboxDto tboxDto = new TboxDto();
        tboxDto.setAid("100");
        tboxDto.setMid(11);
        tboxDto.setVin("111");
        tboxDto.setTbox("001");
        KeyDto keyDto = encryptionService.generateAsymmetricKey(tboxDto);
        Assert.assertNotNull(keyDto);
    }

    @Test
    @Rollback(false)
    public void testBindTboxWithSecretKey() {
        TboxDto tboxDto = new TboxDto();
        tboxDto.setAid("100");
        tboxDto.setMid(13);
        tboxDto.setVin("111");
        tboxDto.setTbox("001");
        tboxDto.setTaskId(21L);
        KeyDto keyDto = new KeyDto();
        encryptionService.bindTboxWithSecretKey(tboxDto, keyDto);
    }

}
