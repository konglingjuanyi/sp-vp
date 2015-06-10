package com.zxq.iov.cloud.sp.vp.dao.journey.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDaoService;
import com.zxq.iov.cloud.sp.vp.dao.journey.repo.IJourneyRepository;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 车辆信息持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-9 13:44
 * modify date
 * @version 0.1, 2015-6-9
 */
@Service
public class JourneyDaoServiceImpl extends BaseServiceImpl<IJourneyRepository, Journey, Long> implements IJourneyDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JourneyDaoServiceImpl.class);

    @Autowired
	public JourneyDaoServiceImpl(IJourneyRepository repo){
		super(repo);
	}

	@Override
	public Journey createJourney(Journey journey) {
		if (journey == null){
			LOGGER.error("journey cannot be null");
		}
		journey.setId(null);
		super.save(journey);
		return journey;
	}

	@Override
	public Journey updateJourney(Journey journey) {
		if (journey == null){
			LOGGER.error("journey cannot be null");
		}
		super.update(journey);
		return journey;
	}

	@Override
	public void removeJourney(Long journeyId) {
		if (journeyId == null){
			LOGGER.error("journeyId cannot be null");
		}
		super.delete(journeyId);
	}

	@Override
	public Journey findJourneyById(Long journeyId) {
		if (journeyId == null){
			LOGGER.error("journeyId cannot be null");
		}
		return super.findOne(journeyId);
	}

	@Override
	public Journey findJourneyByTboxJourneyId(Integer tboxJourneyId) {
		if (tboxJourneyId == null){
			LOGGER.error("tboxJourneyId cannot be null");
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxJourneyId", tboxJourneyId);
		List<Journey> list = super.findListViaBatis(paramMap);
		return (list.size()>0)?list.get(0):null;
	}
}