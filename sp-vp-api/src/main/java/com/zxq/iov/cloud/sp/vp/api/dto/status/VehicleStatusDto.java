package com.zxq.iov.cloud.sp.vp.api.dto.status;

/**
 * 安防 车辆状态传输对象
 *
 * @author 叶荣杰
 * create date 2015-5-13 15:10
 * modify date 2015-6-24 16:54
 * @version 0.3, 2015-6-24
 */
public class VehicleStatusDto {

    private String code;
    private Integer value;

    public VehicleStatusDto() {}

    public VehicleStatusDto(String code, Integer value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
