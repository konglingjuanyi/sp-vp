/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.svt.impl.StolenAlarmDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.svt.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDao;
import com.zxq.iov.cloud.sp.vp.dao.svt.repo.IStolenAlarmRepository;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防服务 被盗警报数据访问接口实现类
 */
@Service
public class StolenAlarmDaoImpl extends BaseServiceImpl<IStolenAlarmRepository, StolenAlarm, Long>
		implements IStolenAlarmDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(StolenAlarmDaoImpl.class);

	@Autowired
	public StolenAlarmDaoImpl(IStolenAlarmRepository repo) {
		super(repo);
	}

	@Override
	public StolenAlarm createStolenAlarm(StolenAlarm stolenAlarm) {
		if (stolenAlarm == null) {
			LOGGER.error("stolenAlarm cannot be null");
		}
		stolenAlarm.setId(null);
		super.save(stolenAlarm);
		return stolenAlarm;
	}

	@Override
	public StolenAlarm updateStolenAlarm(StolenAlarm stolenAlarm) {
		if (stolenAlarm == null) {
			LOGGER.error("stolenAlarm cannot be null");
		}
		super.update(stolenAlarm);
		return stolenAlarm;
	}

	@Override
	public void removeStolenAlarm(Long stolenAlarmId) {
		if (stolenAlarmId == null) {
			LOGGER.error("stolenAlarmId cannot be null");
		}
		super.delete(stolenAlarmId);
	}

	@Override
	public StolenAlarm findStolenAlarmById(Long stolenAlarmId) {
		if (stolenAlarmId == null) {
			LOGGER.error("stolenAlarmId cannot be null");
		}
		return super.findOne(stolenAlarmId);
	}

}