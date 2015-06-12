package com.zxq.iov.cloud.sp.vp.entity.call;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 呼叫类
 * @author 叶荣杰
 * create date 2015-6-11 9:23
 * modify date
 * @version 0.1, 2015-6-11
 */
@Entity()
@Table(name = "TB_CALL")
public class Call extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_call";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxId;

    @Column(name = "TYPE", nullable = false, precision = 4, scale = 0)
    private Integer type;

    @Column(name = "CALL_TYPE", precision = 4, scale = 0)
    private Integer callType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", nullable = false, length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    public Call(){}

    public Call(Long tboxId, Integer type, Integer callType, Date startTime, Date endTime) {
        this.tboxId = tboxId;
        this.type = type;
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
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
}
