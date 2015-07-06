package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 服务消息
 *
 * @author 叶荣杰
 * create date 2015-7-6 11:36
 * modify date
 * @version 0.1, 2015-7-6
 */
public class ServiceMessage extends OtaDto {

    private String appData;

    public ServiceMessage(String aid, Integer mid, Long tboxId, String vin, Long eventId) {
        setAid(aid);
        setMid(mid);
        setVin(vin);
        setEventId(eventId);
    }

    public String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }
}
