package com.zxq.iov.cloud.sp.vp.service.svt;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDaoService;
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
public class StolenAlarmDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IStolenAlarmDaoService stolenAlarmDaoService;

    @Test
    @Rollback(false)
    public void testCreateStolenAlarm(){
        Long tboxId = 1L;
        StolenAlarm stolenAlarm = new StolenAlarm();
        stolenAlarm.setAlarmType(1);
        stolenAlarm.setTboxId(tboxId);
        stolenAlarm.setAlarmTime(new Date());
        stolenAlarm = stolenAlarmDaoService.createStolenAlarm(stolenAlarm);
        Assert.assertNotNull(stolenAlarm);
    }

    @Test
    @Rollback(false)
    public void testUpdateStolenAlarm(){
        Long stolenAlarmId = 5L;
        StolenAlarm stolenAlarm = stolenAlarmDaoService.findStolenAlarmById(stolenAlarmId);
        stolenAlarm.setAlarmData("1");
        stolenAlarm = stolenAlarmDaoService.updateStolenAlarm(stolenAlarm);
        Assert.assertNotNull(stolenAlarm);
    }

    @Test
    @Rollback(false)
    public void testRemoveStolenAlarm(){
        Long stolenAlarmId = 5L;
        stolenAlarmDaoService.removeStolenAlarm(stolenAlarmId);
    }

}
