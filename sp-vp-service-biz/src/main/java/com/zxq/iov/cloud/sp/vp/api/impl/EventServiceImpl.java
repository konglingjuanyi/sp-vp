package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEventService;

/**
 * User: 荣杰
 * Date: 2015/4/24
 * Time: 17:18
 */
public class EventServiceImpl implements IEventService {
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
