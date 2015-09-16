/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-18       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventRuleDto
 *
 * sp - sp-vp-mgmt-api
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event;

/**
 * 安防服务 事件规则传输对象
 */
public class EventRuleDto {

	private String name;
	private String operator;
	private String value;

	public EventRuleDto() {
	}

	public EventRuleDto(String name, String operator, String value) {
		this.name = name;
		this.operator = operator;
		this.value = value;
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
