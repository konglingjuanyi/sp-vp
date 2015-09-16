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
 * com.zxq.iov.cloud.sp.vp.dao.key.impl.RemoteKeyDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.key.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao;
import com.zxq.iov.cloud.sp.vp.dao.key.repo.IRemoteKeyRepository;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防服务 电子钥匙数据访问接口实现类
 */
@Service
public class RemoteKeyDaoImpl extends BaseServiceImpl<IRemoteKeyRepository, RemoteKey, Long> implements IRemoteKeyDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyDaoImpl.class);

	@Autowired
	public RemoteKeyDaoImpl(IRemoteKeyRepository repo) {
		super(repo);
	}

	@Override
	public RemoteKey createRemoteKey(RemoteKey remoteKey) {
		if (remoteKey == null) {
			LOGGER.error("remoteKey cannot be null");
		}
		remoteKey.setId(null);
		super.save(remoteKey);
		return remoteKey;
	}

	@Override
	public RemoteKey updateRemoteKey(RemoteKey remoteKey) {
		if (remoteKey == null) {
			LOGGER.error("remoteKey cannot be null");
		}
		super.update(remoteKey);
		return remoteKey;
	}

	@Override
	public void removeRemoteKey(Long remoteKeyId) {
		if (remoteKeyId == null) {
			LOGGER.error("remoteKeyId cannot be null");
		}
		super.delete(remoteKeyId);
	}

	@Override
	public RemoteKey findRemoteKeyById(Long remoteKeyId) {
		if (remoteKeyId == null) {
			LOGGER.error("remoteKeyId cannot be null");
		}
		return super.findOne(remoteKeyId);
	}

}