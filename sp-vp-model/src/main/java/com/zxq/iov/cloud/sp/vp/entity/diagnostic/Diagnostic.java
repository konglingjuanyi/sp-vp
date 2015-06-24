package com.zxq.iov.cloud.sp.vp.entity.diagnostic;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 远程诊断类
 * @author 叶荣杰
 * create date 2015-6-23 17:56
 * modify date 2015-6-24 9:58
 * @version 0.2, 2015-6-24
 */
@Entity()
@Table(name = "TB_DIAGNOSTIC")
public class Diagnostic extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_diagnostic";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxId;

    @Column(name = "CAN_ID", nullable = false, length = 4)
    private String canId;

    @Column(name = "SERVICE_ID", nullable = false, length = 2)
    private String serviceId;

    @Column(name = "PARAMETER", nullable = false)
    private String parameter;

    @Column(name = "RESULT")
    private String result;

    @Column(name = "EVENT_ID", precision = 20, scale = 0)
    private Long eventId;

    public Diagnostic(){}

    public Diagnostic(Long tboxId, String canId, String serviceId, String parameter) {
        this.tboxId = tboxId;
        this.canId = canId;
        this.serviceId = serviceId;
        this.parameter = parameter;
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

    public String getCanId() {
        return canId;
    }

    public void setCanId(String canId) {
        this.canId = canId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
