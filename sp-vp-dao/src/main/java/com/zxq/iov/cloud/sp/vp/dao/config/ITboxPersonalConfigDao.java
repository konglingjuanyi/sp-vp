package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;

/**
 * 安防 TBOX个性化配置持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-19 13:16
 * modify date 2015-7-29 14:12
 * @version 0.2, 2015-7-29
 */
public interface ITboxPersonalConfigDao extends BaseService<TboxPersonalConfig, Long> {

	/**
	 * 创建TBOX个性化配置
	 * @param tboxPersonalConfig	TBOX个性化配置对象
	 * @return						TBOX个性化配置对象
	 */
	TboxPersonalConfig createTboxPersonalConfig(TboxPersonalConfig tboxPersonalConfig);

	/**
	 * 更新TBOX个性化配置
	 * @param tboxPersonalConfig	TBOX个性化配置对象
	 * @return						TBOX个性化配置对象
	 */
	TboxPersonalConfig updateTboxPersonalConfig(TboxPersonalConfig tboxPersonalConfig);

	/**
	 * 删除TBOX个性化配置
	 * @param tboxPersonalConfigId  TBOX个性化配置ID
	 */
	void removeTboxPersonalConfig(Long tboxPersonalConfigId);

	/**
	 * 根据主键得到TBOX个性化配置对象
	 * @param tboxPersonalConfigId 	TBOX个性化配置主键
	 * @return						TBOX个性化配置对象
	 */
	TboxPersonalConfig findTboxPersonalConfigById(Long tboxPersonalConfigId);

	/**
	 * 根据TBOX ID得到TBOX个性化配置对象
	 * @param tboxId            	TBOX ID
	 * @return                     	TBOX个性化配置对象
	 */
	TboxPersonalConfig findTboxPersonalConfigByTboxId(Long tboxId);

	/**
	 * 根据车辆唯一码得到TBOX个性化配置对象
	 * @param vin            		车辆唯一码
	 * @return                     	TBOX个性化配置对象
	 */
	TboxPersonalConfig findTboxPersonalConfigByVin(String vin);
	
}