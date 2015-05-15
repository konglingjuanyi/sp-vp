package com.zxq.iov.cloud.sp.vp.entity.status;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 车辆信息类
 * @author 叶荣杰
 * create time 2015-5-13 9:54
 * modify time 2015-5-15 13:45
 * @version 0.2, 2015-5-15
 */
@Entity()
@Table(name = "TB_VEHICLE_INFO")
public class VehicleInfo extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_vehicle_info";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "VIN", nullable = false, length=17)
    private String vin;

    @Column(name = "SOURCE_TYPE", nullable = false, precision = 2, scale = 0)
    private Integer sourceType;

    @Column(name = "EVENT_ID", precision = 20, scale = 0)
    private Long eventId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STATUS_TIME", length = 7)
    private Date statusTime;

    @Column(name = "OWNER_ID", nullable = false, precision = 20, scale = 0)
    private Long ownerId;

    @Column(name = "USER_ID", precision = 20, scale = 0)
    private Long userId;

    public VehicleInfo(){}

    public VehicleInfo(Integer sourceType, Long eventId, Date statusTime) {
        this.sourceType = sourceType;
        this.eventId = eventId;
        this.statusTime = statusTime;
    }

    public VehicleInfo(Long id, String vin, Integer sourceType, Long eventId, Date statusTime, Long ownerId, Long userId) {
        this.id = id;
        this.vin = vin;
        this.sourceType = sourceType;
        this.eventId = eventId;
        this.statusTime = statusTime;
        this.ownerId = ownerId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
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
}
