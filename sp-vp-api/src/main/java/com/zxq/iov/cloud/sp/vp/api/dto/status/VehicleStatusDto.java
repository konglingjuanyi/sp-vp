package com.zxq.iov.cloud.sp.vp.api.dto.status;

/**
 * 安防 车辆状态传输对象
 *
 * @author 叶荣杰
 * create date 2015-5-13 15:10
 * modify date 2015-5-14 17:18
 * @version 0.2, 2015-5-14
 */
public class VehicleStatusDto {

    private Long id;
    private String code;
    private String name;
    private Integer value;

    public VehicleStatusDto() {}

    public VehicleStatusDto(Long id, String code, String name, Integer value) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
