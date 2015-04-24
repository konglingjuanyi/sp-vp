package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:08
 */
public class EncryptionEvent extends AbstractEvent {

    @Override
    public Long startEvent() {
        Event event = super.createEvent();
        // 处理加密事件个性化属性（类型、超时时间、超时次数）
        // 推送第三方轮询来触发超时机制
        return event.getId();
    }

    @Override
    public void finishEvent(Long eventId) {

    }

    @Override
    public Long startTask(Long eventId) {
        Task task = super.createTask(eventId);
        // 处理个性化属性
        // 推送第三方轮询来出发超时机制
        return task.getId();
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

}