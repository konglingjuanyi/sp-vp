package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 事件类
 * @author 叶荣杰
 * create time 2015-4-23 11:50:07
 * modify by
 * modify time
 * @version 0.1, 2015-4-23
 */
@Entity()
@Table(name = "TB_EVENT")
public class Event extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_event";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "VIN", nullable = false, length=17)
    private String vin;

    @Column(name = "CODE", nullable = false, length=32)
    private String code;

    @Column(name = "PLATFORM", precision = 4, scale = 0)
    private Integer platform;

    @Column(name = "DELAY_TIME", precision = 4, scale = 0)
    private Integer delayTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", nullable = false, length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_TIME", length = 7)
    private Date expirationTime;

    @Column(name = "ERROR_TYPE", precision = 1, scale = 0)
    private Integer errorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVE_TASK")
    private Task activeTask;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public Task getActiveTask() {
        return activeTask;
    }

    public void setActiveTask(Task activeTask) {
        this.activeTask = activeTask;
    }
}
