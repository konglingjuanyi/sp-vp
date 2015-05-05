package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEventService;

/**
 * 安防 事件服务实现类
 *
 * @author 叶荣杰
 * create date 2015-4-24 17:18:07
 * @version 0.1, 2015-4-24
 */
public class EventServiceImpl implements IEventService {

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
