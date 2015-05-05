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
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:54
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
        }
        else {
            // 抛出异常
        }
    }

    protected Event createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setVin(eventDto.getVin());
        event.setPlatform(eventDto.getPlatform());
        event.setStartTime(new Date());
        return event;
    }

    protected Task createTask(EventDto eventDto) {
        Task task = new Task();
        task.setEvent(eventDaoService.findEventById(eventDto.getEventId()));
        task.setVin(eventDto.getVin());
        task.setPlatform(eventDto.getPlatform());
        task.setStartTime(new Date());
        return task;
    }

    protected TaskStep createTaskStep(EventDto eventDto) {
        TaskStep taskStep = new TaskStep();
        taskStep.setTask(taskDaoService.findTaskById(eventDto.getTaskId()));
        taskStep.setAid(eventDto.getAid());
        taskStep.setMid(eventDto.getMid());
        return taskStep;
    }
}
