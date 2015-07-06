package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import java.util.Date;
import java.util.List;

/**
 * 安防 固定车辆请求传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 17:10
 * modify date
 * @version 0.1, 2015-7-6
 */
public class UpdateProtectStrategyReqDto {

    private Date startTime;
    private Date endTime;
    private List<ProtectStrategySettingDto> protectStrategySettingDtos;

    public UpdateProtectStrategyReqDto() {}

    public UpdateProtectStrategyReqDto(Date startTime, Date endTime, List<ProtectStrategySettingDto> protectStrategySettingDtos) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.protectStrategySettingDtos = protectStrategySettingDtos;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<ProtectStrategySettingDto> getProtectStrategySettingDtos() {
        return protectStrategySettingDtos;
    }

    public void setProtectStrategySettingDtos(List<ProtectStrategySettingDto> protectStrategySettingDtos) {
        this.protectStrategySettingDtos = protectStrategySettingDtos;
    }
}
