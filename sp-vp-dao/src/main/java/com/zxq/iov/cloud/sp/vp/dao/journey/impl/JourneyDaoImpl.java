/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-09       荣杰         1.0            Initial Version
 * 2015-06-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.journey.impl.JourneyDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.journey.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDao;
import com.zxq.iov.cloud.sp.vp.dao.journey.repo.IJourneyRepository;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 行程数据访问接口实现类
 */
@Service
public class JourneyDaoImpl extends BaseServiceImpl<IJourneyRepository, Journey, Long> implements IJourneyDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JourneyDaoImpl.class);

	@Autowired
	public JourneyDaoImpl(IJourneyRepository repo) {
		super(repo);
	}

	@Override
	public Journey createJourney(Journey journey) {
		if (journey == null) {
			LOGGER.error("journey cannot be null");
		}
		journey.setId(null);
		super.save(journey);
		return journey;
	}

	@Override
	public Journey updateJourney(Journey journey) {
		if (journey == null) {
			LOGGER.error("journey cannot be null");
		}
		super.update(journey);
		return journey;
	}

	@Override
	public void removeJourney(Long journeyId) {
		if (journeyId == null) {
			LOGGER.error("journeyId cannot be null");
		}
		super.delete(journeyId);
	}

	@Override
	public Journey findJourneyById(Long journeyId) {
		if (journeyId == null) {
			LOGGER.error("journeyId cannot be null");
		}
		return super.findOne(journeyId);
	}

	@Override
	public Journey findJourneyByTboxJourneyIdAndTboxId(Integer tboxJourneyId, Long tboxId) {
		if (tboxJourneyId == null || tboxId == null) {
			LOGGER.error("tboxJourneyId or tboxId cannot be null");
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxJourneyId", tboxJourneyId);
		paramMap.put("tboxId", tboxId);
		List<Journey> list = super.findListViaBatis(paramMap);
		return list.size() > 0 ? list.get(0) : null;
	}
}