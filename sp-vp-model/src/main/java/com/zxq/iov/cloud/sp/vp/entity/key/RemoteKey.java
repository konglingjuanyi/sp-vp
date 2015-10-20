/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0           Initial Version
 * 2015-10-19       荣杰         1.2           更改VALUE为secretKey，增加userId,vin,mobile
 * com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.key;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 智能钥匙类
 */
@Entity()
@Table(name = "TB_REMOTE_KEY")
public class RemoteKey extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_remote_key";
	private static final Integer WAIT_TBOX = 0; // 等待TBOX写入

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

	@Column(name = "TYPE", nullable = false, precision = 4, scale = 0)
	private Integer type;

	@Column(name = "SECRET_KEY", nullable = false, length = 32)
	private String secretKey;

	@Column(name = "REFERENCE", precision = 20, scale = 0)
	private Long reference;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_START_TIME", length = 7)
	private Date validStartTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_END_TIME", length = 7)
	private Date validEndTime;

	@Column(name = "PRIVILEGE", precision = 4, scale = 0)
	private Integer privilege;

	@Column(name = "USER_ID", precision = 20, scale = 0)
	private Long userId;

	@Column(name = "MOBILE", length = 50)
	private String mobile;

	@Column(name = "IS_ENABLE", nullable = false, precision = 1, scale = 0)
	private Boolean isEnable;

	public RemoteKey() {
	}

	public RemoteKey(Long tboxId, String vin, Date validStartTime, Date validEndTime, Integer privilege, Long userId) {
		this.tboxId = tboxId;
		this.vin = vin;
		this.validStartTime = validStartTime;
		this.validEndTime = validEndTime;
		this.privilege = privilege;
		this.userId = userId;
		this.status = WAIT_TBOX;
		this.isEnable = true;
	}

	public RemoteKey(Long tboxId, String vin, Integer type, String secretKey, Long reference, Long userId) {
		this.tboxId = tboxId;
		this.vin = vin;
		this.type = type;
		this.secretKey = secretKey;
		this.reference = reference;
		this.userId = userId;
		this.status = WAIT_TBOX;
		this.isEnable = true;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public Date getValidStartTime() {
		return validStartTime;
	}

	public void setValidStartTime(Date validStartTime) {
		this.validStartTime = validStartTime;
	}

	public Date getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(Date validEndTime) {
		this.validEndTime = validEndTime;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
}
