/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-12-10       叶荣杰       1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import java.io.Serializable;
import java.util.List;

/**
 * 安防服务 被盗状态传输对象
 */
public class StolenStatusDto implements Serializable {

	// 是否被盗
	private Boolean isStolen;
	// 产生被盗报警对象列表
	private List<StolenAlarmDto> stolenAlarmDtos;

	public StolenStatusDto() {
	}

	public Boolean getStolen() {
		return isStolen;
	}

	public void setStolen(Boolean stolen) {
		isStolen = stolen;
	}

	public List<StolenAlarmDto> getStolenAlarmDtos() {
		return stolenAlarmDtos;
	}

	public void setStolenAlarmDtos(List<StolenAlarmDto> stolenAlarmDtos) {
		this.stolenAlarmDtos = stolenAlarmDtos;
	}
}
