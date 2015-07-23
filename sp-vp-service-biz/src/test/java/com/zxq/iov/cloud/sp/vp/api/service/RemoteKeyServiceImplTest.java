package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 电子钥匙服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-23 14:54
 * modify date 2015-6-29 16:04
 * @version 0.2, 2015-6-29
 */
@Transactional
public class RemoteKeyServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("remoteKeyServiceProxy")
    private IRemoteKeyService remoteKeyService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestWriteKey() {
        Integer keyType = 1;
        String keyValue = "1";
        Integer keyReference = 1;
        remoteKeyService.requestWriteKey(vin, keyType, keyValue, keyReference, new Date(), new Date());
    }

    @Test
    @Rollback(false)
    public void testResponseWriteKey() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 2);
        remoteKeyService.responseWriteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestDeleteKey() {
        remoteKeyService.requestDeleteKey(vin, 1);
    }

    @Test
    @Rollback(false)
    public void testResponseDeleteKey() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 4);
        remoteKeyService.responseDeleteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testKeyAlarm() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 5);
        remoteKeyService.keyAlarm(otaDto);
    }
}
