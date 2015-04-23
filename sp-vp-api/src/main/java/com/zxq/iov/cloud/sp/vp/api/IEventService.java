package com.zxq.iov.cloud.sp.vp.api;


/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 10:48
 */
public interface IEventService {

    /**
     * 开始事件
     * @return          事件ID
     */
    Long startEvent();

    /**
     * 结束事件
     * @param eventId  事件ID
     */
    void finishEvent(Long eventId);

    /**
     * 开始任务
     * @param eventId   事件ID
     * @return          任务ID
     */
    Long startTask(Long eventId);

    /**
     * 结束任务
     * @param taskId    任务ID
     * @return          事件ID
     */
    Long finishTask(Long taskId);

    /**
     * 开始步骤
     * @param taskId    任务ID
     * @return          步骤ID
     */
    Long startStep(Long taskId);

    /**
     * 开始并直接结束步骤（适用于TBOX主动发起的步骤）
     * @param taskId    任务ID
     * @return          步骤ID
     */
    Long startAndFinishStep(Long taskId);

    /**
     * 结束步骤
     * @param stepId    步骤ID
     * @return          任务ID
     */
    Long finishStep(Long stepId);

    /**
     * 降步骤ID与RequestId绑定
     * @param stepId    步骤ID
     * @param requestId RequestId
     */
    void bindStepWithRequestId(Long stepId, Integer requestId);

    /**
     * 验证步骤ID与RequestId一致性
     * @param taskId    任务ID
     * @param requestId RequestId
     */
    void validateStepWithRequestId(Long taskId, Integer requestId);

    /**
     * 检查事件状态
     * @param eventId   事件ID
     */
    void checkEvent(Long eventId);

    /**
     * 检查任务状态
     * @param taskId    任务ID
     */
    void checkTask(Long taskId);

    /**
     * 检查步骤状态
     * @param stepId    步骤ID
     */
    void checkStep(Long stepId);

}
