package com.zxq.iov.cloud.sp.vp.dao.svt;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 被盗警告持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-15 12:10
 * modify date
 * @version 0.1, 2015-6-15
 */
@Transactional
public class StolenAlarmDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private IStolenAlarmDao stolenAlarmDao;

    @Test
    @Rollback(false)
    public void testCreateStolenAlarm(){
        Long tboxId = 1L;
        StolenAlarm stolenAlarm = new StolenAlarm();
        stolenAlarm.setAlarmType(1);
        stolenAlarm.setTboxId(tboxId);
        stolenAlarm.setAlarmTime(new Date());
        stolenAlarm = stolenAlarmDao.createStolenAlarm(stolenAlarm);
        Assert.assertNotNull(stolenAlarm);
    }

    @Test
    @Rollback(false)
    public void testUpdateStolenAlarm(){
        Long stolenAlarmId = 5L;
        StolenAlarm stolenAlarm = stolenAlarmDao.findStolenAlarmById(stolenAlarmId);
        stolenAlarm.setAlarmData("1");
        stolenAlarm = stolenAlarmDao.updateStolenAlarm(stolenAlarm);
        Assert.assertNotNull(stolenAlarm);
    }

    @Test
    @Rollback(false)
    public void testRemoveStolenAlarm(){
        Long stolenAlarmId = 5L;
        stolenAlarmDao.removeStolenAlarm(stolenAlarmId);
    }

}
