/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.key.RemoteKeyDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.key;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防服务 智能钥匙数据访问测试类
 */
@Transactional
public class RemoteKeyDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyDaoImplTest.class);

	private String vin = "12345678901234567";
	private Long userId = 1L;

	@Autowired
	private IRemoteKeyDao remoteKeyDao;

	@Test
	@Rollback(false)
	public void testCreateRemoteKey() {
		Long tboxId = 1L;
		RemoteKey remoteKey = new RemoteKey();
		remoteKey.setTboxId(tboxId);
		remoteKey.setType(1);
		remoteKey.setSecretKey("1");
		remoteKey.setVin("12345678901234567");
		remoteKey.setUserId(userId);
		remoteKey = remoteKeyDao.createRemoteKey(remoteKey);
		Assert.assertNotNull(remoteKey);
	}

	@Test
	@Rollback(false)
	public void testUpdateRemoteKey() {
		Long remoteKeyId = 15L;
		RemoteKey remoteKey = remoteKeyDao.findRemoteKeyById(remoteKeyId);
		remoteKey.setReference(1L);
		remoteKey = remoteKeyDao.updateRemoteKey(remoteKey);
		Assert.assertNotNull(remoteKey);
		LOGGER.info("remoteKey's reference = " + remoteKey.getReference());
	}

	@Test
	@Rollback(false)
	public void testRemoveReomteKey() {
		Long remoteKeyId = 4L;
		remoteKeyDao.removeRemoteKey(remoteKeyId);
	}

	@Test
	@Rollback(false)
	public void testFindRemoteKeyByUserIdAndVin() {
		RemoteKey remoteKey = remoteKeyDao.findRemoteKeyByUserIdAndVin(userId, vin);
		Assert.assertNotNull(remoteKey);
	}

	@Test
	@Rollback(false)
	public void testFindRemoteKeyByUserId() {
		List<RemoteKey> list = remoteKeyDao.findRemoteKeyByUserId(userId);
		LOGGER.info("list size:" + list.size());
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	@Rollback(false)
	public void testFindRemoteKeyByVin() {
		List<RemoteKey> list = remoteKeyDao.findRemoteKeyByVin(vin);
		LOGGER.info("list size:" + list.size());
		Assert.assertTrue(list.size() > 0);
	}

}
