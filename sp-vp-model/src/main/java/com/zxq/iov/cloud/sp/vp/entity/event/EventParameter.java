/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-04       荣杰         1.0            Initial Version
 * 2015-06-08       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.event.EventParameter
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.event;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 事件参数实体类
 */
@Entity()
@Table(name = "TB_EVENT_PARAMETER")
public class EventParameter extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_event_parameter";

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "TYPE", nullable = false, precision = 4, scale = 0)
	private Integer type;

	@Column(name = "STEP_INSTANCE_ID", precision = 20, scale = 0)
	private Long stepInstanceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STEP_INSTANCE_ID", insertable = false, updatable = false)
	private StepInstance stepInstance;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "VALUE", length = 255)
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getStepInstanceId() {
		return stepInstanceId;
	}

	public void setStepInstanceId(Long stepInstanceId) {
		this.stepInstanceId = stepInstanceId;
	}

	public StepInstance getStepInstance() {
		return stepInstance;
	}

	public void setStepInstance(StepInstance stepInstance) {
		this.stepInstance = stepInstance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
