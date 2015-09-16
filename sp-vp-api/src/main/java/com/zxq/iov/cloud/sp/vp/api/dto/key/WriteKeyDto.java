/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-06       荣杰         1.0            Initial Version
 * 2015-08-12       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.key.WriteKeyDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.key;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防服务 写入电子钥匙传输对象
 */
public class WriteKeyDto extends OtaDto {

	// 钥匙类型
	private Integer keyType;
	// 钥匙值
	private byte[] keyValue;
	// 钥匙引用
	private Long keyReference;
	// 有效期开始时间
	private Date keyValidityStartTime;
	// 有效期结束时间
	private Date keyValidityEndTime;

	public WriteKeyDto() {
	}

	public WriteKeyDto(Integer keyType, byte[] keyValue, Long keyReference, Date keyValidityStartTime,
			Date keyValidityEndTime) {
		this.keyType = keyType;
		this.keyValue = keyValue;
		this.keyReference = keyReference;
		this.keyValidityStartTime = keyValidityStartTime;
		this.keyValidityEndTime = keyValidityEndTime;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	public byte[] getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(byte[] keyValue) {
		this.keyValue = keyValue;
	}

	public Long getKeyReference() {
		return keyReference;
	}

	public void setKeyReference(Long keyReference) {
		this.keyReference = keyReference;
	}

	public Date getKeyValidityStartTime() {
		return keyValidityStartTime;
	}

	public void setKeyValidityStartTime(Date keyValidityStartTime) {
		this.keyValidityStartTime = keyValidityStartTime;
	}

	public Date getKeyValidityEndTime() {
		return keyValidityEndTime;
	}

	public void setKeyValidityEndTime(Date keyValidityEndTime) {
		this.keyValidityEndTime = keyValidityEndTime;
	}
}
