/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-15       荣杰         1.0            Initial Version
 * 2015-06-26       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt.StolenAlarmDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;

/**
 * 安防服务 被盗警报传输对象装配类
 */
public class StolenAlarmDtoAssembler {

	public StolenAlarm fromDto(final StolenAlarmDto stolenAlarmDto) {
		return new StolenAlarm(stolenAlarmDto.getAlarmTypeId(), stolenAlarmDto.getAlarmData(),
				stolenAlarmDto.getAlarmTime());
	}
}
