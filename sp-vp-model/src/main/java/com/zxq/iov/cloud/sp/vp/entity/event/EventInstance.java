/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-23       荣杰         1.0            Initial Version
 * 2015-06-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.entity.event.EventInstance
 *
 * sp - sp-vp-model
 */

package com.zxq.iov.cloud.sp.vp.entity.event;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 事件实例实体类
 */
@Entity()
@Table(name = "TB_EVENT_INSTANCE")
public class EventInstance extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_event_instance";

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

    @Column(name = "OWNER", nullable = false, length = 50)
    private String owner;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Column(name = "ERROR_CODE", precision = 1, scale = 0)
    private Integer errorCode;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}