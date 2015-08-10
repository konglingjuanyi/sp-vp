package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
public class EventParameterDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventParameterDao eventParameterDao;

    @Test
    @Rollback(false)
    public void testCreateEventParameter(){
        Long stepInstanceId = 9L;
        EventParameter eventParameter = new EventParameter();
        eventParameter.setType(1);
        eventParameter.setStepInstanceId(stepInstanceId);
        eventParameter = eventParameterDao.createEventParameter(eventParameter);
        Assert.assertNotNull(eventParameter);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventParameter(){
        Long eventParameterId = 15L;
        EventParameter eventParameter = eventParameterDao.findEventParameterById(eventParameterId);
        eventParameter.setName("result");
        eventParameter.setValue("");
        eventParameter = eventParameterDao.updateEventParameter(eventParameter);
        Assert.assertNotNull(eventParameter);
    }

    @Test
    @Rollback(false)
    public void testRemoveEventParameter() {
        Long eventParameterId = 15L;
        eventParameterDao.removeEventParameter(eventParameterId);
    }

}
