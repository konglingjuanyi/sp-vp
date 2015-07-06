package com.zxq.iov.cloud.sp.vp.api.dto.config;

/**
 * 安防 验证钥匙请求传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 17:14
 * modify date
 * @version 0.1, 2015-7-6
 */
public class ReadConfigReqDto {

    private Long[] tboxConfigsettingIds;

    public ReadConfigReqDto() {}

    public ReadConfigReqDto(Long[] tboxConfigsettingIds) {
        this.tboxConfigsettingIds = tboxConfigsettingIds;
    }

    public Long[] getTboxConfigsettingIds() {
        return tboxConfigsettingIds;
    }

    public void setTboxConfigsettingIds(Long[] tboxConfigsettingIds) {
        this.tboxConfigsettingIds = tboxConfigsettingIds;
    }
}
