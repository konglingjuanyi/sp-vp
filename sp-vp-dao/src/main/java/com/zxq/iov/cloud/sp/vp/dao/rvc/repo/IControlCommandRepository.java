/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.rvc.repo.IControlCommandRepository
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.rvc.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;

/**
 * 安防服务 控制命令实体仓库
 */
public interface IControlCommandRepository extends BaseRepository<ControlCommand, Long> {
}
