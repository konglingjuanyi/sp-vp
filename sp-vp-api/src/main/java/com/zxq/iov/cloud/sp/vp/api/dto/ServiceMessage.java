package com.zxq.iov.cloud.sp.vp.api.dto;

/**
 * 安防 队列DTO
 *
 * @author 叶荣杰
 * create date 2015-7-6 11:36
 * modify date
 * @version 0.1, 2015-7-6
 */
public class ServiceMessage extends OtaDto {

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
