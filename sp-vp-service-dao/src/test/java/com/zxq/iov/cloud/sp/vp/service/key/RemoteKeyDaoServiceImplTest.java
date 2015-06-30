package com.zxq.iov.cloud.sp.vp.service.key;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 电子钥匙持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:29
 * modify date
 * @version 0.1, 2015-6-23
 */
@Transactional
public class RemoteKeyDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IRemoteKeyDaoService remoteKeyDaoService;

    @Test
    @Rollback(false)
    public void testCreateRemoteKey(){
        Long tboxId = 1L;
        RemoteKey remoteKey = new RemoteKey();
        remoteKey.setTboxId(tboxId);
        remoteKey.setType(1);
        remoteKey.setValue("1");
        remoteKey = remoteKeyDaoService.createRemoteKey(remoteKey);
        Assert.assertNotNull(remoteKey);
    }

    @Test
    @Rollback(false)
    public void testUpdateRemoteKey(){
        Long remoteKeyId = 4L;
        RemoteKey remoteKey = remoteKeyDaoService.findRemoteKeyById(remoteKeyId);
        remoteKey.setReference(1);
        remoteKey = remoteKeyDaoService.updateRemoteKey(remoteKey);
        Assert.assertNotNull(remoteKey);
    }

    @Test
    @Rollback(false)
    public void testRemoveReomteKey(){
        Long remoteKeyId = 4L;
        remoteKeyDaoService.removeRemoteKey(remoteKeyId);
    }

}
