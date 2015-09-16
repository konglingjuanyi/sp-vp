/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-13       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.status;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆信息快照实体类
 */
@Entity()
@Table(name = "TB_VEHICLE_INFO")
public class VehicleInfo extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_vehicle_info";

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
	private Long tboxId;

	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

	@Column(name = "SOURCE_TYPE", nullable = false, precision = 2, scale = 0)
	private Integer sourceType;

	@Column(name = "SOURCE_ID", precision = 20, scale = 0)
	private Long sourceId;

	@Column(name = "EVENT_ID", precision = 20, scale = 0)
	private Long eventId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STATUS_TIME", length = 7)
	private Date statusTime;

	@Column(name = "OWNER_ID", precision = 20, scale = 0)
	private Long ownerId;

	@Column(name = "USER_ID", precision = 20, scale = 0)
	private Long userId;

	@Transient
	private VehiclePos vehiclePos;

	@Transient
	private List<VehicleStatus> vehicleStatuses;

	@Transient
	private List<VehicleStatus> vehicleAlerts;

	public VehicleInfo() {
	}

	public VehicleInfo(Long tboxId, String vin, Integer sourceType, Long sourceId) {
		this.tboxId = tboxId;
		this.vin = vin;
		this.sourceType = (null == sourceType) ? Constants.VEHICLE_INFO_SOURCE_STATUS : sourceType;
		if (null != sourceId) {
			this.sourceId = sourceId;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public VehiclePos getVehiclePos() {
		return vehiclePos;
	}

	public void setVehiclePos(VehiclePos vehiclePos) {
		this.vehiclePos = vehiclePos;
	}

	public List<VehicleStatus> getVehicleStatuses() {
		return vehicleStatuses;
	}

	public void setVehicleStatuses(List<VehicleStatus> vehicleStatuses) {
		this.vehicleStatuses = vehicleStatuses;
	}

	public List<VehicleStatus> getVehicleAlerts() {
		return vehicleAlerts;
	}

	public void setVehicleAlerts(List<VehicleStatus> vehicleAlerts) {
		this.vehicleAlerts = vehicleAlerts;
	}
}
