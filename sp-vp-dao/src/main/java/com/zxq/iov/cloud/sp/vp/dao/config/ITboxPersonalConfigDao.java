/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 * 2015-07-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.ITboxPersonalConfigDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;

/**
 * 安防 TBOX个性化配置数据访问接口
 */
public interface ITboxPersonalConfigDao extends BaseService<TboxPersonalConfig, Long> {

	/**
	 * 创建TBOX个性化配置
	 *
	 * @param tboxPersonalConfig TBOX个性化配置对象
	 * @return TBOX个性化配置对象
	 */
	TboxPersonalConfig createTboxPersonalConfig(TboxPersonalConfig tboxPersonalConfig);

	/**
	 * 更新TBOX个性化配置
	 *
	 * @param tboxPersonalConfig TBOX个性化配置对象
	 * @return TBOX个性化配置对象
	 */
	TboxPersonalConfig updateTboxPersonalConfig(TboxPersonalConfig tboxPersonalConfig);

	/**
	 * 删除TBOX个性化配置
	 *
	 * @param tboxPersonalConfigId TBOX个性化配置ID
	 */
	void removeTboxPersonalConfig(Long tboxPersonalConfigId);

	/**
	 * 根据主键得到TBOX个性化配置对象
	 *
	 * @param tboxPersonalConfigId TBOX个性化配置主键
	 * @return TBOX个性化配置对象
	 */
	TboxPersonalConfig findTboxPersonalConfigById(Long tboxPersonalConfigId);

	/**
	 * 根据TBOX ID得到TBOX个性化配置对象
	 *
	 * @param tboxId TBOX ID
	 * @return TBOX个性化配置对象
	 */
	TboxPersonalConfig findTboxPersonalConfigByTboxId(Long tboxId);

	/**
	 * 根据车辆唯一码得到TBOX个性化配置对象
	 *
	 * @param vin 车辆唯一码
	 * @return TBOX个性化配置对象
	 */
	TboxPersonalConfig findTboxPersonalConfigByVin(String vin);

}