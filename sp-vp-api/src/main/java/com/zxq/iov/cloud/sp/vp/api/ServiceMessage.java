/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-06       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.ServiceMessage
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 服务消息
 */
public class ServiceMessage extends OtaDto {

	// 传递到应用的数据
	private String appData;
	// 消息KEY，用以给网关判断dispatch，此处传stepId
	private String messageKey;

	public ServiceMessage() {
	}

	public ServiceMessage(OtaDto otaDto) {
		setAid(otaDto.getAid());
		setMid(otaDto.getMid());
		setTboxId(otaDto.getTboxId());
		setVin(otaDto.getVin());
		setEventId(otaDto.getEventId());
	}

	public ServiceMessage(String aid, Integer mid, Long tboxId, String vin, Long eventId) {
		setAid(aid);
		setMid(mid);
		setTboxId(tboxId);
		setVin(vin);
		setEventId(eventId);
	}

	public String getAppData() {
		return appData;
	}

	public void setAppData(String appData) {
		this.appData = appData;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
}
