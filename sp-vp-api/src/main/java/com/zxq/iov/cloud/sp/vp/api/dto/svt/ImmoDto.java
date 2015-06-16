package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 阻止车辆启动传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-16 11:05
 * modify date
 * @version 0.1, 2015-6-16
 */
public class ImmoDto extends OtaDto {

    private Integer immoStatus;
    private String failureReason;

    public ImmoDto() {}

    public Integer getImmoStatus() {
        return immoStatus;
    }

    public void setImmoStatus(Integer immoStatus) {
        this.immoStatus = immoStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
