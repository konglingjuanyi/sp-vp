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
 * com.zxq.iov.cloud.sp.vp.dao.svt.StolenAlarmDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.svt;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 被盗警告数据访问测试类
 */
@Transactional
public class StolenAlarmDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(StolenAlarmDaoImplTest.class);

	@Autowired
	private IStolenAlarmDao stolenAlarmDao;

	@Test
	@Rollback(false)
	public void testCreateStolenAlarm() {
		Long tboxId = 1L;
		StolenAlarm stolenAlarm = new StolenAlarm();
		stolenAlarm.setAlarmType(1);
		stolenAlarm.setTboxId(tboxId);
		stolenAlarm.setAlarmTime(new Date());
		stolenAlarm = stolenAlarmDao.createStolenAlarm(stolenAlarm);
		Assert.assertNotNull(stolenAlarm);
	}

	@Test
	@Rollback(false)
	public void testUpdateStolenAlarm() {
		Long stolenAlarmId = 5L;
		StolenAlarm stolenAlarm = stolenAlarmDao.findStolenAlarmById(stolenAlarmId);
		stolenAlarm.setAlarmData("1");
		stolenAlarm = stolenAlarmDao.updateStolenAlarm(stolenAlarm);
		Assert.assertNotNull(stolenAlarm);
		LOGGER.info("stolenAlarm's alarmData = " + stolenAlarm.getAlarmData());
	}

	@Test
	@Rollback(false)
	public void testRemoveStolenAlarm() {
		Long stolenAlarmId = 5L;
		stolenAlarmDao.removeStolenAlarm(stolenAlarmId);
	}

}
