package com.zxq.iov.cloud.sp.vp.entity.event;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:51
 */
public class Task {

    private Long id;
    private Long eventId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
