/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-25       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.EventInstanceDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
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
 * 安防服务 事件实例数据访问测试类
 */
@Transactional
public class EventInstanceDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventInstanceDaoImplTest.class);

	@Autowired
	private IEventInstanceDao eventInstanceDao;

	@Test
	@Rollback(true)
	public void testCreateEventInstance() {
		Long eventDefinitionId = 8L;
		EventInstance eventInstance = new EventInstance();
		eventInstance.setEventDefinitionId(eventDefinitionId);
		eventInstance.setOwner("V082342");
		eventInstance = eventInstanceDao.createEventInstance(eventInstance);
		Assert.assertNotNull(eventInstance);
	}

	@Test
	@Rollback(true)
	public void testUpdateEventInstance() {
		Long eventInstanceId = 8L;
		EventInstance eventInstance = eventInstanceDao.findEventInstanceById(eventInstanceId);
		eventInstance.setStartTime(new Date());
		eventInstance = eventInstanceDao.updateEventInstance(eventInstance);
		Assert.assertNotNull(eventInstance);
		LOGGER.info("eventInstance's startTime = " + eventInstance.getStartTime());
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
		List<EventInstance> list = eventInstanceDao
				.listEventInstanceByEventDefinitionId(eventInstanceId, owner, runningStatus);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

}
