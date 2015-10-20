/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2            新增手机接口
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.RemoteKeyServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.common.util.MsgUtil;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 智能钥匙服务接口实现类
 */
@Service
public class RemoteKeyServiceImpl extends BaseService implements IRemoteKeyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyServiceImpl.class);

	@Autowired
	private IRemoteKeyDao remoteKeyDao;

	@Override
	public RemoteKey createKey(Long userId, Long tboxId, String vin) throws ServLayerException {
		AssertRequired("userId,vin", userId, vin);
		RemoteKey remoteKey = remoteKeyDao.findRemoteKeyByUserIdAndVin(userId, vin);
		if (null == remoteKey) {
			LOGGER.info("用户:" + userId + "的车:" + vin + "尚未创建智能钥匙，开始创建");
			remoteKey = new RemoteKey(tboxId, vin, Constants.REMOTE_KEY_TYPE_TEMPORARY, generateSecretKey(),
					generateKeyReference(userId, vin), userId);
			remoteKey = remoteKeyDao.createRemoteKey(remoteKey);
		}
		return remoteKey;
	}

	@Override
	public RemoteKey grantKey(RemoteKey remoteKey) throws ServLayerException {
		AssertRequired("tboxId,vin", remoteKey.getTboxId(), remoteKey.getVin());
		RemoteKey _remoteKey = remoteKeyDao.findRemoteKeyByUserIdAndVin(remoteKey.getUserId(), remoteKey.getVin());
		if (null == _remoteKey) {
			remoteKey.setType(Constants.REMOTE_KEY_TYPE_TEMPORARY);
			remoteKey.setReference(generateKeyReference(remoteKey.getUserId(), remoteKey.getVin()));
			remoteKey.setSecretKey(generateSecretKey());
			remoteKeyDao.createRemoteKey(remoteKey);
			return remoteKey;
		}
		return _remoteKey;
	}

	@Override
	public RemoteKey updateKey(Long keyReference, Date startTime, Date endTime, Integer privilege)
			throws ServLayerException {
		AssertRequired("keyReference", keyReference);
		RemoteKey remoteKey = remoteKeyDao.findRemoteKeyById(keyReference); // 此处根据ID还是reference待定（刘凯、朱小彦）
		if (null != remoteKey) {
			remoteKey.setValidStartTime(startTime);
			remoteKey.setValidEndTime(endTime);
			remoteKey.setPrivilege(privilege);
			remoteKeyDao.updateRemoteKey(remoteKey);
		}
		return remoteKey;
	}

	@Override
	public RemoteKey disableKey(Long keyReference) throws ServLayerException {
		AssertRequired("keyReference", keyReference);
		RemoteKey remoteKey = remoteKeyDao.findRemoteKeyById(keyReference); // 此处根据ID还是reference待定（刘凯、朱小彦）
		if (null != remoteKey) {
			remoteKey.setIsEnable(false);
			remoteKeyDao.updateRemoteKey(remoteKey);
		}
		return remoteKey;
	}

	@Override
	public RemoteKey enableKey(Long keyReference) throws ServLayerException {
		AssertRequired("keyReference", keyReference);
		RemoteKey remoteKey = remoteKeyDao.findRemoteKeyById(keyReference); // 此处根据ID还是reference待定（刘凯、朱小彦）
		if (null != remoteKey) {
			remoteKey.setIsEnable(true);
			remoteKeyDao.updateRemoteKey(remoteKey);
		}
		return remoteKey;
	}

	@Override
	public void removeKey(Long keyReference) throws ServLayerException {
		AssertRequired("keyReference", keyReference);
		remoteKeyDao.removeRemoteKey(keyReference); // 此处根据ID还是reference待定
	}

	/**
	 * 根据用户ID生成指定车辆唯一的key
	 *
	 * @param userId 用户ID
	 * @param vin    车辆唯一码
	 * @return
	 */
	private Long generateKeyReference(Long userId, String vin) {
		Long keyReference = 1L; // 如何生成待定
		return keyReference;
	}

	/**
	 * 生成密钥
	 *
	 * @return
	 */
	private String generateSecretKey() {
		String secretKey = "111"; // 如何生成待定
		return secretKey;
	}

	@Override
	public void responseWriteKey(Long tboxId, Boolean writeSuccess, Integer writeFailureReason) {
		if (writeSuccess) {
			// 通知请求者成功
		} else {
			// 通知请求者失败，并告知理由
			// 物理删除电子钥匙
		}
	}

	@Override
	public void responseDeleteKey(Long tboxId, Boolean deleteSuccess, Integer deleteFailureReason) {
		if (deleteSuccess) {
			// 通知请求者成功
		} else {
			// 通知请求者失败，并告知理由
			// 标记或删除电子钥匙
		}
	}

	@Override
	public void keyAlarm(Long tboxId) {
		String mobileId = null; // 根据TBOX ID找到车主手机设备
		MsgUtil.pushDevice(mobileId, Constants.MSG_KEY_ALARM);
	}

	@Override
	public RemoteKey findKeyByReference(Long reference) {
		return remoteKeyDao.findRemoteKeyById(reference);
	}

	@Override
	public List<RemoteKey> listUserKey(Long userId) {
		return remoteKeyDao.findRemoteKeyByUserId(userId);
	}

	@Override
	public List<RemoteKey> listVehicleKey(String vin) {
		return remoteKeyDao.findRemoteKeyByVin(vin);
	}
}