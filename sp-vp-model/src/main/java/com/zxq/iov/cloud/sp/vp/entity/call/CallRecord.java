/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 * 2015-06-25       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.call.CallRecord
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.call;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 呼叫记录实体类
 */
@Entity()
@Table(name = "TB_CALL_RECORD")
public class CallRecord extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_call_record";
	private static final Integer RUNNING_STATUS = 1;

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "CALL_ID", nullable = false, precision = 20, scale = 0)
	private Long callId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CALL_ID", nullable = false, insertable = false, updatable = false)
	private Call call;

	@Column(name = "CALL_NUMBER", nullable = false, length = 30)
	private String callNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CALL_TIME", length = 7)
	private Date callTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HANG_UP_TIME", length = 7)
	private Date hangUpTime;

	@Column(name = "REJECT_REASON", precision = 4, scale = 0)
	private Integer rejectReason;

	public CallRecord() {
	}

	public CallRecord(Long callId, String callNumber, Date callTime) {
		this.callId = callId;
		this.callNumber = callNumber;
		this.callTime = callTime;
		this.status = RUNNING_STATUS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCallId() {
		return callId;
	}

	public void setCallId(Long callId) {
		this.callId = callId;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public Date getHangUpTime() {
		return hangUpTime;
	}

	public void setHangUpTime(Date hangUpTime) {
		this.hangUpTime = hangUpTime;
	}

	public Integer getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(Integer rejectReason) {
		this.rejectReason = rejectReason;
	}
}
