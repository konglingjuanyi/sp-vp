package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import java.io.Serializable;

/**
 * 安防 固定车辆请求传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 17:03
 * modify date
 * @version 0.1, 2015-7-6
 */
public class ImmobiliseReqDto implements Serializable {

    private Integer immoStatus;

    public ImmobiliseReqDto() {}

    public ImmobiliseReqDto(Integer immoStatus) {
        this.immoStatus = immoStatus;
    }

    public Integer getImmoStatus() {
        return immoStatus;
    }

    public void setImmoStatus(Integer immoStatus) {
        this.immoStatus = immoStatus;
    }
}
