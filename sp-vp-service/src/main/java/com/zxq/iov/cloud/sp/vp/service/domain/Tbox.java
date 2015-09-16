/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-09-15       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.service.domain.Tbox
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.domain;

/**
 * 安防服务 Tbox对象
 */
public class Tbox {

	private Long tboxId;
	private String vin;
	private Long userId;

	public Tbox() {

	}

	public Tbox(Long tboxId, String vin) {
		this.tboxId = tboxId;
		this.vin = vin;
	}

	public Tbox(Long tboxId, String vin, Long userId) {
		this.tboxId = tboxId;
		this.vin = vin;
		this.userId = userId;
	}

	public Long getTboxId() {
		return tboxId;
	}

	public void setTboxId(Long tboxId) {
		this.tboxId = tboxId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
