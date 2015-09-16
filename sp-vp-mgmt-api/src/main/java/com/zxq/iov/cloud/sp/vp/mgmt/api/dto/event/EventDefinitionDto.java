/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 * 2015-06-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventDefinitionDto
 *
 * sp - sp-vp-mgmt-api
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event;

/**
 * 安防服务 事件定义传输对象
 */
public class EventDefinitionDto {

    private Long id;
    private String name;
    private Integer type;
    private Integer lifecycle;
    private Boolean isExclusive;
    private Boolean isContinue;
    private Boolean isRollback;

    public EventDefinitionDto() {
    }

    public EventDefinitionDto(Long id, String name, Integer type, Integer lifecycle, Boolean isExclusive, Boolean isContinue, Boolean isRollback) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lifecycle = lifecycle;
        this.isExclusive = isExclusive;
        this.isContinue = isContinue;
        this.isRollback = isRollback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
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
}
