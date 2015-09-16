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
 * com.zxq.iov.cloud.sp.vp.dao.journey.JourneyDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.journey;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 行程数据访问测试类
 */
@Transactional
public class JourneyDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(JourneyDaoImplTest.class);

	@Autowired
	private IJourneyDao journeyDao;

	@Test
	@Rollback(false)
	public void testCreateJourney() {
		Long ownerId = 1L;
		Integer tboxJourneyId = 1;
		Long tboxId = 1L;
		Journey journey = new Journey();
		journey.setOwnerId(ownerId);
		journey.setTboxJourneyId(tboxJourneyId);
		journey.setTboxId(tboxId);
		journey = journeyDao.createJourney(journey);
		Assert.assertNotNull(journey);
	}

	@Test
	@Rollback(false)
	public void testUpdateJourney() {
		Long journeyId = 25L;
		Journey journey = journeyDao.findJourneyById(journeyId);
		journey.setVin("001");
		journey = journeyDao.updateJourney(journey);
		Assert.assertNotNull(journey);
		LOGGER.info("journey's vin = " + journey.getVin());
	}

	@Test
	@Rollback(false)
	public void testRemoveJourney() {
		Long journeyId = 25L;
		journeyDao.removeJourney(journeyId);
	}

	@Test
	@Rollback(false)
	public void testFindJourneyByTboxJourneyId() {
		Integer tboxJourneyId = 1;
		Long tboxId = 1L;
		Journey journey = journeyDao.findJourneyByTboxJourneyIdAndTboxId(tboxJourneyId, tboxId);
		Assert.assertNotNull(journey);
	}
}
