package com.zxq.iov.cloud.sp.vp.entity.event;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 步骤定义类
 * @author 叶荣杰
 * create time 2015-6-2 17:43
 * modify time 2015-6-4 11:00
 * @version 0.2, 2015-6-4
 */
@Entity()
@Table(name = "TB_STEP_DEFINITION")
public class StepDefinition extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_step_definition";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TASK_DEFINITION_ID", nullable = false, precision = 20, scale = 0)
    private Long taskDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_DEFINITION_ID", nullable = false, insertable = false, updatable = false)
    private TaskDefinition taskDefinition;

    @Column(name = "NAME", nullable = false, length=50)
    private String name;

    @Column(name = "LIFECYCLE", precision = 8, scale = 0)
    private Integer lifecycle;

    @Column(name = "PRE_STEP_DEFINITION_ID", precision = 20, scale = 0)
    private Long preStepDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRE_STEP_DEFINITION_ID", insertable = false, updatable = false)
    private StepDefinition preStepDefinition;

    @Column(name = "RETRY_LIMIT", precision = 4, scale = 0)
    private Integer retryLimit;

    @Column(name = "START_CODE", nullable = false, length=50)
    private String startCode;

    @Column(name = "IS_ROLLBACK", nullable = false, precision = 1, scale = 0)
    private Boolean isRollback;

    @Column(name = "IS_LAST", nullable = false, precision = 1, scale = 0)
    private Boolean isLast;

    @Column(name = "SORT", nullable = false, precision = 4, scale = 0)
    private Integer sort;

    public StepDefinition() {
    }

    public StepDefinition(Long taskDefinitionId, String name, Integer lifecycle, Long preStepDefinitionId, Integer retryLimit, String startCode, Boolean isRollback, Boolean isLast, Integer sort) {
        this.taskDefinitionId = taskDefinitionId;
        this.name = name;
        this.lifecycle = lifecycle;
        this.preStepDefinitionId = preStepDefinitionId;
        this.retryLimit = retryLimit;
        this.startCode = startCode;
        this.isRollback = isRollback;
        this.isLast = isLast;
        this.sort = sort;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Long getPreStepDefinitionId() {
        return preStepDefinitionId;
    }

    public void setPreStepDefinitionId(Long preStepDefinitionId) {
        this.preStepDefinitionId = preStepDefinitionId;
    }

    public StepDefinition getPreStepDefinition() {
        return preStepDefinition;
    }

    public void setPreStepDefinition(StepDefinition preStepDefinition) {
        this.preStepDefinition = preStepDefinition;
    }

    public Integer getRetryLimit() {
        return retryLimit;
    }

    public void setRetryLimit(Integer retryLimit) {
        this.retryLimit = retryLimit;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public Boolean isRollback() {
        return isRollback;
    }

    public void setIsRollback(Boolean isRollback) {
        this.isRollback = isRollback;
    }

    public Boolean isLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
