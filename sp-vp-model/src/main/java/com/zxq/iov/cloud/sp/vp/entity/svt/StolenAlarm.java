package com.zxq.iov.cloud.sp.vp.entity.svt;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 被盗报警类
 * @author 叶荣杰
 * create date 2015-6-15 11:50
 * modify date 2015-6-26 12:02
 * @version 0.2, 2015-6-26
 */
@Entity()
@Table(name = "TB_STOLEN_ALARM")
public class StolenAlarm extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_stolen_alarm";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxId;

    @Column(name = "ALARM_TYPE", nullable = false, precision = 4, scale = 0)
    private Integer alarmType;

    @Column(name = "ALARM_DATA", length = 255)
    private String alarmData;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ALARM_TIME", nullable = false, length = 7)
    private Date alarmTime;

    @Column(name = "VEHICLE_INFO_ID", precision = 20, scale = 0)
    private Long vehicleInfoId;

    public StolenAlarm(){}

    public StolenAlarm(Integer alarmType, String alarmData, Date alarmTime) {
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

    public Long getTboxId() {
        return tboxId;
    }

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
