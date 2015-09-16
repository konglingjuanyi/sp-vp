/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-29       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.TboxConfigSettingDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 TBOX配置参数数据访问测试类
 */
@Transactional
public class TboxConfigSettingDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxConfigSettingDaoImplTest.class);

	private Long tboxId = 1L;
	private Long userId = 1L;
	private String vin = "11111111111111111";

	@Autowired
	private ITboxConfigSettingDao tboxConfigSettingDao;

	@Test
	@Rollback(false)
	public void testCreateTboxConfigSetting() {
		Long tboxConfigId = 1L;
		Integer keyId = 2;
		String value = "02";
		Integer configDelter = 1;
		TboxConfigSetting tboxConfigSetting = new TboxConfigSetting(tboxConfigId, tboxId, keyId, value, configDelter);
		tboxConfigSetting = tboxConfigSettingDao.createTboxConfigSetting(tboxConfigSetting);
		Assert.assertNotNull(tboxConfigSetting);
	}

	@Test
	@Rollback(false)
	public void testUpdateTboxConfigSetting() {
		Long tboxConfigSettingId = 3L;
		TboxConfigSetting tboxConfigSetting = tboxConfigSettingDao.findTboxConfigSettingById(tboxConfigSettingId);
		tboxConfigSetting.setConfigDelta(2);
		tboxConfigSetting = tboxConfigSettingDao.updateTboxConfigSetting(tboxConfigSetting);
		Assert.assertNotNull(tboxConfigSetting);
		LOGGER.info("tboxConfigSetting's configDelta = " + tboxConfigSetting.getConfigDelta());
	}

	@Test
	@Rollback(false)
	public void testRemoveTboxConfigSetting() {
		Long tboxConfigSettingId = 3L;
		tboxConfigSettingDao.removeTboxConfigSetting(tboxConfigSettingId);
	}

	@Test
	@Rollback(false)
	public void testListTboxConfigSettingByTboxConfigId() {
		Long tboxConfigId = 1L;
		PageResult<TboxConfigSetting> pageResult = new PageResult<>();
		List<TboxConfigSetting> list = tboxConfigSettingDao
				.listTboxConfigSettingByTboxConfigId(pageResult, tboxConfigId).getResult();
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(false)
	public void testListTboxConfigSettingByTboxId() {
		System.out.println(23 % 10);
		List<TboxConfigSetting> list = tboxConfigSettingDao.listTboxConfigSettingByTboxId(tboxId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

}
