/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-08       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.EventParameterDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


/**
 * 安防服务 事件参数数据访问测试类
 */
@Transactional
public class EventParameterDaoImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventParameterDaoImplTest.class);

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
        LOGGER.info("eventParameter's name = " + eventParameter.getName());
    }

    @Test
    @Rollback(false)
    public void testRemoveEventParameter() {
        Long eventParameterId = 15L;
        eventParameterDao.removeEventParameter(eventParameterId);
    }

    @Test
    @Rollback(false)
    public void testFindEventParameterByTypeAndStepIdAndName() {
        Integer type = 2;
        Long stepInstanceId = 310L;
        String name = "result";
        EventParameter eventParameter = eventParameterDao.
                findEventParameterByTypeAndStepIdAndName(type, stepInstanceId, name);
        Assert.assertNotNull(eventParameter);
    }

}
