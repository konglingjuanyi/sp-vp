package com.zxq.iov.cloud.sp.vp.api.dto.config;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 TBOX配置传输对象
 * @author 叶荣杰
 * create date 2015-4-22 13:48
 * modify date 2015-7-27 17:25
 * @version 0.3, 2015-7-27
 */
public class TboxConfigDto extends OtaDto {

    private Integer packageCount;
    private Integer configDelta;
    private Long    eventId;

    public TboxConfigDto() {
        this.packageCount = 0;
    }

    public Integer getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }

    public Integer getConfigDelta() {
        return configDelta;
    }

    public void setConfigDelta(Integer configDelta) {
        this.configDelta = configDelta;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
