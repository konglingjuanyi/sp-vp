package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.EventDto;

/**
 * User: 荣杰
 * Date: 2015/4/24
 * Time: 16:59
 */
public interface IEvent {

    /**
     * 开始事件
     * @param eventDto  事件传输对象
     */
    void startEvent(EventDto eventDto);

    /**
     * 结束事件
     * @param eventDto  事件传输对象
     */
    void finishEvent(EventDto eventDto);

    /**
     * 开始任务
     * @param eventDto  事件传输对象
     */
    void startTask(EventDto eventDto);

    /**
     * 结束任务
     * @param eventDto  事件传输对象
     */
    void finishTask(EventDto eventDto);

    /**
     * 开始步骤
     * @param eventDto  事件传输对象
     */
    void startStep(EventDto eventDto);

    /**
     * 开始并直接结束步骤（适用于TBOX主动发起的步骤）
     * @param eventDto  事件传输对象
     * @return          事件传输对象
     */
    EventDto startAndFinishStep(EventDto eventDto);

    /**
     * 结束步骤
     * @param stepId    步骤ID
     * @return          任务ID
     */
    Long finishStep(Long stepId);

}
