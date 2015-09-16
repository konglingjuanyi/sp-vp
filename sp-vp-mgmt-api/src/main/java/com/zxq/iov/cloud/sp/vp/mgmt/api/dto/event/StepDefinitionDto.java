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
 * com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.StepDefinitionDto
 *
 * sp - sp-vp-mgmt-api
 */

package com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event;

/**
 * 安防服务 步骤定义传输对象
 */
public class StepDefinitionDto {

    private Long id;
    private Long taskDefinitionId;
    private String name;
    private Integer lifecycle;
    private Long preStepDefinitionId;
    private Integer retryLimit;
    private String startCode;
    private Boolean isRollback;
    private Boolean isLast;
    private Integer sort;

    public StepDefinitionDto() {
    }

    public StepDefinitionDto(Long id, Long taskDefinitionId, String name, Integer lifecycle,
                             Long preStepDefinitionId, Integer retryLimit, String startCode,
                             Boolean isRollback, Boolean isLast, Integer sort) {
        this.id = id;
        this.taskDefinitionId = taskDefinitionId;
        this.name = name;
        this.lifecycle = lifecycle;
        this.preStepDefinitionId = preStepDefinitionId;
        this.retryLimit = retryLimit;
        this.startCode = startCode;
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

    public Long getTaskDefinitionId() {
        return taskDefinitionId;
    }

    public void setTaskDefinitionId(Long taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
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

    public Long getPreStepDefinitionId() {
        return preStepDefinitionId;
    }

    public void setPreStepDefinitionId(Long preStepDefinitionId) {
        this.preStepDefinitionId = preStepDefinitionId;
    }

    public Integer getRetryLimit() {
        return retryLimit;
    }

    public void setRetryLimit(Integer retryLimit) {
        this.retryLimit = retryLimit;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
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
