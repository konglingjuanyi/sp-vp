package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 任务类
 * @author 叶荣杰
 * create time 2015-4-23 11:51:07
 * modify by
 * modify time
 * @version 0.1, 2015-4-23
 */
@Entity()
@Table(name = "TB_TASK")
public class Task extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_task";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    @Column(name = "VIN", nullable = false, length=17)
    private String vin;

    @Column(name = "CODE", nullable = false, length=32)
    private String code;

    @Column(name = "PLATFORM", precision = 4, scale = 0)
    private Integer platform;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", nullable = false, length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_TIME", length = 7)
    private Date expirationTime;

    @Column(name = "SORT", precision = 6, scale = 0)
    private Long sort;

    @Column(name = "ERROR_TYPE", precision = 1, scale = 0)
    private Integer errorType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }
}
