package com.zxq.iov.cloud.sp.vp.entity.key;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 安防服务 电子钥匙类
 * @author 叶荣杰
 * create date 2015-6-23 11:37
 * modify date
 * @version 0.1, 2015-6-23
 */
@Entity()
@Table(name = "TB_REMOTE_KEY")
public class RemoteKey extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_remote_key";

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

    @Column(name = "VALUE", nullable = false, precision = 10, scale = 0)
    private Integer value;

    @Column(name = "REFERENCE", precision = 10, scale = 0)
    private Integer reference;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VALID_START_TIME", length = 7)
    private Date validStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VALID_END_TIME", length = 7)
    private Date validEndTime;

    public RemoteKey(){}

    public RemoteKey(Long tboxId, Integer type, Integer value, Integer reference, Date validStartTime, Date validEndTime) {
        this.tboxId = tboxId;
        this.type = type;
        this.value = value;
        this.reference = reference;
        this.validStartTime = validStartTime;
        this.validEndTime = validEndTime;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }
}
