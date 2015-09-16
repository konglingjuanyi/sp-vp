/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-07-14       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.status;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防服务 车辆位置传输对象
 */
public class VehiclePosDto extends OtaDto {

	// 纬度
	private Integer latitude;
	// 经度
	private Integer longitude;
	// 海拔
	private Integer altitude;
	// 车头方向
	private Integer heading;
	// 速度
	private Integer speed;
	// 水平精度
	private Integer hdop;
	// 卫星数量
	private Integer satellites;
	// GPS时间
	private Date gpsTime;
	// GPS状态
	private Integer gpsStatus;

	public VehiclePosDto() {
	}

	public VehiclePosDto(Integer latitude, Integer longitude, Integer altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public VehiclePosDto(Integer latitude, Integer longitude, Integer altitude, Integer heading, Integer speed,
			Integer hdop, Integer satellites, Date gpsTime, Integer gpsStatus) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.heading = heading;
		this.speed = speed;
		this.hdop = hdop;
		this.satellites = satellites;
		this.gpsTime = gpsTime;
		this.gpsStatus = gpsStatus;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public Integer getHeading() {
		return heading;
	}

	public void setHeading(Integer heading) {
		this.heading = heading;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getHdop() {
		return hdop;
	}

	public void setHdop(Integer hdop) {
		this.hdop = hdop;
	}

	public Integer getSatellites() {
		return satellites;
	}

	public void setSatellites(Integer satellites) {
		this.satellites = satellites;
	}

	public Date getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}

	public Integer getGpsStatus() {
		return gpsStatus;
	}

	public void setGpsStatus(Integer gpsStatus) {
		this.gpsStatus = gpsStatus;
	}
}
