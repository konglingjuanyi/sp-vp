package com.zxq.iov.cloud.sp.vp.entity.event;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 事件实例类
 * @author 叶荣杰
 * create time 2015-4-23 11:50
 * modify time 2015-6-5 11:09
 * @version 0.3, 2015-6-5
 */
@Entity()
@Table(name = "TB_EVENT_INSTANCE")
public class EventInstance extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_event_instance";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "EVENT_DEFINITION_ID", nullable = false, precision = 20, scale = 0)
    private Long eventDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_DEFINITION_ID", nullable = false, insertable = false, updatable = false)
    private EventDefinition eventDefinition;

    @Column(name = "OWNER", nullable = false, length = 50)
    private String owner;

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

    public Long getEventDefinitionId() {
        return eventDefinitionId;
    }

    public void setEventDefinitionId(Long eventDefinitionId) {
        this.eventDefinitionId = eventDefinitionId;
    }

    public EventDefinition getEventDefinition() {
        return eventDefinition;
    }

    public void setEventDefinition(EventDefinition eventDefinition) {
        this.eventDefinition = eventDefinition;
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
}