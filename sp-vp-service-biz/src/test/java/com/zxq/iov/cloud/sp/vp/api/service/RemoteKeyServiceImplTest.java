package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 电子钥匙服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-23 14:54
 * modify date
 * @version 0.1, 2015-6-23
 */
@Transactional
public class RemoteKeyServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("remoteKeyServiceProxy")
    private IRemoteKeyService remoteKeyService;

    @Test
    @Rollback(false)
    public void testRequestWriteKey() {
        Long tboxId = 1L;
        RemoteKeyDto remoteKeyDto = new RemoteKeyDto();
        remoteKeyDto.setTboxId(tboxId);
        remoteKeyDto.setType(1);
        remoteKeyDto.setValue(1);
        remoteKeyService.requestWriteKey(remoteKeyDto);
    }

    @Test
    @Rollback(false)
    public void testResponseWriteKey() {
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 2);
        remoteKeyService.responseWriteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestDeleteKey() {
        Long tboxId = 1L;
        remoteKeyService.requestDeleteKey(tboxId, 1);
    }

    @Test
    @Rollback(false)
    public void testResponseDeleteKey() {
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 4);
        remoteKeyService.responseDeleteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testKeyAlarm() {
        Long tboxId = 1L;
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 5);
        remoteKeyService.keyAlarm(otaDto);
    }
}
