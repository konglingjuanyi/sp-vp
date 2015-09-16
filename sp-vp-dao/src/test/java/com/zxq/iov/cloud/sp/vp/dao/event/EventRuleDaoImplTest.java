/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.EventRuleDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防服务 事件规则数据访问测试类
 */
@Transactional
public class EventRuleDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventRuleDaoImplTest.class);

	@Autowired
	private IEventRuleDao eventRuleDao;

	@Test
	@Rollback(true)
	public void testCreateEventRule() {
		Long stepDefinitionId = 9L;
		EventRule eventRule = new EventRule();
		eventRule.setStepDefinitionId(stepDefinitionId);
		eventRule.setName("type");
		eventRule.setOperator("eq");
		eventRule = eventRuleDao.createEventRule(eventRule);
		Assert.assertNotNull(eventRule);
	}

	@Test
	@Rollback(true)
	public void testUpdateEventRule() {
		Long eventRuleId = 4L;
		EventRule eventRule = eventRuleDao.findEventRuleById(eventRuleId);
		eventRule.setValue("1");
		eventRule = eventRuleDao.updateEventRule(eventRule);
		Assert.assertNotNull(eventRule);
		LOGGER.info("eventRule's value = " + eventRule.getValue());
	}

	@Test
	@Rollback(true)
	public void testRemoveEventRule() {
		Long eventRuleId = 4L;
		eventRuleDao.removeEventRule(eventRuleId);
	}

	@Test
	@Rollback(true)
	public void testListEventRulenByStepDefinitionId() {
		Long stepDefinitionId = 9L;
		List<EventRule> list = eventRuleDao.listEventRuleByStepDefinitionId(stepDefinitionId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

}
