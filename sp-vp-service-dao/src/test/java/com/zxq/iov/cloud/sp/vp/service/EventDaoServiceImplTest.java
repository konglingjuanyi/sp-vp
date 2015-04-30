package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IVehicleDaoService;
import com.zxq.iov.cloud.sp.vp.entity.Vehicle;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-11-27
 * Time: 上午8:56
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
        event.setCode("1");
        event.setStartTime(new Date());
        event = eventDaoService.createEvent(event);
        Assert.assertNotNull(event);
    }

    @Test
    @Rollback(true)
    public void testUpdateEvent(){
        Long eventId = 11L;
        Event event = eventDaoService.findEventById(eventId);
        event.setVin("002");
        event = eventDaoService.updateEvent(event);
        Assert.assertNotNull(event);
    }

}
