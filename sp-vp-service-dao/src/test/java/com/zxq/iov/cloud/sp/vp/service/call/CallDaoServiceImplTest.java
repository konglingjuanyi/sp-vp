package com.zxq.iov.cloud.sp.vp.service.call;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDaoService;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 呼叫持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-11 9:50
 * modify date
 * @version 0.1, 2015-6-11
 */
@Transactional
public class CallDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ICallDaoService callDaoService;

    @Test
    @Rollback(false)
    public void testCreateCall(){
        Long tboxId = 1L;
        Integer type = 1;
        Call call = new Call();
        call.setStartTime(new Date());
        call.setTboxId(tboxId);
        call.setType(type);
        call = callDaoService.createCall(call);
        Assert.assertNotNull(call);
    }

    @Test
    @Rollback(false)
    public void testUpdateCall(){
        Long callId = 5L;
        Call call = callDaoService.findCallById(callId);
        call.setCallType(1);
        call = callDaoService.updateCall(call);
        Assert.assertNotNull(call);
    }

    @Test
    @Rollback(false)
    public void testRemoveCall(){
        Long callId = 5L;
        callDaoService.removeCall(callId);
    }

}
