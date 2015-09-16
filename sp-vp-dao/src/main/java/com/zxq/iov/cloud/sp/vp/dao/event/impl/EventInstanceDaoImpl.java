/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-24       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.EventInstanceDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventInstanceDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventInstanceRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 事件实例数据访问接口实现类
 */
@Service
public class EventInstanceDaoImpl extends BaseServiceImpl<IEventInstanceRepository, EventInstance, Long>
		implements IEventInstanceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventInstanceDaoImpl.class);

	@Autowired
	public EventInstanceDaoImpl(IEventInstanceRepository repo) {
		super(repo);
	}

	public EventInstance createEventInstance(EventInstance eventInstance) {
		if (eventInstance == null) {
			LOGGER.error("eventInstance cannot be null");
		}
		eventInstance.setId(null);
		super.save(eventInstance);
		super.flush();
		return eventInstance;
	}

	@Override
	public EventInstance updateEventInstance(EventInstance eventInstance) {
		if (eventInstance == null) {
			LOGGER.error("eventInstance cannot be null");
		}
		super.update(eventInstance);
		return eventInstance;
	}

	@Override
	public void removeEventInstance(Long eventInstanceId) {
		if (eventInstanceId == null) {
			LOGGER.error("eventInstanceId cannot be null");
		}
		super.delete(eventInstanceId);
	}

	@Override
	public EventInstance findEventInstanceById(Long eventInstanceId) {
		if (eventInstanceId == null) {
			LOGGER.error("eventInstanceId cannot be null");
		}
		return super.findOne(eventInstanceId);
	}

	@Override
	public List<EventInstance> listEventInstanceByEventDefinitionId(Long eventDefinitionId, String owner,
			Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventDefinitionId", eventDefinitionId);
		paramMap.put("owner", owner);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}