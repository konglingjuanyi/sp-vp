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
 * com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.key;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

/**
 * 安防服务 电子钥匙数据访问接口
 */
public interface IRemoteKeyDao extends BaseService<RemoteKey, Long> {

	/**
	 * 创建电子钥匙
	 *
	 * @param remoteKey 电子钥匙对象
	 * @return 电子钥匙对象
	 */
	RemoteKey createRemoteKey(RemoteKey remoteKey);

	/**
	 * 更新电子钥匙
	 *
	 * @param remoteKey 电子钥匙对象
	 * @return 电子钥匙对象
	 */
	RemoteKey updateRemoteKey(RemoteKey remoteKey);

	/**
	 * 删除电子钥匙
	 *
	 * @param remoteKeyId 电子钥匙ID
	 */
	void removeRemoteKey(Long remoteKeyId);

	/**
	 * 根据主键得到电子钥匙对象
	 *
	 * @param remoteKeyId 电子钥匙主键
	 * @return 电子钥匙对象
	 */
	RemoteKey findRemoteKeyById(Long remoteKeyId);

}