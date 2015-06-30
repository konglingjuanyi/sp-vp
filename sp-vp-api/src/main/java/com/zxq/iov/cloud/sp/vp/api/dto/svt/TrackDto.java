package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;

import java.util.Date;

/**
 * 安防 追踪点传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-25 16:51
 * modify date
 * @version 0.1, 2015-6-25
 */
public class TrackDto extends OtaDto {

    private Date trackTime;
    private VehiclePosDto vehiclePosDto;
    private Integer gnssSpeed;
    private Boolean gsmAntConnected;
    private Boolean gnssAntConnected;
    private Boolean vehicleBatteryConnected;
    private Integer intBattV;
    private Integer vehicleAlarmStatus;
    private Integer engineStatus;
    private Integer powerMode;
    private Integer lastKeySeen;
    private Integer fuelLevelPrc;
    private Integer fuelRange;
    private Boolean canBusActive;
    private Date lastCanBusActivityTime;
    private Integer ttnTrackPoint;

    public TrackDto() {}

    public TrackDto(Date trackTime, VehiclePosDto vehiclePosDto, Integer gnssSpeed, Boolean gsmAntConnected,
                    Boolean gnssAntConnected, Boolean vehicleBatteryConnected, Integer intBattV,
                    Integer vehicleAlarmStatus, Integer engineStatus, Integer powerMode, Integer lastKeySeen,
                    Integer fuelLevelPrc, Integer fuelRange, Boolean canBusActive, Date lastCanBusActivityTime,
                    Integer ttnTrackPoint) {
        this.trackTime = trackTime;
        this.vehiclePosDto = vehiclePosDto;
        this.gnssSpeed = gnssSpeed;
        this.gsmAntConnected = gsmAntConnected;
        this.gnssAntConnected = gnssAntConnected;
        this.vehicleBatteryConnected = vehicleBatteryConnected;
        this.intBattV = intBattV;
        this.vehicleAlarmStatus = vehicleAlarmStatus;
        this.engineStatus = engineStatus;
        this.powerMode = powerMode;
        this.lastKeySeen = lastKeySeen;
        this.fuelLevelPrc = fuelLevelPrc;
        this.fuelRange = fuelRange;
        this.canBusActive = canBusActive;
        this.lastCanBusActivityTime = lastCanBusActivityTime;
        this.ttnTrackPoint = ttnTrackPoint;
    }

    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public VehiclePosDto getVehiclePosDto() {
        return vehiclePosDto;
    }

    public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
        this.vehiclePosDto = vehiclePosDto;
    }

    public Integer getGnssSpeed() {
        return gnssSpeed;
    }

    public void setGnssSpeed(Integer gnssSpeed) {
        this.gnssSpeed = gnssSpeed;
    }

    public Boolean isGsmAntConnected() {
        return gsmAntConnected;
    }

    public void setGsmAntConnected(Boolean gsmAntConnected) {
        this.gsmAntConnected = gsmAntConnected;
    }

    public Boolean isGnssAntConnected() {
        return gnssAntConnected;
    }

    public void setGnssAntConnected(Boolean gnssAntConnected) {
        this.gnssAntConnected = gnssAntConnected;
    }

    public Boolean isVehicleBatteryConnected() {
        return vehicleBatteryConnected;
    }

    public void setVehicleBatteryConnected(Boolean vehicleBatteryConnected) {
        this.vehicleBatteryConnected = vehicleBatteryConnected;
    }

    public Integer getIntBattV() {
        return intBattV;
    }

    public void setIntBattV(Integer intBattV) {
        this.intBattV = intBattV;
    }

    public Integer getVehicleAlarmStatus() {
        return vehicleAlarmStatus;
    }

    public void setVehicleAlarmStatus(Integer vehicleAlarmStatus) {
        this.vehicleAlarmStatus = vehicleAlarmStatus;
    }

    public Integer getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(Integer engineStatus) {
        this.engineStatus = engineStatus;
    }

    public Integer getPowerMode() {
        return powerMode;
    }

    public void setPowerMode(Integer powerMode) {
        this.powerMode = powerMode;
    }

    public Integer getLastKeySeen() {
        return lastKeySeen;
    }

    public void setLastKeySeen(Integer lastKeySeen) {
        this.lastKeySeen = lastKeySeen;
    }

    public Integer getFuelLevelPrc() {
        return fuelLevelPrc;
    }

    public void setFuelLevelPrc(Integer fuelLevelPrc) {
        this.fuelLevelPrc = fuelLevelPrc;
    }

    public Integer getFuelRange() {
        return fuelRange;
    }

    public void setFuelRange(Integer fuelRange) {
        this.fuelRange = fuelRange;
    }

    public Boolean isCanBusActive() {
        return canBusActive;
    }

    public void setCanBusActive(Boolean canBusActive) {
        this.canBusActive = canBusActive;
    }

    public Date getLastCanBusActivityTime() {
        return lastCanBusActivityTime;
    }

    public void setLastCanBusActivityTime(Date lastCanBusActivityTime) {
        this.lastCanBusActivityTime = lastCanBusActivityTime;
    }

    public Integer getTtnTrackPoint() {
        return ttnTrackPoint;
    }

    public void setTtnTrackPoint(Integer ttnTrackPoint) {
        this.ttnTrackPoint = ttnTrackPoint;
    }
}
