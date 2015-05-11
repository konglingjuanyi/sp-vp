package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 安防 事件持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-4-29 8:56
 * @version 0.1, 2015-4-29
 */
@Transactional
public class EventDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventDaoService eventDaoService;

    @Test
    @Rollback(true)
    public void testCreateEvent(){
        Event event = new Event();
        event.setVin("001");
        event.setTbox("001");
        event.setCode("1");
        event.setStartTime(new Date());
        event = eventDaoService.createEvent(event);
        Assert.assertNotNull(event);
    }

    @Test
    @Rollback(true)
    public void testUpdateEvent(){
        Long eventId = 22L;
        Event event = eventDaoService.findEventById(eventId);
        event.setVin("002");
        event = eventDaoService.updateEvent(event);
        Assert.assertNotNull(event);
    }

    @Test
    @Rollback(true)
    public void testFindActiveEventByTboxAndCode() {
        String tbox = "001";
        String code = "1";
        List<Event> list = eventDaoService.findActiveEventByTboxAndCode(tbox, code);
        System.out.println("=========" + list.size());
    }
}
