package com.zxq.iov.cloud.sp.vp.api;


/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 10:48
 */
public interface IEventService {

    /**
     * 检查事件状态（外部定时器调用）
     * @param eventId   事件ID
     */
    void checkEvent(Long eventId);

    /**
     * 检查任务状态（外部定时器调用）
     * @param taskId    任务ID
     */
    void checkTask(Long taskId);

    /**
     * 检查步骤状态（外部定时器调用）
     * @param stepId    步骤ID
     */
    void checkStep(Long stepId);

}
