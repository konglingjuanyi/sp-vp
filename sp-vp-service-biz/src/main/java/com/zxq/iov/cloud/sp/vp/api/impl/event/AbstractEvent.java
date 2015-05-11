package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.alibaba.dubbo.common.json.JSON;
import com.zxq.iov.cloud.sp.vp.api.dto.EventDto;
import com.zxq.iov.cloud.sp.vp.api.exception.AidNotMatchException;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IEventParameterDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IStepDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;
import com.zxq.iov.cloud.sp.vp.entity.event.Step;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
    private IStepDaoService stepDaoService;
    @Autowired
    private IEventParameterDaoService taskParameterDaoService;

    protected String aid; // 应用ID
    protected String eventCode; // 事件命令代码
    protected String taskCode; // 任务命令代码
    protected boolean isExclusive; // 是否互斥
    protected boolean isContinue; // 是否继续

    public abstract void startEvent(EventDto eventDto);

    @Override
    public void finishEvent(EventDto eventDto) {
        Event event = taskDaoService.findTaskById(eventDto.getTaskId()).getEvent();
        if(null == event.getEndTime()) {
            event.setEndTime(new Date());
            eventDaoService.updateEvent(event);
        }
    }

    public abstract void startTask(EventDto eventDto);

    @Override
    public void finishTask(EventDto eventDto) {
        Task task = taskDaoService.findTaskById(eventDto.getTaskId());
        if(null == task.getEndTime()) {
            task.setEndTime(new Date());
            taskDaoService.updateTask(task);
        }
    }

    public abstract Object startStep(EventDto eventDto, Class clazz);

    @Override
    public void finishStep(EventDto eventDto, Object result) {
        Step step = taskDaoService.findTaskById(eventDto.getTaskId()).getActiveStep();
        if(step.getAid().equals(eventDto.getAid()) &&
                step.getMid().intValue() == eventDto.getMid().intValue()) {
            Date now = new Date();
            step.setStartTime(now);
            step.setEndTime(now);
            stepDaoService.updateStep(step);

            // 保存结果参数
            EventParameter eventParameter = new EventParameter();
            eventParameter.setEvent(step.getTask().getEvent());
            eventParameter.setTask(step.getTask());
            eventParameter.setStep(step);
            eventParameter.setName("result");
            try {
                eventParameter.setValue(JSON.json(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
            taskParameterDaoService.createEventParameter(eventParameter);
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

    protected Step createStep(EventDto eventDto) {
        Step step = new Step();
        step.setTask(taskDaoService.findTaskById(eventDto.getTaskId()));
        step.setVin(eventDto.getVin());
        step.setTbox(eventDto.getTbox());
        step.setAid(eventDto.getAid());
        step.setMid(eventDto.getMid());
        step.setRetry(0);
        return step;
    }

    /**
     * 检查事件
     * @param eventDto
     */
    protected boolean checkEvent(EventDto eventDto) {
        boolean flag = true;
        if(!this.aid.equals(eventDto.getAid())) {
            throw new AidNotMatchException();
        }
        if(isExclusive) {
            // 如果是互斥事件，检查当前TBOX的当前CODE的事件是否有已激活的
            List<Event> list = eventDaoService.findActiveEventByTboxAndCode(eventDto.getTbox(), eventCode);
            if(list.size() > 0) {
                eventDto.setEventId(list.get(0).getId());
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 检查任务
     * @param eventDto
     * @return
     */
    protected boolean checkTask(EventDto eventDto) {
        boolean flag = true;
        if(null == eventDto.getEventId()) {
            eventDto.setEventId(taskDaoService.findTaskById(eventDto.getTaskId()).getId());
        }
        if(isContinue) {
            // 如果是可继续任务，检查当前任务是否已被执行
            Event event = eventDaoService.findEventById(eventDto.getEventId());
            if(null != event.getActiveTask() && null == eventDto.getTaskId()) {
                eventDto.setTaskId(event.getActiveTask().getId());
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 检查任务步骤
     * @param eventDto
     * @return
     */
    protected boolean checkStep(EventDto eventDto) {
        boolean flag = true;
        if(isContinue) {
            // 如果是可继续任务，检查当前任务步骤是否已被执行
            Task task = taskDaoService.findTaskById(eventDto.getTaskId());
            Step step = task.getActiveStep();
            if(null != step && step.getAid().equals(eventDto.getAid()) &&
                    step.getMid().intValue() == eventDto.getMid().intValue()) {
                step.setRetry(step.getRetry() + 1);
                stepDaoService.update(step);
                flag = false;
            }
        }
        return flag;
    }
}
