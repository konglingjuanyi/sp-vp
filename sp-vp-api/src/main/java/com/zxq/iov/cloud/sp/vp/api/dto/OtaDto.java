package com.zxq.iov.cloud.sp.vp.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 安防 OTA基础DTO，包含非业务属性
 *
 * @author 叶荣杰
 * create date 2015-5-4 10:46
 * modify date 2015-7-6 13:45
 * @version 0.10, 2015-7-6
 */
public class OtaDto implements Serializable {

    private String vin;
    private Integer tboxSn;
    private Long tboxId;
    private String aid;
    private Integer mid;
    private Date eventCreateTime;
    private Long eventId;

    public OtaDto() {
    }

    public OtaDto(Integer tboxSn, String aid, Integer mid) {
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

    public OtaDto(Long tboxId, Date eventCreateTime, String aid, Integer mid) {
        this.tboxId = tboxId;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = eventCreateTime;
    }

    public OtaDto(String vin, String aid, Integer mid) {
        this.vin = vin;
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

    public Integer getTboxSn() {
        return tboxSn;
    }

    public void setTboxSn(Integer tboxSn) {
        this.tboxSn = tboxSn;
    }

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
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

}
