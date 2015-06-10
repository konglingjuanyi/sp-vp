package com.zxq.iov.cloud.sp.vp.api.dto.event;

/**
 * 安防 任务定义DTO
 *
 * @author 叶荣杰
 * create date 2015-6-3 13:19
 * modify date 2015-6-4 14:44
 * @version 0.2, 2015-6-4
 */
public class TaskDefinitionDto {

    private Long id;
    private Long eventDefinitionId;
    private String name;
    private Integer lifecycle;
    private Long preTaskDefinitionId;
    private Integer cycleLimit;
    private Boolean isExclusive;
    private Boolean isContinue;
    private Boolean isRollback;
    private Boolean isLast;
    private Integer sort;

    public TaskDefinitionDto() {
    }

    public TaskDefinitionDto(Long id, Long eventDefinitionId, String name, Integer lifecycle, Long preTaskDefinitionId, Integer cycleLimit, Boolean isExclusive, Boolean isContinue, Boolean isRollback, Boolean isLast, Integer sort) {
        this.id = id;
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
