package com.zxq.iov.cloud.sp.vp.entity.config;


import com.saicmotor.telematics.framework.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 TBOX配置类
 * @author 叶荣杰
 * create date 2015-7-29 11:16
 * modify date
 * @version 0.1, 2015-7-29
 */
@Entity()
@Table(name = "TB_TBOX_CONFIG_SETTING")
public class TboxConfigSetting extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_tbox_config_setting";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @Column(name = "TBOX_CONFIG_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxConfigId;

    @Column(name = "TBOX_ID", nullable = false, precision = 20, scale = 0)
    private Long tboxId;

    @Column(name = "KEY_ID", nullable = false, precision = 11, scale = 0)
    private Integer keyId;

    @Column(name = "VALUE", nullable = false, length = 255)
    private String value;

    @Column(name = "CONFIG_DELTA", nullable = false, precision = 10, scale = 0)
    private Integer configDelta;

    public TboxConfigSetting(){}

    public TboxConfigSetting(Long tboxConfigId, Long tboxId, Integer keyId, String value, Integer configDelta) {
        this.tboxConfigId = tboxConfigId;
        this.tboxId = tboxId;
        this.keyId = keyId;
        this.value = value;
        this.configDelta = configDelta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTboxConfigId() {
        return tboxConfigId;
    }

    public void setTboxConfigId(Long tboxConfigId) {
        this.tboxConfigId = tboxConfigId;
    }

    public Long getTboxId() {
        return tboxId;
    }

    public void setTboxId(Long tboxId) {
        this.tboxId = tboxId;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getConfigDelta() {
        return configDelta;
    }

    public void setConfigDelta(Integer configDelta) {
        this.configDelta = configDelta;
    }
}
