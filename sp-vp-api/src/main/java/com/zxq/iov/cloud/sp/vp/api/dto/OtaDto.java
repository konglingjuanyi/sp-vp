package com.zxq.iov.cloud.sp.vp.api.dto;

import java.util.Date;

/**
 * 安防 OTA基础DTO，包含非业务属性
 *
 * @author 叶荣杰
 * create date 2015-5-4 10:46
 * modify date 2015-6-12 9:57
 * @version 0.5, 2015-6-12
 */
public class OtaDto {

    private String vin;
    private String tboxSn;
    private Long tboxId;
    private Integer platform;
    private String aid;
    private Integer mid;
    private Date eventCreateTime;
    private Long eventId;
    private Integer requestId;

    public OtaDto() {
    }

    public OtaDto(String tboxSn, String aid, Integer mid) {
        this.tboxSn = tboxSn;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = new Date();
    }

    public OtaDto(Long tboxId, String aid, Integer mid) {
        this.tboxId = tboxId;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = new Date();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTboxSn() {
        return tboxSn;
    }

    public void setTboxSn(String tboxSn) {
        this.tboxSn = tboxSn;
    }

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
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

    public Date getEventCreateTime() {
        return eventCreateTime;
    }

    public void setEventCreateTime(Date eventCreateTime) {
        this.eventCreateTime = eventCreateTime;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
}
