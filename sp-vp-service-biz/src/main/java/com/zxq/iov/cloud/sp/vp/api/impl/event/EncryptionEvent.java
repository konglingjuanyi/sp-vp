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

import java.util.*;

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

    private static final String AID = "100";
    private static final String CODE = ""; // 还未确定交换密码的具体命令代码，还有事件和任务的命令代码是否完全一致？
    private static final Integer RETRY = 0;
    private static final Map<Integer, Integer> MID = new HashMap();
    static {
        MID.put(11, 12);
        MID.put(12, 13);
        MID.put(13, 14);
    }

    @Override
    public void startEvent(EventDto eventDto) {
        if(AID.equals(eventDto.getAid())) {
            Event event = super.createEvent(eventDto);
            event.setCode(CODE);
            // event.setExpirationTime(); // 未确定具体超时时间
            eventDaoService.createEvent(event);
            eventDto.setEventId(event.getId());
            // 推送第三方轮询来触发超时机制

            this.startTask(eventDto); // 开始交换密钥任务
        }
        else {
            // 抛出AID不匹配异常
        }
    }

    @Override
    public void finishEvent(EventDto eventDto) {
        super.finishStep(eventDto); // 完成步骤
        super.finishTask(eventDto); // 完成任务
        super.finishEvent(eventDto); // 完成事件
    }

    @Override
    public void startTask(EventDto eventDto) {
        Task task = super.createTask(eventDto);
        task.setSort(0L);
        task.setCode(CODE);
        // task.setExpirationTime();  // 未确定具体超时时间
        taskDaoService.createTask(task);
        eventDto.setTaskId(task.getId());

        Event event = task.getEvent(); // 激活当前任务
        event.setActiveTask(task);
        eventDaoService.updateEvent(event);
        // 推送第三方轮询来出发超时机制

        this.startAndFinishStep(eventDto);  // 因为是TBOX主动发起，第一步直接结束
    }

    @Override
    public void startAndFinishStep(EventDto eventDto) {
        TaskStep taskStep = super.createTaskStep(eventDto);
        taskStep.setRespAid(AID);
        taskStep.setRespMid(MID.get(taskStep.getMid()));
        taskStep.setRetry(RETRY);
        taskStep.setEndTime(new Date());
        taskStepDaoService.createTaskStep(taskStep);

        Integer nextMid = MID.get(MID.get(taskStep.getMid()));
        if(null != nextMid) { // 如果有后续步骤，则直接创建下一步骤
            eventDto.setMid(nextMid);
            TaskStep nextTaskStep = super.createTaskStep(eventDto);
            nextTaskStep.setRespAid(AID);
            nextTaskStep.setRespMid(MID.get(nextTaskStep.getMid()));
            nextTaskStep.setRetry(RETRY);
            taskStepDaoService.createTaskStep(taskStep);

            Task task = nextTaskStep.getTask(); // 激活当前步骤
            task.setActiveTaskStep(nextTaskStep);
            taskDaoService.updateTask(task);
        }
    }

    @Override
    public void startStep(EventDto eventDto) {
        TaskStep taskStep = super.createTaskStep(eventDto);
        taskStep.setRespAid(AID);
        taskStep.setRespMid(MID.get(taskStep.getMid()));
        taskStep.setRetry(RETRY);
        taskStepDaoService.createTaskStep(taskStep);

        Task task = taskStep.getTask(); // 激活当前步骤
        task.setActiveTaskStep(taskStep);
        taskDaoService.updateTask(task);
    }
}