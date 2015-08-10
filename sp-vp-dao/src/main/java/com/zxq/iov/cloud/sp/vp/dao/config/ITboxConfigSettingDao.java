package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;

import java.util.List;

/**
 * 安防 TBOX配置参数持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-7-29 11:21
 * modify date
 * @version 0.1, 2015-7-29
 */
public interface ITboxConfigSettingDao extends BaseService<TboxConfigSetting, Long> {

	/**
	 * 创建TBOX配置参数
	 * @param tboxConfigSetting		TBOX配置参数对象
	 * @return						TBOX配置参数对象
	 */
	TboxConfigSetting createTboxConfigSetting(TboxConfigSetting tboxConfigSetting);

	/**
	 * 更新TBOX配置参数
	 * @param tboxConfigSetting		TBOX配置参数对象
	 * @return						TBOX配置参数对象
	 */
	TboxConfigSetting updateTboxConfigSetting(TboxConfigSetting tboxConfigSetting);

	/**
	 * 删除TBOX配置参数
	 * @param tboxConfigSettingId  	TBOX配置参数ID
	 */
	void removeTboxConfigSetting(Long tboxConfigSettingId);

	/**
	 * 根据主键得到TBOX个性化配置对象
	 * @param tboxConfigSettingId 	TBOX个性化配置主键
	 * @return						TBOX个性化配置对象
	 */
	TboxConfigSetting findTboxConfigSettingById(Long tboxConfigSettingId);

	/**
	 * 根据TBOX配置ID得到TBOX配置参数对象列表
	 * @param pageResult 			分页
	 * @param tboxConfigId          TBOX配置ID
	 * @return                     	TBOX配置参数对象列表
	 */
	PageResult<TboxConfigSetting> listTboxConfigSettingByTboxConfigId(PageResult<TboxConfigSetting> pageResult,
																	  Long tboxConfigId);

	/**
	 * 根据TBOX ID得到TBOX配置参数对象列表
	 * @param tboxId          		TBOX ID
	 * @return                     	TBOX配置参数对象列表
	 */
	List<TboxConfigSetting> listTboxConfigSettingByTboxId(Long tboxId);
	
}