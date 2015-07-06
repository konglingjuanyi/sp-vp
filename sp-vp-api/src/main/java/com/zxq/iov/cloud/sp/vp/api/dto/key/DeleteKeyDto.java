package com.zxq.iov.cloud.sp.vp.api.dto.key;

/**
 * 安防 删除电子钥匙传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:36
 * modify date
 * @version 0.1, 2015-7-6
 */
public class DeleteKeyDto {

    private Integer keyReference;

    public DeleteKeyDto() {}

    public DeleteKeyDto(Integer keyReference) {
        this.keyReference = keyReference;
    }

    public Integer getKeyReference() {
        return keyReference;
    }

    public void setKeyReference(Integer keyReference) {
        this.keyReference = keyReference;
    }

}
