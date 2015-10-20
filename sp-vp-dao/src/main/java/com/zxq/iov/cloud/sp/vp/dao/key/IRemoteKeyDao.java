/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.1            增加成员函数
 *
 * com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.key;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

import java.util.List;

/**
 * 安防服务 智能钥匙数据访问接口
 */
public interface IRemoteKeyDao extends BaseService<RemoteKey, Long> {

	/**
	 * 创建电子钥匙
	 *
	 * @param remoteKey 智能钥匙对象
	 * @return 智能钥匙对象
	 */
	RemoteKey createRemoteKey(RemoteKey remoteKey);

	/**
	 * 更新电子钥匙
	 *
	 * @param remoteKey 智能钥匙对象
	 * @return 智能钥匙对象
	 */
	RemoteKey updateRemoteKey(RemoteKey remoteKey);

	/**
	 * 删除电子钥匙
	 *
	 * @param remoteKeyId 智能钥匙ID
	 */
	void removeRemoteKey(Long remoteKeyId);

	/**
	 * 根据主键得到智能钥匙对象
	 *
	 * @param remoteKeyId 智能钥匙主键
	 * @return 智能钥匙对象
	 */
	RemoteKey findRemoteKeyById(Long remoteKeyId);

	/**
	 * 根据用户ID和车辆唯一码得到智能钥匙对象
	 *
	 * @param userId 用户ID
	 * @param vin    车辆唯一码
	 * @return 智能钥匙对象
	 */
	RemoteKey findRemoteKeyByUserIdAndVin(Long userId, String vin);

	/**
	 * 根据用户ID得到钥匙对象列表
	 *
	 * @param userId 用户ID
	 * @return 钥匙对象列表
	 */
	List<RemoteKey> findRemoteKeyByUserId(Long userId);

	/**
	 * 根据车辆唯一码得到钥匙对象列表
	 *
	 * @param vin 车辆唯一码
	 * @return 钥匙对象列表
	 */
	List<RemoteKey> findRemoteKeyByVin(String vin);

}