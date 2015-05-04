package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.api.dto.BaseDto;
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

    public abstract BaseDto startEvent(BaseDto baseDto);

    @Override
    public void finishEvent(BaseDto baseDto) {
        Event event = eventDaoService.findEventById(baseDto.getEventId());
        event.setEndTime(new Date());
        eventDaoService.updateEvent(event);
    }

    public abstract Long startTask(Long eventId);

    @Override
    public Long finishTask(Long taskId) {
        Task task = taskDaoService.findTaskById(taskId);
        task.setEndTime(new Date());
        taskDaoService.updateTask(task);
        return task.getEvent().getId();
    }

    @Override
    public Long startStep(Long taskId) {
        TaskStep taskStep = this.createTaskStep(taskId);
        taskStepDaoService.createTaskStep(taskStep);
        return taskStep.getId();
    }

    @Override
    public Long startAndFinishStep(Long taskId) {
        TaskStep taskStep = this.createTaskStep(taskId);
        taskStep.setEndTime(new Date());
        taskStepDaoService.createTaskStep(taskStep);
        return taskStep.getTask().getId();
    }

    @Override
    public Long finishStep(Long stepId) {
        TaskStep taskStep = taskStepDaoService.findTaskStepById(stepId);
        taskStep.setEndTime(new Date());
        taskStepDaoService.updateTaskStep(taskStep);
        return taskStep.getTask().getId();
    }

    protected Event createEvent(BaseDto baseDto) {
        Event event = new Event();
        event.setVin(baseDto.getVin());
        event.setPlatform(baseDto.getPlatform());
        event.setStartTime(new Date());
        return event;
    }

    protected Task createTask(Long eventId) {
        Task task = new Task();
        task.setEvent(eventDaoService.findEventById(eventId));
        task.setStartTime(new Date());
        return task;
    }

    protected TaskStep createTaskStep(Long taskId) {
        TaskStep taskStep = new TaskStep();
        taskStep.setTask(taskDaoService.findTaskById(taskId));
        taskStep.setStartTime(new Date());
        return taskStep;
    }
}
