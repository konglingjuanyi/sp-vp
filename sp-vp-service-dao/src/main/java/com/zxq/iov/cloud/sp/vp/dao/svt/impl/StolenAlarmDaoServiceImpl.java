package com.zxq.iov.cloud.sp.vp.dao.svt.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDaoService;
import com.zxq.iov.cloud.sp.vp.dao.svt.repo.IStolenAlarmRepository;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 被盗警报持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-15 12:00
 * modify date
 * @version 0.1, 2015-6-15
 */
@Service
public class StolenAlarmDaoServiceImpl extends BaseServiceImpl<IStolenAlarmRepository, StolenAlarm, Long> implements IStolenAlarmDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StolenAlarmDaoServiceImpl.class);

    @Autowired
	public StolenAlarmDaoServiceImpl(IStolenAlarmRepository repo){
		super(repo);
	}

	@Override
	public StolenAlarm createStolenAlarm(StolenAlarm stolenAlarm) {
		if (stolenAlarm == null){
			LOGGER.error("stolenAlarm cannot be null");
		}
		stolenAlarm.setId(null);
		super.save(stolenAlarm);
		return stolenAlarm;
	}

	@Override
	public StolenAlarm updateStolenAlarm(StolenAlarm stolenAlarm) {
		if (stolenAlarm == null){
			LOGGER.error("stolenAlarm cannot be null");
		}
		super.update(stolenAlarm);
		return stolenAlarm;
	}

	@Override
	public void removeStolenAlarm(Long stolenAlarmId) {
		if (stolenAlarmId == null){
			LOGGER.error("stolenAlarmId cannot be null");
		}
		super.delete(stolenAlarmId);
	}

	@Override
	public StolenAlarm findStolenAlarmById(Long stolenAlarmId) {
		if (stolenAlarmId == null){
			LOGGER.error("stolenAlarmId cannot be null");
		}
		return super.findOne(stolenAlarmId);
	}

}