package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IStepDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IEventParameterDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 事件参数持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-4-29 8:56
 * @version 0.1, 2015-4-29
 */
@Transactional
public class EventParameterDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventDaoService eventDaoService;
    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private IStepDaoService stepDaoService;
    @Autowired
    private IEventParameterDaoService eventParameterDaoService;

    @Test
    @Rollback(true)
    public void testCreateEventParameter(){
        Long eventId = 22L;
        Long taskId = 14L;
        Long stepId = 1L;
        EventParameter eventParameter = new EventParameter();
        eventParameter.setEvent(eventDaoService.findEventById(eventId));
        eventParameter.setTask(taskDaoService.findTaskById(taskId));
        eventParameter.setStep(stepDaoService.findStepById(stepId));
        eventParameter.setName("a");
        eventParameter.setValue("1");
        eventParameter = eventParameterDaoService.createEventParameter(eventParameter);
        Assert.assertNotNull(eventParameter);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventParameter(){
        Long eventParameterId = 2L;
        EventParameter eventParameter = eventParameterDaoService.findEventParameterById(eventParameterId);
        eventParameter.setValue("2");
        eventParameter = eventParameterDaoService.updateEventParameter(eventParameter);
        Assert.assertNotNull(eventParameter);
    }

    @Test
    @Rollback(true)
    public void testFindEventParameterByName(){
        String name = "a";
        Long eventId = null;
        Long taskId = null;
        Long stepId = 2L;
        EventParameter eventParameter = eventParameterDaoService.findEventParameterByName(name, eventId, taskId, stepId);
        Assert.assertNotNull(eventParameter);
    }

}
