/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.repo.IEventInstanceRepository
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;

/**
 * 安防服务 事件实例实体仓库
 */
public interface IEventInstanceRepository extends BaseRepository<EventInstance, Long> {
}
