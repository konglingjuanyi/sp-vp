package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 电子钥匙服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-23 14:54
 * modify date 2015-8-5 17:18
 * @version 0.3, 2015-8-5
 */
@Transactional
public class RemoteKeyApiImplTest extends BaseServiceTestCase {

    @Autowired
    private IRemoteKeyApi remoteKeyApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testRequestWriteKey() throws Exception {
        Integer keyType = 1;
        String keyValue = "01";
        Integer keyReference = 1;
        remoteKeyApi.requestWriteKey(vin, keyType, keyValue, keyReference, new Date(), new Date());
    }

    @Test
    @Rollback(false)
    public void testResponseWriteKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 2);
        remoteKeyApi.responseWriteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestDeleteKey() throws Exception {
        remoteKeyApi.requestDeleteKey(vin, 1);
    }

    @Test
    @Rollback(false)
    public void testResponseDeleteKey() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 4);
        remoteKeyApi.responseDeleteKey(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testKeyAlarm() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 5);
        remoteKeyApi.keyAlarm(otaDto);
    }
}
