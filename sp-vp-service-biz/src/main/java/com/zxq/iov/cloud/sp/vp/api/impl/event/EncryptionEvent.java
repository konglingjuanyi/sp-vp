package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.BaseDto;
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
    public BaseDto startEvent(BaseDto baseDto) {
        // 开始交换密钥事件
        Event event = super.createEvent(baseDto);
        event.setCode(""); // 还未确定交换密码的具体命令代码
        // event.setExpirationTime(); // 未确定具体超时时间
        eventDaoService.createEvent(event);
        // 开始交换密钥任务
        Long taskId = this.startTask(event.getId());
        // 开始任务第一步
        this.startStep(taskId);
        // 推送第三方轮询来触发超时机制
        baseDto.setTaskId(taskId);
        return baseDto;
    }

    @Override
    public void finishEvent(BaseDto baseDto) {
        // 先完成之前任务步骤
        super.startAndFinishStep(baseDto.getTaskId());
        // 完成任务
        super.finishTask(baseDto.getTaskId());
        // 完成事件
        super.finishEvent(baseDto);
    }

    @Override
    public Long startTask(Long eventId) {
        Task task = super.createTask(eventId);
        task.setSort(1L); // 未确定是0开始还是1开始
        taskDaoService.createTask(task);
        // 推送第三方轮询来出发超时机制
        return task.getId();
    }

}