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
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IVehicleDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.IEventRepository;
import com.zxq.iov.cloud.sp.vp.dao.repo.IVehicleRepository;
import com.zxq.iov.cloud.sp.vp.entity.Vehicle;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 事件持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-29 15:01:07
 * @version 0.1, 2015-4-29
 */
@Service
public class EventDaoServiceImpl extends BaseServiceImpl<IEventRepository, Event, Long> implements IEventDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventDaoServiceImpl.class);

    @Autowired
	public EventDaoServiceImpl(IEventRepository repo){
		super(repo);
	}

	public Event createEvent(Event event) {
		if (event == null){
			LOGGER.error("Event cannot be null");
		}
		event.setId(null);
		super.save(event);
		return event;
	}

	@Override
	public Event updateEvent(Event event) {
		if (event == null){
			LOGGER.error("Event cannot be null");
		}
		super.update(event);
		return event;
	}

	@Override
	public Event findEventById(Long eventId) {
		if (eventId == null){
			LOGGER.error("Event ID cannot be null");
		}
		return super.findOne(eventId);
	}
}