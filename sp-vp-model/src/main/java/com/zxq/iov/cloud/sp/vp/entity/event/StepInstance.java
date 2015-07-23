package com.zxq.iov.cloud.sp.vp.entity.event;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 步骤类
 * @author 叶荣杰
 * create time 2015-4-23 13:59
 * modify time 2015-6-15 14:23
 * @version 0.5, 2015-6-15
 */
@Entity()
@Table(name = "TB_STEP_INSTANCE")
public class StepInstance extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_step_instance";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "STEP_DEFINITION_ID", nullable = false, precision = 20, scale = 0)
    private Long stepDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_DEFINITION_ID", nullable = false, insertable = false, updatable = false)
    private StepDefinition stepDefinition;

    @Column(name = "TASK_INSTANCE_ID", nullable = false, precision = 20, scale = 0)
    private Long taskInstanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_INSTANCE_ID", nullable = false, insertable = false, updatable = false)
    private TaskInstance taskInstance;

    @Column(name = "OWNER", length = 50)
    private String owner;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME", length = 7)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME", length = 7)
    private Date endTime;

    @Column(name = "ERROR_CODE", precision = 1, scale = 0)
    private Integer errorCode;

    @Column(name = "RETRY_COUNT", nullable = false, precision = 3, scale = 0)
    private Integer retryCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStepDefinitionId() {
        return stepDefinitionId;
    }

    public void setStepDefinitionId(Long stepDefinitionId) {
        this.stepDefinitionId = stepDefinitionId;
    }

    public StepDefinition getStepDefinition() {
        return stepDefinition;
    }

    public void setStepDefinition(StepDefinition stepDefinition) {
        this.stepDefinition = stepDefinition;
    }

    public Long getTaskInstanceId() {
        return taskInstanceId;
    }

    public void setTaskInstanceId(Long taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public TaskInstance getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstance taskInstance) {
        this.taskInstance = taskInstance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }
}
