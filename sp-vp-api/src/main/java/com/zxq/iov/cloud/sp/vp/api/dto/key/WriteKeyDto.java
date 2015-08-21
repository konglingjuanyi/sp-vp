package com.zxq.iov.cloud.sp.vp.api.dto.key;

import java.util.Date;

/**
 * 安防 写入电子钥匙传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:22
 * modify date 2015-8-12 14:41
 * @version 0.3, 2015-8-12
 */
public class WriteKeyDto {

    private Integer keyType;
    private byte[] keyValue;
    private Long keyReference;
    private Date keyValidityStartTime;
    private Date keyValidityEndTime;

    public WriteKeyDto() {}

    public WriteKeyDto(Integer keyType, byte[] keyValue, Long keyReference, Date keyValidityStartTime, Date keyValidityEndTime) {
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.keyReference = keyReference;
        this.keyValidityStartTime = keyValidityStartTime;
        this.keyValidityEndTime = keyValidityEndTime;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public byte[] getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }

    public Long getKeyReference() {
        return keyReference;
    }

    public void setKeyReference(Long keyReference) {
        this.keyReference = keyReference;
    }

    public Date getKeyValidityStartTime() {
        return keyValidityStartTime;
    }

    public void setKeyValidityStartTime(Date keyValidityStartTime) {
        this.keyValidityStartTime = keyValidityStartTime;
    }

    public Date getKeyValidityEndTime() {
        return keyValidityEndTime;
    }

    public void setKeyValidityEndTime(Date keyValidityEndTime) {
        this.keyValidityEndTime = keyValidityEndTime;
    }
}
