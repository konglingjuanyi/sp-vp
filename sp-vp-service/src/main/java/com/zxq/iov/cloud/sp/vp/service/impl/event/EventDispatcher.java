package com.zxq.iov.cloud.sp.vp.service.impl.event;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.event.*;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
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
 * modify date 2015-8-11 14:50
 * @version 0.9, 2015-8-11
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
     * @param event             事件对象
     * @return                  事件对象
     */
    public Event start(Event event) throws ServLayerException{
        StepDefinition stepDefinition = eventParse.findStepDefiniton(event.getCode(), event.getParamMap(), event.getId());
        EventInstance eventInstance = null;
        if(null != event.getId()) {
            eventInstance = eventConvert.findEventInstanceById(event.getId());
        }
        else {
            EventDefinition eventDefinition = stepDefinition.getTaskDefinition().getEventDefinition();
            if(eventDefinition.isExclusive()) {
                List<EventInstance> list = eventConvert.findRunningEventInstance(eventDefinition.getId(), event.getOwner());
                if(list.size()>0) {
                    eventInstance = list.get(0);
                }
            }
        }
        if(null == eventInstance) {
            eventInstance = eventCreator.createEventInstance(stepDefinition.getTaskDefinition().getEventDefinitionId(),
                    event.getOwner());
        }
        event.setId(eventInstance.getId());
        TaskInstance taskInstance = null;
        if(stepDefinition.getTaskDefinition().isExclusive()) {
            taskInstance = eventConvert.findRunningTaskInstance(eventInstance.getId(), stepDefinition.getTaskDefinitionId());
        }
        if(null == taskInstance) {
            taskInstance = eventCreator.createTaskInstance(eventInstance.getId(), stepDefinition.getTaskDefinitionId(),
                    event.getOwner());
        }
        event.setTask(taskInstance);
        StepInstance stepInstance = eventConvert.findLastStepInstance(taskInstance.getId());
        if(null != stepInstance && stepInstance.getStepDefinition().getId().intValue() == stepDefinition.getId().intValue()) {
            event.setIsRetry(true);
            EventParameter eventParameter = eventConvert.retryStepInstance(stepInstance.getId());
            if(null != eventParameter) {
                event.setResult(eventParameter.getValue());
            }
        }
        else {
            stepInstance = eventCreator.createStepInstance(taskInstance.getId(), stepDefinition.getId(), event.getOwner());
        }
        event.setStep(stepInstance);
        return event;
    }

    /**
     * 结束事件
     * @param event             事件对象
     */
    public void end(Event event) throws ServLayerException {
        StepDefinition stepDefinition = eventParse.findStepDefiniton(event.getCode(), event.getParamMap(), event.getId());
        StepInstance stepInstance = eventConvert.finishRunningStepInstance(event.getId(), stepDefinition.getId(), null);
        if(null != stepInstance) {
            if(null != event.getResult()) {
                eventConvert.saveResult(stepInstance.getId(), event.getResult());
            }
            if(stepDefinition.isLast()) {
                eventConvert.finishRunningTaskInstance(event.getId(), stepDefinition.getTaskDefinitionId(), null);
                if(stepDefinition.getTaskDefinition().isLast()) {
                    eventConvert.finishRunningEventInstance(event.getId(),
                            stepDefinition.getTaskDefinition().getEventDefinitionId(), event.getOwner());
                }
            }
        }
    }

    /**
     * 结束事件
     * @param owner             事件拥有者
     * @param stepId            步骤实例ID
     */
    public void end(String owner, Long stepId) throws ServLayerException {
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
    public void error(Long eventId, String code, Map<String, Object> paramMap, Integer errorCode)
            throws ServLayerException {
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
    public StepInstance findInstance(String owner, String code) throws ServLayerException {
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