package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.entity.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 安防 事件调度器
 *
 * @author 叶荣杰
 * create date 2015-6-8 9:35
 * modify date 2015-6-17 17:20
 * @version 0.6, 2015-6-17
 */
@Service
public class EventDispatcher {

    @Autowired
    private EventParser eventParse;
    @Autowired
    private EventConverter eventConvert;
    @Autowired
    private EventCreator eventCreator;

    /**
     * 启动事件
     * @param owner             事件拥有者
     * @param eventId           事件ID
     * @param eventCreateTime   事件创建时间
     * @param code              代码
     * @param paramMap          参数MAP
     * @return                  事件实例ID
     */
    public Long start(String owner, Long eventId, Date eventCreateTime, String code, Map<String, Object> paramMap) {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(code, paramMap, eventId);
        Long eventInstanceId = null;
        Long taskInstanceId = null;
        boolean isCreateEvent = false;
        boolean isCreateTask = false;
        boolean isCreateStep = false;
        if(null != eventId) {
            EventInstance eventInstance = eventConvert.findEventInstanceById(eventId);
            if(null != eventInstance) {
                eventInstanceId = eventInstance.getId();
                if(stepDefinition.getTaskDefinition().isExclusive()) {
                    TaskInstance taskInstance = eventConvert.findRunningTaskInstance(eventInstance.getId(),
                            stepDefinition.getTaskDefinition().getId());
                    if(null != taskInstance) {
                        //这里有一步判断是否重试，如果重试的话isCreateStep=false并且获得对应result
                        taskInstanceId = taskInstance.getId();
                        isCreateStep = true;
                    }
                    else {
                        isCreateTask = true;
                    }
                }
                else {
                    isCreateTask = true;
                }
            }
            else {
                isCreateEvent = true;
            }
        }
        else {
            EventDefinition eventDefinition = stepDefinition.getTaskDefinition().getEventDefinition();
            if(eventDefinition.isExclusive()) {
                List<EventInstance> list = eventConvert.findRunningEventInstance(eventDefinition.getId());
                if(list.size()>0) {
                    eventInstanceId = list.get(0).getId();
                    TaskInstance taskInstance = eventConvert.findRunningTaskInstance(eventInstanceId, stepDefinition.getTaskDefinitionId());
                    if(null != taskInstance) {
                        //这里有一步判断是否重试，如果重试的话isCreateStep=false并且获得对应result
                        taskInstanceId = taskInstance.getId();
                        isCreateStep = true;
                    }
                    else {
                        isCreateTask = true;
                    }
                }
                else {
                    isCreateEvent = true;
                }
            }
            else {
                isCreateEvent = true;
            }
        }
        if(isCreateEvent) {
            isCreateTask = true;
            eventInstanceId = eventCreator.createEventInstance(stepDefinition.getTaskDefinition().getEventDefinitionId(), owner, eventCreateTime).getId();
        }
        if(isCreateTask) {
            isCreateStep = true;
            taskInstanceId = eventCreator.createTaskInstance(eventInstanceId, stepDefinition.getTaskDefinitionId(), owner, eventCreateTime).getId();
        }
        if(isCreateStep) {
            eventCreator.createStepInstance(taskInstanceId, stepDefinition.getId(), owner, eventCreateTime);
        }
        return eventInstanceId;
    }

    /**
     * 结束事件
     * @param eventId           事件实例ID
     * @param eventCreateTime   事件发生时间
     * @param code              代码
     * @param paramMap          参数MAP
     * @param result            结果对象
     */
    public void end(Long eventId, Date eventCreateTime, String code, Map<String, Object> paramMap, Object result) {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(code, paramMap, eventId);
        StepInstance stepInstance = eventConvert.finishRunningStepInstance(eventId, stepDefinition.getId(), eventCreateTime, null);
        if(null != result) {
            eventConvert.saveResult(stepInstance.getId(), result);
        }
        if(stepDefinition.isLast()) {
            eventConvert.finishRunningTaskInstance(eventId, stepDefinition.getTaskDefinitionId(), eventCreateTime, null);
            if(stepDefinition.getTaskDefinition().isLast()) {
                eventConvert.finishRunningEventInstance(eventId,
                        stepDefinition.getTaskDefinition().getEventDefinitionId(), eventCreateTime);
            }
        }
    }

    /**
     * 异常事件
     * @param eventId           事件实例ID
     * @param eventCreateTime   事件发生时间
     * @param code              代码
     * @param paramMap          参数MAP
     * @param errorCode         错误代码
     */
    public void error(Long eventId, Date eventCreateTime, String code, Map<String, Object> paramMap, Integer errorCode) {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(code, paramMap, eventId);
        eventConvert.finishRunningStepInstance(eventId, stepDefinition.getId(), eventCreateTime, errorCode);
        eventConvert.finishRunningTaskInstance(eventId, stepDefinition.getTaskDefinitionId(), eventCreateTime, errorCode);
    }

    /**
     * 得到拥有者当前实例
     * @param owner             拥有者
     * @param code              代码
     * @return                  步骤实例
     */
    public StepInstance findInstance(String owner, String code) {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(code, null, null);
        return eventConvert.findOwnerRunningStepInstance(owner, stepDefinition.getId());
    }

}