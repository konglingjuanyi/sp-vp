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
 * com.zxq.iov.cloud.sp.vp.api.dto.svt.TrackSettingReqDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 跟踪设置请求传输对象
 */
public class TrackSettingReqDto extends OtaDto {

	// 每次追踪上传间隔
	private Integer trackInterval;
	// 追踪数量
	private Integer tracks;

	public TrackSettingReqDto() {
	}

	public TrackSettingReqDto(Integer trackInterval, Integer tracks) {
		this.trackInterval = trackInterval;
		this.tracks = tracks;
	}

	public Integer getTrackInterval() {
		return trackInterval;
	}

	public void setTrackInterval(Integer trackInterval) {
		this.trackInterval = trackInterval;
	}

	public Integer getTracks() {
		return tracks;
	}

	public void setTracks(Integer tracks) {
		this.tracks = tracks;
	}
}
