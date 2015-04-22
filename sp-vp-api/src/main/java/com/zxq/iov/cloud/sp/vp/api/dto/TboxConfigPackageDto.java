package com.zxq.iov.cloud.sp.vp.api.dto;

import java.io.Serializable;
import java.util.List;

/**
 * User: 荣杰
 * Date: 2015/4/22
 * Time: 13:24
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
