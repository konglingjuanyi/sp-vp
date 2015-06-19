package com.zxq.iov.cloud.sp.vp.api.dto.config;

import java.io.Serializable;
import java.util.List;

/**
 * 安防服务 配置包传输对象
 * @author 叶荣杰
 * create date 2015-4-22 13:24
 * modify date
 * @version 0.1, 2015-4-22
 */
public class TboxConfigPackageDto implements Serializable {

    private List<TboxConfigSettingDto> tboxConfigSettingDtos;
    private Integer packageId;

    public List<TboxConfigSettingDto> getTboxConfigSettingDtos() {
        return tboxConfigSettingDtos;
    }

    public void setTboxConfigSettingDtos(List<TboxConfigSettingDto> tboxConfigSettingDtos) {
        this.tboxConfigSettingDtos = tboxConfigSettingDtos;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

}
