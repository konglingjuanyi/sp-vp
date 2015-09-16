/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.svt.IStolenAlarmDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.svt;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;

/**
 * 安防服务 被盗警报数据访问接口
 */
public interface IStolenAlarmDao extends BaseService<StolenAlarm, Long> {

	/**
	 * 创建被盗警报
	 *
	 * @param stolenAlarm 被盗警报对象
	 * @return 被盗警报对象
	 */
	StolenAlarm createStolenAlarm(StolenAlarm stolenAlarm);

	/**
	 * 更新被盗警报
	 *
	 * @param stolenAlarm 被盗警报对象
	 * @return 被盗警报对象
	 */
	StolenAlarm updateStolenAlarm(StolenAlarm stolenAlarm);

	/**
	 * 删除被盗警报
	 *
	 * @param stolenAlarmId 被盗警报ID
	 */
	void removeStolenAlarm(Long stolenAlarmId);

	/**
	 * 根据主键得到被盗警报对象
	 *
	 * @param stolenAlarmId 被盗警报主键
	 * @return 被盗警报对象
	 */
	StolenAlarm findStolenAlarmById(Long stolenAlarmId);

}