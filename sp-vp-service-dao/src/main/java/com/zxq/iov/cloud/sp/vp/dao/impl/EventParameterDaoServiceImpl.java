/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: VehicleServiceImpl.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.service
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.dao.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.IEventParameterDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.IEventParameterRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 事件参数持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-5-4 9:47
 * modify date 2015-5-8 18:08
 * @version 0.2, 2015-5-8
 */
@Service
public class EventParameterDaoServiceImpl extends BaseServiceImpl<IEventParameterRepository, EventParameter, Long> implements IEventParameterDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventParameterDaoServiceImpl.class);

    @Autowired
	public EventParameterDaoServiceImpl(IEventParameterRepository repo){
		super(repo);
	}

	@Override
	public EventParameter createEventParameter(EventParameter eventParameter) {
		if (eventParameter == null){
			LOGGER.error("EventParameter cannot be null");
		}
		eventParameter.setId(null);
		super.save(eventParameter);
		return eventParameter;
	}

	@Override
	public EventParameter updateEventParameter(EventParameter eventParameter) {
		if (eventParameter == null){
			LOGGER.error("EventParameter cannot be null");
		}
		super.update(eventParameter);
		return eventParameter;
	}

	@Override
	public EventParameter findEventParameterById(Long eventParameterId) {
		if (eventParameterId == null){
			LOGGER.error("eventParameterId cannot be null");
		}
		return super.findOne(eventParameterId);
	}

	@Override
	public EventParameter findEventParameterByName(String name, Long eventId, Long taskId, Long stepId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("eventId", eventId);
		paramMap.put("taskId", taskId);
		paramMap.put("stepId", stepId);
		List<EventParameter> list = super.findListViaBatis(paramMap);
		return (list.size()>0)?list.get(0):null;
	}
}