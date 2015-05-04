package com.zxq.iov.cloud.sp.vp.api.dto;

/**
 * 安防 基础DTO，包含非业务属性
 *
 * @author 叶荣杰
 * create date 2015-5-4 10:46:07
 * @version 0.1, 2015-5-4
 */
public class BaseDto {

    private String vin;
    private Integer platform;
    private Long eventId;
    private Long taskId;
    private Long stepId;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }
}
