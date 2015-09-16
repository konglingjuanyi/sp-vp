/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-07-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.rvc;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 控制命令实体类
 */
@Entity()
@Table(name = "TB_CONTROL_COMMAND")
public class ControlCommand extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_control_command";

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

	@Column(name = "EVENT_ID", precision = 20, scale = 0)
	private Long eventId;

	@Column(name = "USER_ID", precision = 20, scale = 0)
	private Long userId;

	@Column(name = "REQUEST_CLIENT", length = 20)
	private String requestClient;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "CODE", nullable = false, length = 2)
	private String code;

	@Column(name = "PARAMETER", length = 255)
	private String parameter;

	@Column(name = "COMMAND_STATUS", nullable = false, length = 2)
	private String commandStatus;

	@Column(name = "IS_CANCEL", nullable = false, precision = 1, scale = 0)
	private Boolean isCancel;

	@Column(name = "FAILURE_TYPE", precision = 6, scale = 0)
	private Integer failureType;

	public ControlCommand() {
	}

	public ControlCommand(Long tboxId, String vin, String requestClient, String name, String code, String parameter) {
		this.tboxId = tboxId;
		this.vin = vin;
		this.requestClient = requestClient;
		this.name = name;
		this.code = code;
		this.parameter = parameter;
		this.commandStatus = "00";
		this.isCancel = false;
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

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRequestClient() {
		return requestClient;
	}

	public void setRequestClient(String requestClient) {
		this.requestClient = requestClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCommandStatus() {
		return commandStatus;
	}

	public void setCommandStatus(String commandStatus) {
		this.commandStatus = commandStatus;
	}

	public Boolean isCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	public Integer getFailureType() {
		return failureType;
	}

	public void setFailureType(Integer failureType) {
		this.failureType = failureType;
	}
}
