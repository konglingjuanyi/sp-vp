package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 阻止车辆启动传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-16 11:05
 * modify date 2015-7-10 11:22
 * @version 0.2, 2015-7-10
 */
public class ProtectStrategySettingDto extends OtaDto {

    private Integer alarmTypeId;
    private Boolean alarmEnabled;

    public ProtectStrategySettingDto() {}

    public ProtectStrategySettingDto(Integer alarmTypeId, Boolean alarmEnabled) {
        this.alarmTypeId = alarmTypeId;
        this.alarmEnabled = alarmEnabled;
    }

    public Integer getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(Integer alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public Boolean isAlarmEnabled() {
        return alarmEnabled;
    }

    public void setAlarmEnabled(Boolean alarmEnabled) {
        this.alarmEnabled = alarmEnabled;
    }
}
