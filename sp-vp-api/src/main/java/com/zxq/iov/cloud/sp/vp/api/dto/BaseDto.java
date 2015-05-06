package com.zxq.iov.cloud.sp.vp.api.dto;

/**
 * 安防 基础DTO，包含非业务属性
 *
 * @author 叶荣杰
 * create date 2015-5-4 10:46
 * modify date 2015-5-5 17:23
 * @version 0.2, 2015-5-5
 */
public class BaseDto {

    private String vin;
    private String tbox;
    private Integer platform;
    private String aid;
    private Integer mid;
    private Integer requestId;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTbox() {
        return tbox;
    }

    public void setTbox(String tbox) {
        this.tbox = tbox;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
}
