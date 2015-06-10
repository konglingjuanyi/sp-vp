package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 事件规则类
 * @author 叶荣杰
 * create date 2015-6-5 16:54
 * modify date
 * @version 0.1, 2015-6-5
 */
@Entity()
@Table(name = "TB_EVENT_RULE")
public class EventRule extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_event_rule";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "STEP_DEFINITION_ID", precision = 20, scale = 0)
    private Long stepDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_DEFINITION_ID", insertable = false, updatable = false)
    private StepDefinition stepDefinition;

    @Column(name = "NAME", nullable = false, length=50)
    private String name;

    @Column(name = "OPERATOR", nullable = false, length=50)
    private String operator;

    @Column(name = "VALUE", length=255)
    private String value;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
