package com.zxq.iov.cloud.sp.vp.service.domain;

import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;

import java.util.Map;

/**
 * 安防 事件对象
 *
 * @author 叶荣杰
 * create date 2015-8-11 14:55
 * modify date
 * @version 0.1, 2015-8-11
 */
public class Event {

    private Long id;
    private TaskInstance task;
    private StepInstance step;
    private String owner;
    private String code;
    private Map<String, Object> paramMap;
    private Boolean isRetry;
    private Object result;

    public Event() {
        this.isRetry = false;
    }

    public Event(Long id) {
        this.id = id;
        this.isRetry = false;
    }

    public Event(Long id, String owner, String code) {
        this.id = id;
        this.owner = owner;
        this.code = code;
        this.isRetry = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskInstance getTask() {
        return task;
    }

    public void setTask(TaskInstance task) {
        this.task = task;
    }

    public StepInstance getStep() {
        return step;
    }

    public void setStep(StepInstance step) {
        this.step = step;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Boolean isRetry() {
        return isRetry;
    }

    public void setIsRetry(Boolean isRetry) {
        this.isRetry = isRetry;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
