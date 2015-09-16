/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-02       荣杰         1.0            Initial Version
 * 2015-06-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.event;

import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 任务定义实体类
 */
@Entity()
@Table(name = "TB_TASK_DEFINITION")
public class TaskDefinition extends MyBaseEntity<Long> implements Serializable {

	private static final String SEQ_NAME = "seq_tb_task_definition";

	@Id
	@Column(name = "ID", nullable = false, updatable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
	@TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
			pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
	private Long id;

	@Column(name = "EVENT_DEFINITION_ID", nullable = false, precision = 20, scale = 0)
	private Long eventDefinitionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EVENT_DEFINITION_ID", nullable = false, insertable = false, updatable = false)
	private EventDefinition eventDefinition;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "LIFECYCLE", precision = 8, scale = 0)
	private Integer lifecycle;

	@Column(name = "PRE_TASK_DEFINITION_ID", precision = 20, scale = 0)
	private Long preTaskDefinitionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRE_TASK_DEFINITION_ID", insertable = false, updatable = false)
	private TaskDefinition preTaskDefinition;

	@Column(name = "CYCLE_LIMIT", nullable = false, precision = 8, scale = 0)
	private Integer cycleLimit;

	@Column(name = "IS_EXCLUSIVE", nullable = false, precision = 1, scale = 0)
	private Boolean isExclusive;

	@Column(name = "IS_CONTINUE", nullable = false, precision = 1, scale = 0)
	private Boolean isContinue;

	@Column(name = "IS_ROLLBACK", nullable = false, precision = 1, scale = 0)
	private Boolean isRollback;

	@Column(name = "IS_LAST", nullable = false, precision = 1, scale = 0)
	private Boolean isLast;

	@Column(name = "SORT", nullable = false, precision = 4, scale = 0)
	private Integer sort;

	public TaskDefinition() {
	}

	public TaskDefinition(Long eventDefinitionId, String name, Integer lifecycle, Long preTaskDefinitionId,
			Integer cycleLimit, Boolean isExclusive, Boolean isContinue, Boolean isRollback, Boolean isLast,
			Integer sort) {
		this.eventDefinitionId = eventDefinitionId;
		this.name = name;
		this.lifecycle = lifecycle;
		this.preTaskDefinitionId = preTaskDefinitionId;
		this.cycleLimit = cycleLimit;
		this.isExclusive = isExclusive;
		this.isContinue = isContinue;
		this.isRollback = isRollback;
		this.isLast = isLast;
		this.sort = sort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEventDefinitionId() {
		return eventDefinitionId;
	}

	public void setEventDefinitionId(Long eventDefinitionId) {
		this.eventDefinitionId = eventDefinitionId;
	}

	public EventDefinition getEventDefinition() {
		return eventDefinition;
	}

	public void setEventDefinition(EventDefinition eventDefinition) {
		this.eventDefinition = eventDefinition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

	public Long getPreTaskDefinitionId() {
		return preTaskDefinitionId;
	}

	public void setPreTaskDefinitionId(Long preTaskDefinitionId) {
		this.preTaskDefinitionId = preTaskDefinitionId;
	}

	public TaskDefinition getPreTaskDefinition() {
		return preTaskDefinition;
	}

	public void setPreTaskDefinition(TaskDefinition preTaskDefinition) {
		this.preTaskDefinition = preTaskDefinition;
	}

	public Integer getCycleLimit() {
		return cycleLimit;
	}

	public void setCycleLimit(Integer cycleLimit) {
		this.cycleLimit = cycleLimit;
	}

	public Boolean isExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Boolean isExclusive) {
		this.isExclusive = isExclusive;
	}

	public Boolean isContinue() {
		return isContinue;
	}

	public void setIsContinue(Boolean isContinue) {
		this.isContinue = isContinue;
	}

	public Boolean isRollback() {
		return isRollback;
	}

	public void setIsRollback(Boolean isRollback) {
		this.isRollback = isRollback;
	}

	public Boolean isLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
