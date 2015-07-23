package com.zxq.iov.cloud.sp.vp.entity.event;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 事件定义类
 * @author 叶荣杰
 * create time 2015-6-2 16:59
 * modify time 2015-6-4 10:23
 * @version 0.2, 2015-6-4
 */
@Entity()
@Table(name = "TB_EVENT_DEFINITION")
public class EventDefinition extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_event_definition";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "NAME", nullable = false, length=50)
    private String name;

    @Column(name = "TYPE", precision = 6, scale = 0)
    private Integer type;

    @Column(name = "LIFECYCLE", precision = 11, scale = 0)
    private Integer lifecycle;

    @Column(name = "IS_EXCLUSIVE", nullable = false, precision = 1, scale = 0)
    private Boolean isExclusive;

    @Column(name = "IS_CONTINUE", nullable = false, precision = 1, scale = 0)
    private Boolean isContinue;

    @Column(name = "IS_ROLLBACK", nullable = false, precision = 1, scale = 0)
    private Boolean isRollback;

    public EventDefinition() {
    }

    public EventDefinition(String name, Integer type, Integer lifecycle, Boolean isExclusive, Boolean isContinue, Boolean isRollback) {
        this.name = name;
        this.type = type;
        this.lifecycle = lifecycle;
        this.isExclusive = isExclusive;
        this.isContinue = isContinue;
        this.isRollback = isRollback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Integer lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Boolean isExclusive() {
        return isExclusive;
    }

    public void setIsExclusive(Boolean isExclusive) {
        this.isExclusive = isExclusive;
    }

    public Boolean isContinue() {
        return isContinue;
    }

    public void setIsContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }

    public Boolean isRollback() {
        return isRollback;
    }

    public void setIsRollback(Boolean isRollback) {
        this.isRollback = isRollback;
    }
}
