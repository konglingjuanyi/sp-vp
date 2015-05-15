package com.zxq.iov.cloud.sp.vp.api.dto.status;

/**
 * 安防 车辆警告信息传输对象
 *
 * @author 叶荣杰
 * create date 2015-5-13 15:58
 * modify date 2015-5-14 17:27
 * @version 0.2, 2015-5-14
 */
public class VehicleAlertDto {

    private Long id;
    private String code;
    private String name;
    private Integer value;
    private String data;

    public VehicleAlertDto() {}

    public VehicleAlertDto(Long id, String code, String name, Integer value, String data) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.value = value;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
