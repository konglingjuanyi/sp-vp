package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventInstanceRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 事件实例持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 10:46
 * modify date 2015-6-24 17:36
 * @version 0.3, 2015-6-24
 */
@Service
public class EventInstanceDaoServiceImpl extends BaseServiceImpl<IEventInstanceRepository, EventInstance, Long> implements IEventInstanceDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventInstanceDaoServiceImpl.class);

    @Autowired
	public EventInstanceDaoServiceImpl(IEventInstanceRepository repo){
		super(repo);
	}

	public EventInstance createEventInstance(EventInstance eventInstance) {
		if (eventInstance == null){
			LOGGER.error("eventInstance cannot be null");
		}
		eventInstance.setId(null);
		super.save(eventInstance);
		super.flush();
		return eventInstance;
	}

	@Override
	public EventInstance updateEventInstance(EventInstance eventInstance) {
		if (eventInstance == null){
			LOGGER.error("eventInstance cannot be null");
		}
		super.update(eventInstance);
		return eventInstance;
	}

	@Override
	public void removeEventInstance(Long eventInstanceId) {
		if (eventInstanceId == null){
			LOGGER.error("eventInstanceId cannot be null");
		}
		super.delete(eventInstanceId);
	}

	@Override
	public EventInstance findEventInstanceById(Long eventInstanceId) {
		if (eventInstanceId == null){
			LOGGER.error("eventInstanceId cannot be null");
		}
		return super.findOne(eventInstanceId);
	}

	@Override
	public List<EventInstance> listEventInstanceByEventDefinitionId(Long eventDefinitionId, String owner, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventDefinitionId", eventDefinitionId);
		paramMap.put("owner", owner);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}