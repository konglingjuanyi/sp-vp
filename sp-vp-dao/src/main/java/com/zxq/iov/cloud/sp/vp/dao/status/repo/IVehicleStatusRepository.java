/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.status.repo.IVehicleStatusRepository
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

/**
 * 安防服务 车辆状态信息实体仓库
 */
public interface IVehicleStatusRepository extends BaseRepository<VehicleStatus, Long> {
}
