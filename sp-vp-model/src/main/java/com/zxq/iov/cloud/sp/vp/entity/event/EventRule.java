/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.event.EventRule
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.event;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 事件规则实体类
 */
@Entity()
@Table(name = "TB_EVENT_RULE")
public class EventRule extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_event_rule";

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "STEP_DEFINITION_ID", precision = 20, scale = 0)
	private Long stepDefinitionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STEP_DEFINITION_ID", insertable = false, updatable = false)
	private StepDefinition stepDefinition;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "OPERATOR", nullable = false, length = 50)
	private String operator;

	@Column(name = "VALUE", length = 255)
	private String value;

	public EventRule() {
	}

	public EventRule(String name, String operator, String value) {
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStepDefinitionId() {
		return stepDefinitionId;
	}

	public void setStepDefinitionId(Long stepDefinitionId) {
		this.stepDefinitionId = stepDefinitionId;
	}

	public StepDefinition getStepDefinition() {
		return stepDefinition;
	}

	public void setStepDefinition(StepDefinition stepDefinition) {
		this.stepDefinition = stepDefinition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
