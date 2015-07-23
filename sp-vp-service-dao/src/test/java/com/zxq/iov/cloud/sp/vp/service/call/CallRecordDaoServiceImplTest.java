package com.zxq.iov.cloud.sp.vp.service.call;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallRecordDaoService;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 呼叫记录持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-11 10:30
 * modify date
 * @version 0.1, 2015-6-11
 */
@Transactional
public class CallRecordDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ICallRecordDaoService callRecordDaoService;

    @Test
    @Rollback(false)
    public void testCreateCallRecord(){
        Long callId = 6L;
        String callNumber = "4008888888";
        CallRecord callRecord = new CallRecord();
        callRecord.setCallId(callId);
        callRecord.setCallNumber(callNumber);
        callRecord = callRecordDaoService.createCallRecord(callRecord);
        Assert.assertNotNull(callRecord);
    }

    @Test
    @Rollback(false)
    public void testUpdateCallRecord(){
        Long callRecordId = 4L;
        CallRecord callRecord = callRecordDaoService.findCallRecordById(callRecordId);
        callRecord.setCallTime(new Date());
        callRecord = callRecordDaoService.updateCallRecord(callRecord);
        Assert.assertNotNull(callRecord);
    }

    @Test
    @Rollback(false)
    public void testRemoveCallRecord(){
        Long callRecordId = 4L;
        callRecordDaoService.removeCallRecord(callRecordId);
    }

}
