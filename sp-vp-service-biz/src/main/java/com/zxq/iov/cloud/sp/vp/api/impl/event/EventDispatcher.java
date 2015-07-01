package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.entity.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 安防 事件调度器
 *
 * @author 叶荣杰
 * create date 2015-6-8 9:35
 * modify date 2015-6-30 9:43
 * @version 0.8, 2015-6-30
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
     * @param code              代码
     * @param paramMap          参数MAP
     * @return                  事件实例ID
     */
    public Long start(String owner, Long eventId, String code, Map<String, Object> paramMap) {
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
                List<EventInstance> list = eventConvert.findRunningEventInstance(eventDefinition.getId(), owner);
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
            eventInstanceId = eventCreator.createEventInstance(stepDefinition.getTaskDefinition().getEventDefinitionId(), owner).getId();
        }
        if(isCreateTask) {
            isCreateStep = true;
            taskInstanceId = eventCreator.createTaskInstance(eventInstanceId, stepDefinition.getTaskDefinitionId(), owner).getId();
        }
        if(isCreateStep) {
            eventCreator.createStepInstance(taskInstanceId, stepDefinition.getId(), owner);
        }
        return eventInstanceId;
    }

    /**
     * 结束事件
     * @param owner             事件拥有者
     * @param eventId           事件实例ID
     * @param code              代码
     * @param paramMap          参数MAP
     * @param result            结果对象
     */
    public void end(String owner, Long eventId, String code, Map<String, Object> paramMap, Object result) {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(code, paramMap, eventId);
        StepInstance stepInstance = eventConvert.finishRunningStepInstance(eventId, stepDefinition.getId(), null);
        if(null != result) {
            eventConvert.saveResult(stepInstance.getId(), result);
        }
        if(stepDefinition.isLast()) {
            eventConvert.finishRunningTaskInstance(eventId, stepDefinition.getTaskDefinitionId(), null);
            if(stepDefinition.getTaskDefinition().isLast()) {
                eventConvert.finishRunningEventInstance(eventId,
                        stepDefinition.getTaskDefinition().getEventDefinitionId(), owner);
            }
        }
    }

    /**
     * 结束事件
     * @param owner             事件拥有者
     * @param stepId            步骤实例ID
     */
    public void end(String owner, Long stepId) {
        StepInstance stepInstance = eventConvert.finishRunningStepInstance(stepId, null);
        if(stepInstance.getStepDefinition().isLast()) {
            eventConvert.finishRunningTaskInstance(stepInstance.getTaskInstanceId(), null);
            if(stepInstance.getStepDefinition().getTaskDefinition().isLast()) {
                eventConvert.finishRunningEventInstance(stepInstance.getTaskInstance().getEventInstanceId(),
                        stepInstance.getStepDefinition().getTaskDefinition().getEventDefinitionId(), owner);
            }
        }
    }

    /**
     * 异常事件
     * @param eventId           事件实例ID
     * @param code              代码
     * @param paramMap          参数MAP
     * @param errorCode         错误代码
     */
    public void error(Long eventId, String code, Map<String, Object> paramMap, Integer errorCode) {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(code, paramMap, eventId);
        eventConvert.finishRunningStepInstance(eventId, stepDefinition.getId(), errorCode);
        eventConvert.finishRunningTaskInstance(eventId, stepDefinition.getTaskDefinitionId(), errorCode);
    }

    /**
     * 超时事件
     * @param stepId            步骤实例ID
     */
    public void timeout(Long stepId) {
        Integer timeoutErrorCode = 2;
        StepInstance stepInstance = eventConvert.finishRunningStepInstance(stepId, timeoutErrorCode);
        eventConvert.finishRunningTaskInstance(stepInstance.getTaskInstanceId(), timeoutErrorCode);
        callback(eventParse.findStepCallbackClass(stepInstance.getStepDefinitionId()), "timeout",
                stepInstance.getStepDefinition().getStartCode());
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

    /**
     * 调用业务方法
     * @param classPath         业务类路径
     * @param methodName        方法名
     * @param code              代码
     */
    public void callback(String classPath, String methodName, String code) {
        try {
            Class cls = ClassLoader.getSystemClassLoader().loadClass(classPath);
            Method method = cls.getMethod(methodName, String.class);
            method.invoke(cls.newInstance(), code);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}