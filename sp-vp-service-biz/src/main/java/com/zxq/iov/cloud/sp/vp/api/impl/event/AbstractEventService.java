package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:54
 */
public abstract class AbstractEventService implements IEventService {

    public abstract Long startEvent();

    public Event createEvent() {
        Event event = new Event();
        // 初始化默认属性
        return event;
    }

    @Override
    public void finishEvent(Long eventId) {

    }

    public abstract Long startTask(Long eventId);

    public Task createTask(Long eventId) {
        Task task = new Task();
        task.setEventId(eventId);
        // 处理默认属性
        return task;
    }

    @Override
    public Long finishTask(Long taskId) {
        return null;
    }

    @Override
    public Long startStep(Long taskId) {
        return null;
    }

    @Override
    public Long startAndFinishStep(Long taskId) {
        return null;
    }

    @Override
    public Long finishStep(Long stepId) {
        return null;
    }

    @Override
    public void bindStepWithRequestId(Long stepId, Integer requestId) {
        // 绑定stepId和requestId
    }

    @Override
    public void validateStepWithRequestId(Long taskId, Integer requestId) {
        // 验证一致后完成该步骤，并更新其任务
    }

    @Override
    public void checkEvent(Long eventId) {
        // 判断开始时间、超时时间等参数，更新事件状态
    }

    @Override
    public void checkTask(Long taskId) {
        // 判断开始时间、超时时间等参数，更新任务状态
    }

    @Override
    public void checkStep(Long stepId) {
        // 判断开始时间、超时时间、重试次数等参数，更新步骤状态或重试
    }
}
