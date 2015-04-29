package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 任务步骤类
 * @author 叶荣杰
 * create time 2015-4-23 13:59:07
 * modify by
 * modify time
 * @version 0.1, 2015-4-23
 */
@Entity()
@Table(name = "TB_TASK_STEP")
public class TaskStep extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_task_step";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID", nullable = false)
    private Task task;

    @Column(name = "REQUEST_ID", precision = 6, scale = 0)
    private Integer requestId;

    @Column(name = "TYPE", precision = 6, scale = 0)
    private Integer type;

    @Column(name = "AID", nullable = false, length=3)
    private String aid;

    @Column(name = "MID", nullable = false, precision = 4, scale = 0)
    private Integer mid;

    @Column(name = "RESP_AID", length=3)
    private String respAid;

    @Column(name = "RESP_MID", precision = 4, scale = 0)
    private Integer respMid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", nullable = false, length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Column(name = "RETRY", precision = 3, scale = 0)
    private Integer retry;

    @Column(name = "ERROR_TYPE", precision = 1, scale = 0)
    private Integer errorType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getRespAid() {
        return respAid;
    }

    public void setRespAid(String respAid) {
        this.respAid = respAid;
    }

    public Integer getRespMid() {
        return respMid;
    }

    public void setRespMid(Integer respMid) {
        this.respMid = respMid;
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

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }
}
