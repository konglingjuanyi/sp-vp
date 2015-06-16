package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防 钥匙验证传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-15 15:30
 * modify date
 * @version 0.1, 2015-6-15
 */
public class KeyAuthDto extends OtaDto {

    private Boolean isAccepted;
    private String failureReason;

    public KeyAuthDto() {}

    public Boolean isAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
