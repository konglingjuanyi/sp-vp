/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
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

/**
 * 安防服务 电子钥匙数据访问测试类
 */
@Transactional
public class RemoteKeyDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyDaoImplTest.class);

	@Autowired
	private IRemoteKeyDao remoteKeyDao;

	@Test
	@Rollback(false)
	public void testCreateRemoteKey() {
		Long tboxId = 1L;
		RemoteKey remoteKey = new RemoteKey();
		remoteKey.setTboxId(tboxId);
		remoteKey.setType(1);
		remoteKey.setValue("1");
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

}
