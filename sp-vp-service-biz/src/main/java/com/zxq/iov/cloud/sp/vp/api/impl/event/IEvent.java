package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.api.dto.BaseDto;
import org.springframework.scripting.bsh.BshScriptUtils;

/**
 * User: 荣杰
 * Date: 2015/4/24
 * Time: 16:59
 */
public interface IEvent {

    /**
     * 开始事件
     * @param baseDto   基础传输对象
     * @return          基础传输对象
     */
    BaseDto startEvent(BaseDto baseDto);

    /**
     * 结束事件
     * @param baseDto  基础传输对象
     */
    void finishEvent(BaseDto baseDto);

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
     * @return          任务ID
     */
    Long startAndFinishStep(Long taskId);

    /**
     * 结束步骤
     * @param stepId    步骤ID
     * @return          任务ID
     */
    Long finishStep(Long stepId);

}
