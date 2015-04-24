package com.zxq.iov.cloud.sp.vp.api;


/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 10:48
 */
public interface IEventService {

    /**
     * 将步骤ID与RequestId绑定（TBOX Connector调用）
     * @param stepId    步骤ID
     * @param requestId RequestId
     */
    void bindStepWithRequestId(Long stepId, Integer requestId);

    /**
     * 验证步骤ID与RequestId一致性（TBOX Connector调用）
     * @param taskId    任务ID
     * @param requestId RequestId
     */
    void validateStepWithRequestId(Long taskId, Integer requestId);

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
