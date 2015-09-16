/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-09       荣杰         1.0            Initial Version
 * 2015-06-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.journey.IJourneyDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.journey;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;

/**
 * 安防服务 行程数据访问接口
 */
public interface IJourneyDao extends BaseService<Journey, Long> {

	/**
	 * 创建行程
	 *
	 * @param journey 行程对象
	 * @return 行程对象
	 */
	Journey createJourney(Journey journey);

	/**
	 * 更新行程
	 *
	 * @param journey 行程对象
	 * @return 行程对象
	 */
	Journey updateJourney(Journey journey);

	/**
	 * 删除行程
	 *
	 * @param journeyId 行程ID
	 */
	void removeJourney(Long journeyId);

	/**
	 * 根据主键得到行程对象
	 *
	 * @param journeyId 行程主键
	 * @return 行程对象
	 */
	Journey findJourneyById(Long journeyId);

	/**
	 * 根据TBOX行程ID得到行程对象
	 *
	 * @param tboxJourneyId TBOX行程ID
	 * @param tboxId        TBOX ID
	 * @return 行程对象
	 */
	Journey findJourneyByTboxJourneyIdAndTboxId(Integer tboxJourneyId, Long tboxId);

}