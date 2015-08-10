package com.zxq.iov.cloud.sp.vp.service.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 安防 OTA基础DTO，包含非业务属性
 *
 * @author 叶荣杰
 * create date 2015-5-4 10:46
 * modify date 2015-7-13 13:14
 * @version 0.11, 2015-7-13
 */
public class Ota implements Serializable {

    private String vin;
    private Integer tboxSn;
    private Long tboxId;
    private String aid;
    private Integer mid;
    private Date eventCreateTime;
    private Long eventId;

    public Ota() {
    }

    public Ota(Integer tboxSn, String aid, Integer mid) {
        this.tboxSn = tboxSn;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = new Date();
    }

    public Ota(Long tboxId, String aid, Integer mid) {
        this.tboxId = tboxId;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = new Date();
    }

    public Ota(Long tboxId, Date eventCreateTime, String aid, Integer mid) {
        this.tboxId = tboxId;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = eventCreateTime;
    }

    public Ota(String vin, String aid, Integer mid) {
        this.vin = vin;
        this.aid = aid;
        this.mid = mid;
        this.eventCreateTime = new Date();
    }

    public Ota(Long tboxId, String vin, String aid, Integer mid) {
        this.tboxId = tboxId;
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
