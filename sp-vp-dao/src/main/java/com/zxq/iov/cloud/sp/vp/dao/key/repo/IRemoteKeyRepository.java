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
 * com.zxq.iov.cloud.sp.vp.dao.key.repo.IRemoteKeyRepository
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.key.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

/**
 * 安防服务 电子钥匙实体仓库
 */
public interface IRemoteKeyRepository extends BaseRepository<RemoteKey, Long> {
}
