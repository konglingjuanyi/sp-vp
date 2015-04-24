package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:54
 */
public abstract class AbstractEvent implements IEvent {

    public abstract Long startEvent();

    public abstract void finishEvent(Long eventId);

    public abstract Long startTask(Long eventId);

    public abstract Long finishTask(Long taskId);

    public abstract Long startStep(Long taskId);

    public abstract Long startAndFinishStep(Long taskId);

    public abstract Long finishStep(Long stepId);

    protected Event createEvent() {
        Event event = new Event();
        // 初始化默认属性
        return event;
    }

    protected Task createTask(Long eventId) {
        Task task = new Task();
        task.setEventId(eventId);
        // 处理默认属性
        return task;
    }
}
