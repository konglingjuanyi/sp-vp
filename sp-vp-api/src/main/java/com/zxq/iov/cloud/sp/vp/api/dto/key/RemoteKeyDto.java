/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-10-20       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.key;

import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 智能钥匙传输对象
 */
public class RemoteKeyDto implements Serializable {

	// 钥匙ID
	private Long keyId;
	// 钥匙值
	private String secretKey;
	// 钥匙引用
	private Long keyReference;
	// 有效期开始时间
	private Date keyValidityStartTime;
	// 有效期结束时间
	private Date keyValidityEndTime;
	// 钥匙权限
	private Integer keyPrivilege;
	// 是否启用
	private Boolean isEnable;
	// TBOX蓝牙MAC地址
	private String tboxMac;
	// 车辆唯一码
	private String vin;
	// 用户姓名
	private String realName;
	// 用户名
	private String userName;
	// 手机
	private String mobile;

	public RemoteKeyDto() {
	}

	public RemoteKeyDto(Long keyId, String secretKey, Long keyReference, Date keyValidityStartTime,
			Date keyValidityEndTime, Integer keyPrivilege, Boolean isEnable, String vin) {
		this.keyId = keyId;
		this.secretKey = secretKey;
		this.keyReference = keyReference;
		this.keyValidityStartTime = keyValidityStartTime;
		this.keyValidityEndTime = keyValidityEndTime;
		this.keyPrivilege = keyPrivilege;
		this.isEnable = isEnable;
		this.vin = vin;
	}

	public RemoteKeyDto(Long keyId, String secretKey, Long keyReference, Date keyValidityStartTime,
			Date keyValidityEndTime, Integer keyPrivilege, Boolean isEnable, String vin, String realName,
			String userName, String mobile) {
		this.keyId = keyId;
		this.secretKey = secretKey;
		this.keyReference = keyReference;
		this.keyValidityStartTime = keyValidityStartTime;
		this.keyValidityEndTime = keyValidityEndTime;
		this.keyPrivilege = keyPrivilege;
		this.isEnable = isEnable;
		this.vin = vin;
		this.realName = realName;
		this.userName = userName;
		this.mobile = mobile;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
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

	public Integer getKeyPrivilege() {
		return keyPrivilege;
	}

	public void setKeyPrivilege(Integer keyPrivilege) {
		this.keyPrivilege = keyPrivilege;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getTboxMac() {
		return tboxMac;
	}

	public void setTboxMac(String tboxMac) {
		this.tboxMac = tboxMac;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
