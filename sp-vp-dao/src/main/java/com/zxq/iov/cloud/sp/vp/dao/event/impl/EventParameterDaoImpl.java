package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventParameterDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventParameterRepository;
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
 * create date 2015-6-8 16:05
 * modify date 2015-8-11 15:20
 * @version 0.2, 2015-8-11
 */
@Service
public class EventParameterDaoImpl extends BaseServiceImpl<IEventParameterRepository, EventParameter, Long> implements IEventParameterDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventParameterDaoImpl.class);

    @Autowired
	public EventParameterDaoImpl(IEventParameterRepository repo){
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

	@Override
	public EventParameter findEventParameterByTypeAndStepIdAndName(Integer type,
																   Long stepInstanceId, String name) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("type", type);
		paramMap.put("stepInstanceId", stepInstanceId);
		paramMap.put("name", name);
		List<EventParameter> list = super.findListViaBatis(paramMap);
		return list.size()>0?list.get(0):null;
	}
}