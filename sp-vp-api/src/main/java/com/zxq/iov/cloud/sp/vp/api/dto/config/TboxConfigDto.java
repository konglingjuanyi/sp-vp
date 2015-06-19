package com.zxq.iov.cloud.sp.vp.api.dto.config;

/**
 * 安防服务 bCall接口
 * @author 叶荣杰
 * create date 2015-4-22 13:48
 * modify date 2015-6-19 10:23
 * @version 0.2, 2015-6-19
 */
public class TboxConfigDto {

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
