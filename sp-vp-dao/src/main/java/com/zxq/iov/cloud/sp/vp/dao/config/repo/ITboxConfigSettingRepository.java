/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-29       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.repo.ITboxConfigSettingRepository
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;

/**
 * 安防 TBOX个性化配置实体仓库
 */
public interface ITboxConfigSettingRepository extends BaseRepository<TboxConfigSetting, Long> {
}
