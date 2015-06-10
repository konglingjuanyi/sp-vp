package com.zxq.iov.cloud.sp.vp.service.event;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventParameterDaoService;
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
 * create date 2015-6-8 16:35
 * modify date
 * @version 0.1, 2015-6-8
 */
@Transactional
public class EventParameterDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventParameterDaoService eventParameterDaoService;

    @Test
    @Rollback(false)
    public void testCreateEventParameter(){
        Long stepInstanceId = 9L;
        EventParameter eventParameter = new EventParameter();
        eventParameter.setType(1);
        eventParameter.setStepInstanceId(stepInstanceId);
        eventParameter = eventParameterDaoService.createEventParameter(eventParameter);
        Assert.assertNotNull(eventParameter);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventParameter(){
        Long eventParameterId = 15L;
        EventParameter eventParameter = eventParameterDaoService.findEventParameterById(eventParameterId);
        eventParameter.setName("result");
        eventParameter.setValue("");
        eventParameter = eventParameterDaoService.updateEventParameter(eventParameter);
        Assert.assertNotNull(eventParameter);
    }

    @Test
    @Rollback(false)
    public void testRemoveEventParameter() {
        Long eventParameterId = 15L;
        eventParameterDaoService.removeEventParameter(eventParameterId);
    }

}
