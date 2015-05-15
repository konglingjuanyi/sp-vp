package com.zxq.iov.cloud.sp.vp.entity.status;

import com.zxq.iov.cloud.core.dal.entity.MyBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 安防服务 车辆状态信息类
 * @author 叶荣杰
 * create time 2015-5-13 10:38
 * modify time 2015-5-14 17:05
 * @version 0.2, 2015-5-14
 */
@Entity()
@Table(name = "TB_VEHICLE_STATUS")
public class VehicleStatus extends MyBaseEntity<Long> implements Serializable {

    private static final String SEQ_NAME = "seq_tb_vehicle_status";

    @Id
    @Column(name = "ID", nullable = false, updatable = false, length=20)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ_NAME)
    @TableGenerator(name = SEQ_NAME, table = MY_SEQ_TABLE, allocationSize = 1,
            pkColumnName = "pk_name", valueColumnName = "current_value", pkColumnValue = SEQ_NAME)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_INFO_ID", nullable = false)
    private VehicleInfo vehicleInfo;

    @Column(name = "TYPE", nullable = false, precision = 6, scale = 0)
    private Integer type;

    @Column(name = "CODE", nullable = false, length=50)
    private String code;

    @Column(name = "NAME", nullable = false, length=50)
    private String name;

    @Column(name = "VALUE", nullable = false, precision = 11, scale = 0)
    private Integer value;

    @Column(name = "DATA", length=255)
    private String data;

    public VehicleStatus() {}

    public VehicleStatus(Long id, String code, String name, Integer value, String data, Integer type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.value = value;
        this.data = data;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
