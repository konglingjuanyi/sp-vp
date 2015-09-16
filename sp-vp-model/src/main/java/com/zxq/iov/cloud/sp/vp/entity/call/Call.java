/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 * 2015-07-09       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.call.Call
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.call;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 呼叫实体类
 */
@Entity()
@Table(name = "TB_CALL")
public class Call extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_call";
	private static final Integer RUNNDING_STATUS = 1;

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

	@Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
	private Long tboxId;

	@Column(name = "TYPE", nullable = false, precision = 4, scale = 0)
	private Integer type;

	@Column(name = "CALL_TYPE", precision = 4, scale = 0)
	private Integer callType;

	@Column(name = "CRASH_SEVERITY", precision = 4, scale = 0)
	private Integer crashSeverity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME", nullable = false, length = 7)
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME", length = 7)
	private Date endTime;

	public Call() {
	}

	public Call(String vin, Long tboxId, Integer type, Integer callType, Date startTime) {
		this.vin = vin;
		this.tboxId = tboxId;
		this.type = type;
		this.callType = callType;
		this.startTime = startTime;
		this.status = RUNNDING_STATUS;
	}

	public Call(String vin, Long tboxId, Integer type, Integer callType, Integer crashSeverity, Date startTime) {
		this.vin = vin;
		this.tboxId = tboxId;
		this.type = type;
		this.callType = callType;
		this.crashSeverity = crashSeverity;
		this.startTime = startTime;
		this.status = RUNNDING_STATUS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Long getTboxId() {
		return tboxId;
	}

	public void setTboxId(Long tboxId) {
		this.tboxId = tboxId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCallType() {
		return callType;
	}

	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	public Integer getCrashSeverity() {
		return crashSeverity;
	}

	public void setCrashSeverity(Integer crashSeverity) {
		this.crashSeverity = crashSeverity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
