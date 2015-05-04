package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.BaseDto;
import com.zxq.iov.cloud.sp.vp.api.dto.EventDto;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskStepDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:08
 */
public class EncryptionEvent extends AbstractEvent {

    @Autowired
    private IEventDaoService eventDaoService;
    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private ITaskStepDaoService taskStepDaoService;

    @Override
    public void startEvent(EventDto eventDto) {
        // 开始交换密钥事件
        Event event = super.createEvent(eventDto);
        event.setCode(""); // 还未确定交换密码的具体命令代码
        // event.setExpirationTime(); // 未确定具体超时时间
        eventDaoService.createEvent(event);
        eventDto.setEventId(event.getId());
        this.startTask(eventDto); // 开始交换密钥任务
        // 推送第三方轮询来触发超时机制
    }

    @Override
    public void finishEvent(EventDto eventDto) {
        super.startAndFinishStep(eventDto);  // 先完成之前任务步骤
        super.finishTask(eventDto); // 完成任务
        super.finishEvent(eventDto); // 完成事件
    }

    @Override
    public void startTask(EventDto eventDto) {
        Task task = super.createTask(eventDto);
        task.setSort(1L); // 未确定是0开始还是1开始
        taskDaoService.createTask(task);
        eventDto.setTaskId(task.getId());
        this.startStep(eventDto);  // 开始任务第一步
        // 推送第三方轮询来出发超时机制
    }

}