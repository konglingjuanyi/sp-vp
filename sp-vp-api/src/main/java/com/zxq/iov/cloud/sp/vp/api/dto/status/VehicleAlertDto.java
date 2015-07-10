package com.zxq.iov.cloud.sp.vp.api.dto.status;

import java.io.Serializable;
import java.util.Date;

/**
 * 安防 车辆警告信息传输对象
 *
 * @author 叶荣杰
 * create date 2015-5-13 15:58
 * modify date 2015-6-24 15:24
 * @version 0.3, 2015-6-24
 */
public class VehicleAlertDto implements Serializable {

    private Integer alertId;
    private Date alertTime;
    private VehiclePosDto vehiclePosDto;
    private Boolean alertStatus;
    private Integer alertData;

    public VehicleAlertDto() {}

    public VehicleAlertDto(Integer alertId, Date alertTime, VehiclePosDto vehiclePosDto, Boolean alertStatus, Integer alertData) {
        this.alertId = alertId;
        this.alertTime = alertTime;
        this.vehiclePosDto = vehiclePosDto;
        this.alertStatus = alertStatus;
        this.alertData = alertData;
    }

    public Integer getAlertId() {
        return alertId;
    }

    public void setAlertId(Integer alertId) {
        this.alertId = alertId;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public VehiclePosDto getVehiclePosDto() {
        return vehiclePosDto;
    }

    public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
        this.vehiclePosDto = vehiclePosDto;
    }

    public Boolean isAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(Boolean alertStatus) {
        this.alertStatus = alertStatus;
    }

    public Integer getAlertData() {
        return alertData;
    }

    public void setAlertData(Integer alertData) {
        this.alertData = alertData;
    }
}
