package com.zxq.iov.cloud.sp.vp.api.dto.key;

/**
 * 安防 删除电子钥匙传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:36
 * modify date 2015-8-12 14:41
 * @version 0.2, 2015-8-12
 */
public class DeleteKeyDto {

    private Long keyReference;

    public DeleteKeyDto() {}

    public DeleteKeyDto(Long keyReference) {
        this.keyReference = keyReference;
    }

    public Long getKeyReference() {
        return keyReference;
    }

    public void setKeyReference(Long keyReference) {
        this.keyReference = keyReference;
    }

}
