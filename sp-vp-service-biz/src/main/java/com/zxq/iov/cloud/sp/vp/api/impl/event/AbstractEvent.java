package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.EventDto;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskStepDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 安防 事件抽象类
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:54
 * modify date 2015-5-5 17:26
 * @version 0.2, 2015-5-5
 */
public abstract class AbstractEvent implements IEvent {

    @Autowired
    private IEventDaoService eventDaoService;
    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private ITaskStepDaoService taskStepDaoService;

    public abstract void startEvent(EventDto eventDto);

    @Override
    public void finishEvent(EventDto eventDto) {
        Event event = taskDaoService.findTaskById(eventDto.getTaskId()).getEvent();
        event.setEndTime(new Date());
        eventDaoService.updateEvent(event);
    }

    public abstract void startTask(EventDto eventDto);

    @Override
    public void finishTask(EventDto eventDto) {
        Task task = taskDaoService.findTaskById(eventDto.getTaskId());
        task.setEndTime(new Date());
        taskDaoService.updateTask(task);

        Event event = task.getEvent(); // 去除事件中该任务的激活状态
        event.setActiveTask(null);
        eventDaoService.updateEvent(event);
    }

    public abstract void startStep(EventDto eventDto);

    public abstract void startAndFinishStep(EventDto eventDto);

    @Override
    public void finishStep(EventDto eventDto) {
        TaskStep taskStep = taskDaoService.findTaskById(eventDto.getTaskId()).getActiveTaskStep();
        if(taskStep.getAid().equals(eventDto.getAid()) &&
                taskStep.getMid().intValue() == eventDto.getMid().intValue()) {
            Date now = new Date();
            taskStep.setStartTime(now);
            taskStep.setEndTime(now);
            taskStepDaoService.updateTaskStep(taskStep);

            Task task = taskStep.getTask(); // 去除任务中该步骤的激活状态
            task.setActiveTaskStep(null);
            taskDaoService.updateTask(task);
        }
        else {
            // 抛出异常
            throw new RuntimeException("消息异常");
        }
    }

    protected Event createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setVin(eventDto.getVin());
        event.setTbox(eventDto.getTbox());
        event.setPlatform(eventDto.getPlatform());
        event.setStartTime(new Date());
        return event;
    }

    protected Task createTask(EventDto eventDto) {
        Task task = new Task();
        task.setEvent(eventDaoService.findEventById(eventDto.getEventId()));
        task.setVin(eventDto.getVin());
        task.setTbox(eventDto.getTbox());
        task.setPlatform(eventDto.getPlatform());
        task.setStartTime(new Date());
        return task;
    }

    protected TaskStep createTaskStep(EventDto eventDto) {
        TaskStep taskStep = new TaskStep();
        taskStep.setTask(taskDaoService.findTaskById(eventDto.getTaskId()));
        taskStep.setVin(eventDto.getVin());
        taskStep.setTbox(eventDto.getTbox());
        taskStep.setAid(eventDto.getAid());
        taskStep.setMid(eventDto.getMid());
        return taskStep;
    }
}
