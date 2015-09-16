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
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.EventRuleDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventRuleDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventRuleRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 事件规则数据访问接口实现类
 */
@Service
public class EventRuleDaoImpl extends BaseServiceImpl<IEventRuleRepository, EventRule, Long> implements IEventRuleDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventRuleDaoImpl.class);

	@Autowired
	public EventRuleDaoImpl(IEventRuleRepository repo) {
		super(repo);
	}

	public EventRule createEventRule(EventRule eventRule) {
		if (eventRule == null) {
			LOGGER.error("eventRule cannot be null");
		}
		eventRule.setId(null);
		super.save(eventRule);
		return eventRule;
	}

	@Override
	public EventRule updateEventRule(EventRule eventRule) {
		if (eventRule == null) {
			LOGGER.error("eventRule cannot be null");
		}
		super.update(eventRule);
		return eventRule;
	}

	@Override
	public void removeEventRule(Long eventRuleId) {
		if (eventRuleId == null) {
			LOGGER.error("eventRuleId cannot be null");
		}
		super.delete(eventRuleId);
	}

	@Override
	public EventRule findEventRuleById(Long eventRuleId) {
		if (eventRuleId == null) {
			LOGGER.error("eventRuleId cannot be null");
		}
		return super.findOne(eventRuleId);
	}

	@Override
	public List<EventRule> listEventRuleByStepDefinitionId(Long stepDefinitionId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("stepDefinitionId", stepDefinitionId);
		return super.findListViaBatis(paramMap);
	}

}