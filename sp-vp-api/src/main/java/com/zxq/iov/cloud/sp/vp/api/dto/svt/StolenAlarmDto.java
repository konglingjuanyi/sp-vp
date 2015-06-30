package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;

import java.util.Date;

/**
 * 安防 被盗警报传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-15 12:26
 * modify date 2015-6-26 12:01
 * @version 0.3, 2015-6-26
 */
public class StolenAlarmDto {

    private Integer alarmTypeId;
    private Boolean trigAlarmSts;
    private Boolean currentAlarmSts;
    private VehiclePosDto vehiclePosDto;
    private Date alarmTime;
    private String alarmData;

    public StolenAlarmDto() {}

    public StolenAlarmDto(Integer alarmTypeId, Boolean trigAlarmSts, Boolean currentAlarmSts,
                          VehiclePosDto vehiclePosDto, Date alarmTime, String alarmData) {
        this.alarmTypeId = alarmTypeId;
        this.trigAlarmSts = trigAlarmSts;
        this.currentAlarmSts = currentAlarmSts;
        this.vehiclePosDto = vehiclePosDto;
        this.alarmTime = alarmTime;
        this.alarmData = alarmData;
    }

    public Integer getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(Integer alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public Boolean isTrigAlarmSts() {
        return trigAlarmSts;
    }

    public void setTrigAlarmSts(Boolean trigAlarmSts) {
        this.trigAlarmSts = trigAlarmSts;
    }

    public Boolean isCurrentAlarmSts() {
        return currentAlarmSts;
    }

    public void setCurrentAlarmSts(Boolean currentAlarmSts) {
        this.currentAlarmSts = currentAlarmSts;
    }

    public VehiclePosDto getVehiclePosDto() {
        return vehiclePosDto;
    }

    public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
        this.vehiclePosDto = vehiclePosDto;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData;
    }

}
