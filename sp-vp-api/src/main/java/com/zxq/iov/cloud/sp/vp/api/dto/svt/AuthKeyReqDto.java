package com.zxq.iov.cloud.sp.vp.api.dto.svt;

/**
 * 安防 验证钥匙请求传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 17:03
 * modify date
 * @version 0.1, 2015-7-6
 */
public class AuthKeyReqDto {

    private Integer keyId;

    public AuthKeyReqDto() {}

    public AuthKeyReqDto(Integer keyId) {
        this.keyId = keyId;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }
}
