package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 事件参数类
 * @author 叶荣杰
 * create time 2015-5-4 9:25
 * modify time 2015-6-8 15:38
 * @version 0.3, 2015-6-8
 */
@Entity()
@Table(name = "TB_EVENT_PARAMETER")
public class EventParameter extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_event_parameter";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TYPE", nullable = false, precision = 4, scale = 0)
    private Integer type;

    @Column(name = "STEP_INSTANCE_ID", precision = 20, scale = 0)
    private Long stepInstanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_INSTANCE_ID", insertable = false, updatable = false)
    private StepInstance stepInstance;

    @Column(name = "NAME", length=50)
    private String name;

    @Column(name = "VALUE", length=255)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStepInstanceId() {
        return stepInstanceId;
    }

    public void setStepInstanceId(Long stepInstanceId) {
        this.stepInstanceId = stepInstanceId;
    }

    public StepInstance getStepInstance() {
        return stepInstance;
    }

    public void setStepInstance(StepInstance stepInstance) {
        this.stepInstance = stepInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
