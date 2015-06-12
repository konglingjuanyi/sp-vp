package com.zxq.iov.cloud.sp.vp.api.dto.journey;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防 行程传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-9 13:14
 * modify date 2015-6-11 17:24
 * @version 0.2, 2015-6-11
 */
public class JourneyDto extends OtaDto {

    private Long id;
    private Integer tboxJourneyId;
    private Long tboxId;
    private Long ownerId;
    private Long userId;
    private Long keyId;
    private String vin;
    private Date startTime;
    private Long startVehicleInfoId;
    private Date endTime;
    private Long endVehicleInfoId;
    private Integer distance;
    private Integer avgSpeed;
    private Integer fuelConsumption;

    public JourneyDto() {}

    public JourneyDto(Long id, Integer tboxJourneyId, Long tboxId, Long ownerId, Long userId, Long keyId, String vin, Date startTime, Long startVehicleInfoId, Date endTime, Long endVehicleInfoId, Integer distance, Integer avgSpeed, Integer fuelConsumption) {
        this.id = id;
        this.tboxJourneyId = tboxJourneyId;
        this.tboxId = tboxId;
        this.ownerId = ownerId;
        this.userId = userId;
        this.keyId = keyId;
        this.vin = vin;
        this.startTime = startTime;
        this.startVehicleInfoId = startVehicleInfoId;
        this.endTime = endTime;
        this.endVehicleInfoId = endVehicleInfoId;
        this.distance = distance;
        this.avgSpeed = avgSpeed;
        this.fuelConsumption = fuelConsumption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTboxJourneyId() {
        return tboxJourneyId;
    }

    public void setTboxJourneyId(Integer tboxJourneyId) {
        this.tboxJourneyId = tboxJourneyId;
    }

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    @Override
    public String getVin() {
        return vin;
    }

    @Override
    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getStartVehicleInfoId() {
        return startVehicleInfoId;
    }

    public void setStartVehicleInfoId(Long startVehicleInfoId) {
        this.startVehicleInfoId = startVehicleInfoId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getEndVehicleInfoId() {
        return endVehicleInfoId;
    }

    public void setEndVehicleInfoId(Long endVehicleInfoId) {
        this.endVehicleInfoId = endVehicleInfoId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Integer avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
