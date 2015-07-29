package com.zxq.iov.cloud.sp.vp.api.dto.config;

/**
 * 安防服务 配置参数传输对象
 * @author 叶荣杰
 * create date 2015-4-22 12:28
 * modify date 2015-7-29 13：38
 * @version 0.2, 2015-7-29
 */
public class TboxConfigSettingDto {

    private Integer id;
    private byte[] value;

    public TboxConfigSettingDto() {
    }

    public TboxConfigSettingDto(Integer id, byte[] value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}
