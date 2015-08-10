package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
public class CallDaoImplTest extends BaseServiceTestCase {

    private String vin = "11111111111111111";

    @Autowired
    private ICallDao callDao;

    @Test
    @Rollback(false)
    public void testCreateCall(){
        String vin = "1";
        Long tboxId = 1L;
        Integer type = 1;
        Integer callType = 1;
        Integer crashSeverity = 1;
        Assert.assertNotNull(callDao.createCall(new Call(vin, tboxId, type, callType, crashSeverity,
                new Date())));
    }

    @Test
    @Rollback(false)
    public void testUpdateCall(){
        Long callId = 5L;
        Call call = callDao.findCallById(callId);
        call.setCallType(1);
        call = callDao.updateCall(call);
        Assert.assertNotNull(call);
    }

    @Test
    @Rollback(false)
    public void testRemoveCall(){
        Long callId = 29L;
        callDao.removeCall(callId);
    }

    @Test
    @Rollback(false)
    public void testListCallByTboxId(){
        Long tboxId = 1L;
        List<Call> list = callDao.listCallByTboxId(tboxId, 1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(false)
    public void testListCallByVin(){
        String vin = "1";
        List<Call> list = callDao.listCallByVin(vin, 1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(false)
    public void testListCallByVinAndType(){
        Integer type = 1;
        List<Call> list = callDao.listCallByVinAndType(vin, type, 1);
        Assert.assertTrue(list.size() > 0);
    }
}
