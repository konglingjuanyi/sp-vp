package com.zxq.iov.cloud.sp.vp.service.event;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventInstanceDaoService;
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
 * modify date 2015-6-8 13:34
 * @version 0.2, 2015-6-8
 */
@Transactional
public class EventInstanceDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventInstanceDaoService eventInstanceDaoService;

    @Test
    @Rollback(true)
    public void testCreateEventInstance(){
        Long eventDefinitionId = 8L;
        EventInstance eventInstance = new EventInstance();
        eventInstance.setEventDefinitionId(eventDefinitionId);
        eventInstance.setOwner("V082342");
        eventInstance = eventInstanceDaoService.createEventInstance(eventInstance);
        Assert.assertNotNull(eventInstance);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventInstance(){
        Long eventInstanceId = 8L;
        EventInstance eventInstance = eventInstanceDaoService.findEventInstanceById(eventInstanceId);
        eventInstance.setStartTime(new Date());
        eventInstance = eventInstanceDaoService.updateEventInstance(eventInstance);
        Assert.assertNotNull(eventInstance);
    }

    @Test
    @Rollback(true)
    public void testRemoveEventInstance() {
        Long eventInstanceId = 8L;
        eventInstanceDaoService.removeEventInstance(eventInstanceId);
    }

    @Test
    @Rollback(true)
    public void testListEventInstanceByEventDefinitionId() {
        Long eventInstanceId = 8L;
        Integer runningStatus = 1;
        List<EventInstance> list =  eventInstanceDaoService.listEventInstanceByEventDefinitionId(eventInstanceId, runningStatus);
        Assert.assertTrue(list.size()>0);
    }

}
