package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防 被盗警报传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-15 12:26
 * modify date
 * @version 0.1, 2015-6-15
 */
public class StolenAlarmDto extends OtaDto {

    private Long id;
    private Long tboxId;
    private Integer alarmType;
    private String alarmData;
    private Date alarmTime;
    private Long vehicleInfoId;

    public StolenAlarmDto() {}

    public StolenAlarmDto(Long id, Long tboxId, Integer alarmType, String alarmData, Date alarmTime) {
        this.id = id;
        this.tboxId = tboxId;
        this.alarmType = alarmType;
        this.alarmData = alarmData;
        this.alarmTime = alarmTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getTboxId() {
        return tboxId;
    }

    @Override
    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Long getVehicleInfoId() {
        return vehicleInfoId;
    }

    public void setVehicleInfoId(Long vehicleInfoId) {
        this.vehicleInfoId = vehicleInfoId;
    }
}
