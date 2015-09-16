/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 * 2015-07-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.config;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 TBOX个性化配置实体类
 */
@Entity()
@Table(name = "TB_TBOX_PERSONAL_CONFIG")
public class TboxPersonalConfig extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_tbox_personal_config";

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
	private Long tboxId;

	@Column(name = "USER_ID", nullable = false, precision = 20, scale = 0)
	private Long userId;

	@Column(name = "VIN", nullable = false, length = 17)
	private String vin;

	@Column(name = "CONFIG_DELTA", nullable = false, precision = 10, scale = 0)
	private Integer configDelta;

	public TboxPersonalConfig() {
	}

	public TboxPersonalConfig(Long tboxId, Long userId, String vin) {
		this.tboxId = tboxId;
		this.userId = userId;
		this.vin = vin;
		this.configDelta = 1;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getConfigDelta() {
		return configDelta;
	}

	public void setConfigDelta(Integer configDelta) {
		this.configDelta = configDelta;
	}
}
