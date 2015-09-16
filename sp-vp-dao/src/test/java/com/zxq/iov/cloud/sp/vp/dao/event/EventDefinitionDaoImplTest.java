/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.EventDefinitionDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


/**
 * 安防服务 事件定义数据访问测试类
 */
@Transactional
public class EventDefinitionDaoImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDefinitionDaoImplTest.class);

    @Autowired
    private IEventDefinitionDao eventDefinitionDao;

    @Test
    @Rollback(true)
    public void testCreateEventDefinition(){
        EventDefinition eventDefinition = new EventDefinition();
        eventDefinition.setName("被盗追踪事件");
        eventDefinition.setIsExclusive(true);
        eventDefinition.setIsContinue(true);
        eventDefinition.setIsRollback(false);
        eventDefinition = eventDefinitionDao.createEventDefinition(eventDefinition);
        Assert.assertNotNull(eventDefinition);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventDefinition(){
        Long eventDefinitionId = 7L;
        EventDefinition eventDefinition = eventDefinitionDao.findEventDefinitionById(eventDefinitionId);
        eventDefinition.setName("被盗追踪事件2");
        eventDefinition = eventDefinitionDao.updateEventDefinition(eventDefinition);
        Assert.assertNotNull(eventDefinition);
        LOGGER.info("eventDefinition's name = " + eventDefinition.getName());
    }

    @Test
    @Rollback(true)
    public void testRemoveEventDefinition() {
        Long eventDefinitionId = 7L;
        eventDefinitionDao.removeEventDefinition(eventDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testPagingEventDefinition() {
        PageResult<EventDefinition> pageResult = new PageResult<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        pageResult = eventDefinitionDao.pagingEventDefinition(pageResult, paramMap);
        Assert.assertTrue(pageResult.getTotalCount()>0);
        LOGGER.info("pageResult totalCount = " + pageResult.getTotalCount());
    }
}
