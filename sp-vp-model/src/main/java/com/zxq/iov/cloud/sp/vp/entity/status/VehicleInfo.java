package com.zxq.iov.cloud.sp.vp.entity.status;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;
import com.zxq.iov.cloud.sp.vp.common.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 车辆信息快照类
 * @author 叶荣杰
 * create time 2015-5-13 9:54
 * modify time 2015-8-4 11:01
 * @version 0.8, 2015-8-4
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

    @Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxId;

    @Column(name = "VIN", nullable = false, length=17)
    private String vin;

    @Column(name = "SOURCE_TYPE", nullable = false, precision = 2, scale = 0)
    private Integer sourceType;

    @Column(name = "SOURCE_ID", precision = 20, scale = 0)
    private Long sourceId;

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

    public VehicleInfo(Long tboxId, String vin, Integer sourceType, Long sourceId) {
        this.tboxId = tboxId;
        this.vin = vin;
        this.sourceType = (null==sourceType)?Constants.VEHICLE_INFO_SOURCE_STATUS:sourceType;
        if(null != sourceId) {
            this.sourceId = sourceId;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
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

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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
