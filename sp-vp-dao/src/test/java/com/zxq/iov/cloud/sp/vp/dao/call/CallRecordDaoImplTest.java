package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
public class CallRecordDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private ICallRecordDao callRecordDao;

    @Test
    @Rollback(false)
    public void testCreateCallRecord(){
        Long callId = 46L;
        String callNumber = "4008888888";
        CallRecord callRecord = new CallRecord();
        callRecord.setCallId(callId);
        callRecord.setCallNumber(callNumber);
        callRecord = callRecordDao.createCallRecord(callRecord);
        Assert.assertNotNull(callRecord);
    }

    @Test
    @Rollback(false)
    public void testUpdateCallRecord(){
        Long callRecordId = 4L;
        CallRecord callRecord = callRecordDao.findCallRecordById(callRecordId);
        callRecord.setCallTime(new Date());
        callRecord = callRecordDao.updateCallRecord(callRecord);
        Assert.assertNotNull(callRecord);
    }

    @Test
    @Rollback(false)
    public void testRemoveCallRecord(){
        Long callRecordId = 4L;
        callRecordDao.removeCallRecord(callRecordId);
    }

}
