package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 安防 事件实例持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-5 11:03
 * modify date 2015-6-25 10:56
 * @version 0.3, 2015-6-25
 */
@Transactional
public class EventInstanceDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventInstanceDao eventInstanceDao;

    @Test
    @Rollback(true)
    public void testCreateEventInstance(){
        Long eventDefinitionId = 8L;
        EventInstance eventInstance = new EventInstance();
        eventInstance.setEventDefinitionId(eventDefinitionId);
        eventInstance.setOwner("V082342");
        eventInstance = eventInstanceDao.createEventInstance(eventInstance);
        Assert.assertNotNull(eventInstance);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventInstance(){
        Long eventInstanceId = 8L;
        EventInstance eventInstance = eventInstanceDao.findEventInstanceById(eventInstanceId);
        eventInstance.setStartTime(new Date());
        eventInstance = eventInstanceDao.updateEventInstance(eventInstance);
        Assert.assertNotNull(eventInstance);
    }

    @Test
    @Rollback(true)
    public void testRemoveEventInstance() {
        Long eventInstanceId = 8L;
        eventInstanceDao.removeEventInstance(eventInstanceId);
    }

    @Test
    @Rollback(true)
    public void testListEventInstanceByEventDefinitionId() {
        Long eventInstanceId = 8L;
        String owner = "1";
        Integer runningStatus = 1;
        List<EventInstance> list =  eventInstanceDao.listEventInstanceByEventDefinitionId(eventInstanceId,
                owner, runningStatus);
        Assert.assertTrue(list.size()>0);
    }

}
