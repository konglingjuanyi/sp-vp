package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventParameterDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventParameterRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 安防 事件参数持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-8 16:05
 * modify date
 * @version 0.1, 2015-6-8
 */
@Service
public class EventParameterDaoServiceImpl extends BaseServiceImpl<IEventParameterRepository, EventParameter, Long> implements IEventParameterDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventParameterDaoServiceImpl.class);

    @Autowired
	public EventParameterDaoServiceImpl(IEventParameterRepository repo){
		super(repo);
	}

	public EventParameter createEventParameter(EventParameter eventParameter) {
		if (eventParameter == null){
			LOGGER.error("eventParameter cannot be null");
		}
		eventParameter.setId(null);
		super.save(eventParameter);
		return eventParameter;
	}

	@Override
	public EventParameter updateEventParameter(EventParameter eventParameter) {
		if (eventParameter == null){
			LOGGER.error("eventParameter cannot be null");
		}
		super.update(eventParameter);
		return eventParameter;
	}

	@Override
	public void removeEventParameter(Long eventParameterId) {
		if (eventParameterId == null){
			LOGGER.error("eventParameterId cannot be null");
		}
		super.delete(eventParameterId);
	}

	@Override
	public EventParameter findEventParameterById(Long eventParameterId) {
		if (eventParameterId == null){
			LOGGER.error("eventParameterId cannot be null");
		}
		return super.findOne(eventParameterId);
	}

}