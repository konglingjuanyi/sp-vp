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
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.EventDefinitionDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventDefinitionDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventDefinitionRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 安防服务 事件定义数据访问接口实现类
 */
@Service
public class EventDefinitionDaoImpl extends BaseServiceImpl<IEventDefinitionRepository, EventDefinition, Long>
		implements IEventDefinitionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventDefinitionDaoImpl.class);

	@Autowired
	public EventDefinitionDaoImpl(IEventDefinitionRepository repo) {
		super(repo);
	}

	public EventDefinition createEventDefinition(EventDefinition eventDefinition) {
		if (eventDefinition == null) {
			LOGGER.error("eventDefinition cannot be null");
		}
		eventDefinition.setId(null);
		super.save(eventDefinition);
		return eventDefinition;
	}

	@Override
	public EventDefinition updateEventDefinition(EventDefinition eventDefinition) {
		if (eventDefinition == null) {
			LOGGER.error("eventDefinition cannot be null");
		}
		super.update(eventDefinition);
		return eventDefinition;
	}

	@Override
	public void removeEventDefinition(Long eventDefinitionId) {
		if (eventDefinitionId == null) {
			LOGGER.error("eventDefinitionId cannot be null");
		}
		super.delete(eventDefinitionId);
	}

	@Override
	public EventDefinition findEventDefinitionById(Long eventDefinitionId) {
		if (eventDefinitionId == null) {
			LOGGER.error("eventDefinitionId cannot be null");
		}
		return super.findOne(eventDefinitionId);
	}

	@Override
	public PageResult<EventDefinition> pagingEventDefinition(PageResult<EventDefinition> pageResult,
			Map<String, Object> paramMap) {
		return super.getPagedListViaBatis(pageResult, paramMap);
	}
}