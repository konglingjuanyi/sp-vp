package com.zxq.iov.cloud.sp.vp.api.dto.event;

/**
 * 安防 事件定义DTO
 *
 * @author 叶荣杰
 * create date 2015-6-3 9:59
 * modify date 2015-6-4 10:30
 * @version 0.2, 2015-6-4
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
