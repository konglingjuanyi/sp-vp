package com.zxq.iov.cloud.sp.vp.api.dto.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防 bCall传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-10 17:10
 * modify date 2015-6-11 10:54
 * @version 0.2, 2015-6-11
 */
public class BcallDto extends OtaDto {

    private Long id;
    private Long tboxId;
    private Integer callType;
    private Date startTime;
    private Date endTime;

    public BcallDto() {}

    public BcallDto(Long id, Long tboxId, Integer callType, Date startTime, Date endTime) {
        this.id = id;
        this.tboxId = tboxId;
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
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
}
