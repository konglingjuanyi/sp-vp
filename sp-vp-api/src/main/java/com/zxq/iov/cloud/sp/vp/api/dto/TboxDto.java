package com.zxq.iov.cloud.sp.vp.api.dto;

/**
 * 安防 TBOX DTO
 *
 * @author 叶荣杰
 * create date 2015-4-23 10:30:07
 * @version 0.1, 2015-4-23
 */
public class TboxDto extends EventDto {

    private Long tboxId;
    private String tboxSn;

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
    }

    public String getTboxSn() {
        return tboxSn;
    }

    public void setTboxSn(String tboxSn) {
        this.tboxSn = tboxSn;
    }
}
