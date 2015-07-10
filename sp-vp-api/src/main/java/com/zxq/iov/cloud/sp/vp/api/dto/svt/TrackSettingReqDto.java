package com.zxq.iov.cloud.sp.vp.api.dto.svt;

import java.io.Serializable;

/**
 * 安防 跟踪设置请求传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 17:03
 * modify date
 * @version 0.1, 2015-7-6
 */
public class TrackSettingReqDto implements Serializable {

    private Integer trackInterval;
    private Integer tracks;

    public TrackSettingReqDto() {}

    public TrackSettingReqDto(Integer trackInterval, Integer tracks) {
        this.trackInterval = trackInterval;
        this.tracks = tracks;
    }

    public Integer getTrackInterval() {
        return trackInterval;
    }

    public void setTrackInterval(Integer trackInterval) {
        this.trackInterval = trackInterval;
    }

    public Integer getTracks() {
        return tracks;
    }

    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }
}
