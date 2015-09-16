/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.impl.TboxPersonalConfigDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxPersonalConfigDao;
import com.zxq.iov.cloud.sp.vp.dao.config.repo.ITboxPersonalConfigRepository;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 TBOX个性化配置数据访问接口实现类
 */
@Service
public class TboxPersonalConfigDaoImpl extends BaseServiceImpl<ITboxPersonalConfigRepository, TboxPersonalConfig, Long>
		implements ITboxPersonalConfigDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxPersonalConfigDaoImpl.class);

	@Autowired
	public TboxPersonalConfigDaoImpl(ITboxPersonalConfigRepository repo) {
		super(repo);
	}

	@Override
	public TboxPersonalConfig createTboxPersonalConfig(TboxPersonalConfig tboxPersonalConfig) {
		if (tboxPersonalConfig == null) {
			LOGGER.error("tboxPersonalConfig cannot be null");
		}
		tboxPersonalConfig.setId(null);
		super.save(tboxPersonalConfig);
		return tboxPersonalConfig;
	}

	@Override
	public TboxPersonalConfig updateTboxPersonalConfig(TboxPersonalConfig tboxPersonalConfig) {
		if (tboxPersonalConfig == null) {
			LOGGER.error("tboxPersonalConfig cannot be null");
		}
		super.update(tboxPersonalConfig);
		return tboxPersonalConfig;
	}

	@Override
	public void removeTboxPersonalConfig(Long tboxPersonalConfigId) {
		if (tboxPersonalConfigId == null) {
			LOGGER.error("tboxPersonalConfigId cannot be null");
		}
		super.delete(tboxPersonalConfigId);
	}

	@Override
	public TboxPersonalConfig findTboxPersonalConfigById(Long tboxPersonalConfigId) {
		if (tboxPersonalConfigId == null) {
			LOGGER.error("tboxPersonalConfigId cannot be null");
		}
		return super.findOne(tboxPersonalConfigId);
	}

	@Override
	public TboxPersonalConfig findTboxPersonalConfigByTboxId(Long tboxId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxId", tboxId);
		List<TboxPersonalConfig> list = super.findListViaBatis(paramMap);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public TboxPersonalConfig findTboxPersonalConfigByVin(String vin) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		List<TboxPersonalConfig> list = super.findListViaBatis(paramMap);
		return list.size() > 0 ? list.get(0) : null;
	}
}