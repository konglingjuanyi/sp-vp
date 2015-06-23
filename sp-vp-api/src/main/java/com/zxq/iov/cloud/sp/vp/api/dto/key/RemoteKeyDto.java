package com.zxq.iov.cloud.sp.vp.api.dto.key;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防 电子钥匙DTO
 *
 * @author 叶荣杰
 * create date 2015-6-23 11:55
 * modify date
 * @version 0.1, 2015-6-23
 */
public class RemoteKeyDto extends OtaDto {

    private Long id;
    private Long tboxId;
    private Integer type;
    private Integer value;
    private Integer reference;
    private Date validStartTime;
    private Date validEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getTboxId() {
        return tboxId;
    }

    @Override
    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }
}
