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
 * com.zxq.iov.cloud.sp.vp.dao.config.ITboxConfigSettingDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;

import java.util.List;

/**
 * 安防 TBOX配置参数数据访问接口
 */
public interface ITboxConfigSettingDao extends BaseService<TboxConfigSetting, Long> {

	/**
	 * 创建TBOX配置参数
	 *
	 * @param tboxConfigSetting TBOX配置参数对象
	 * @return TBOX配置参数对象
	 */
	TboxConfigSetting createTboxConfigSetting(TboxConfigSetting tboxConfigSetting);

	/**
	 * 更新TBOX配置参数
	 *
	 * @param tboxConfigSetting TBOX配置参数对象
	 * @return TBOX配置参数对象
	 */
	TboxConfigSetting updateTboxConfigSetting(TboxConfigSetting tboxConfigSetting);

	/**
	 * 删除TBOX配置参数
	 *
	 * @param tboxConfigSettingId TBOX配置参数ID
	 */
	void removeTboxConfigSetting(Long tboxConfigSettingId);

	/**
	 * 根据主键得到TBOX个性化配置对象
	 *
	 * @param tboxConfigSettingId TBOX个性化配置主键
	 * @return TBOX个性化配置对象
	 */
	TboxConfigSetting findTboxConfigSettingById(Long tboxConfigSettingId);

	/**
	 * 根据TBOX配置ID得到TBOX配置参数对象列表
	 *
	 * @param pageResult   分页
	 * @param tboxConfigId TBOX配置ID
	 * @return TBOX配置参数对象列表
	 */
	PageResult<TboxConfigSetting> listTboxConfigSettingByTboxConfigId(PageResult<TboxConfigSetting> pageResult,
			Long tboxConfigId);

	/**
	 * 根据TBOX ID得到TBOX配置参数对象列表
	 *
	 * @param tboxId TBOX ID
	 * @return TBOX配置参数对象列表
	 */
	List<TboxConfigSetting> listTboxConfigSettingByTboxId(Long tboxId);

}