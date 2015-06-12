package com.zxq.iov.cloud.sp.vp.entity.journey;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 行程类
 * @author 叶荣杰
 * create date 2015-6-9 13:16
 * modify date 2015-6-11 17:22
 * @version 0.2, 2015-6-11
 */
@Entity()
@Table(name = "TB_JOURNEY")
public class Journey extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_journey";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TBOX_JOURNEY_ID", nullable = false, precision = 10, scale = 0)
    private Integer tboxJourneyId;

    @Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxId;

    @Column(name = "OWNER_ID", nullable = false, precision = 20, scale = 0)
    private Long ownerId;

    @Column(name = "USER_ID", precision = 20, scale = 0)
    private Long userId;

    @Column(name = "KEY_ID", precision = 20, scale = 0)
    private Long keyId;

    @Column(name = "VIN", length = 17)
    private String vin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", length = 7)
    private Date startTime;

    @Column(name = "START_VEHICLE_INFO_ID", precision = 20, scale = 0)
    private Long startVehicleInfoId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Column(name = "END_VEHICLE_INFO_ID", precision = 20, scale = 0)
    private Long endVehicleInfoId;

    @Column(name = "DISTANCE", precision = 10, scale = 0)
    private Integer distance;

    @Column(name = "AVG_SPEED", precision = 4, scale = 0)
    private Integer avgSpeed;

    @Column(name = "FUEL_CONSUMPTION", precision = 10, scale = 0)
    private Integer fuelConsumption;

    public Journey(){}

    public Journey(Integer tboxJourneyId, Long tboxId, Long ownerId, Long userId, Long keyId, String vin, Date startTime, Long startVehicleInfoId, Date endTime, Long endVehicleInfoId, Integer distance, Integer avgSpeed, Integer fuelConsumption) {
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

    public String getVin() {
        return vin;
    }

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
