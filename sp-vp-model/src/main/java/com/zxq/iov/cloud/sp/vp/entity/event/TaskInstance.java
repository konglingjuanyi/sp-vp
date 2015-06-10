package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 任务实例类
 * @author 叶荣杰
 * create time 2015-4-23 11:50
 * modify time 2015-6-5 13:13
 * @version 0.3, 2015-6-5
 */
@Entity()
@Table(name = "TB_TASK_INSTANCE")
public class TaskInstance extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_task_instance";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TASK_DEFINITION_ID", nullable = false, precision = 20, scale = 0)
    private Long taskDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_DEFINITION_ID", nullable = false, insertable = false, updatable = false)
    private TaskDefinition taskDefinition;

    @Column(name = "EVENT_INSTANCE_ID", nullable = false, precision = 20, scale = 0)
    private Long eventInstanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_INSTANCE_ID", nullable = false, insertable = false, updatable = false)
    private EventInstance eventInstance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Column(name = "ERROR_CODE", precision = 1, scale = 0)
    private Integer errorCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskDefinitionId() {
        return taskDefinitionId;
    }

    public void setTaskDefinitionId(Long taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }

    public Long getEventInstanceId() {
        return eventInstanceId;
    }

    public void setEventInstanceId(Long eventInstanceId) {
        this.eventInstanceId = eventInstanceId;
    }

    public EventInstance getEventInstance() {
        return eventInstance;
    }

    public void setEventInstance(EventInstance eventInstance) {
        this.eventInstance = eventInstance;
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

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}