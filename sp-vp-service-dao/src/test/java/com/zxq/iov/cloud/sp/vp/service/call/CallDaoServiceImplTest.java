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
import java.util.List;

/**
 * 安防 呼叫持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-11 9:50
 * modify date 2015-7-9 11:03
 * @version 0.3, 2015-7-9
 */
@Transactional
public class CallDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ICallDaoService callDaoService;

    @Test
    @Rollback(false)
    public void testCreateCall(){
        String vin = "1";
        Long tboxId = 1L;
        Integer type = 1;
        Integer callType = 1;
        Integer crashSeverity = 1;
        Assert.assertNotNull(callDaoService.createCall(new Call(vin, tboxId, type, callType, crashSeverity,
                new Date())));
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
        Long callId = 29L;
        callDaoService.removeCall(callId);
    }

    @Test
    @Rollback(false)
    public void testListCallByTboxId(){
        Long tboxId = 1L;
        List<Call> list = callDaoService.listCallByTboxId(tboxId, 1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(false)
    public void testListCallByVin(){
        String vin = "1";
        List<Call> list = callDaoService.listCallByVin(vin, 1);
        Assert.assertTrue(list.size() > 0);
    }
}
