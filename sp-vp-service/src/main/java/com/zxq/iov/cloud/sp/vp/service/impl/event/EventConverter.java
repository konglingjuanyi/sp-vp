package com.zxq.iov.cloud.sp.vp.service.impl.event;

import com.alibaba.dubbo.common.json.JSON;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.event.*;
import com.zxq.iov.cloud.sp.vp.entity.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 安防 事件转换器
 *
 * @author 叶荣杰
 * create date 2015-6-8 10:12
 * modify date 2015-8-12 10:21
 * @version 0.8, 2015-8-12
 */
@Service
public class EventConverter {

    @Autowired
    private IEventInstanceDao eventInstanceDao;
    @Autowired
    private ITaskInstanceDao taskInstanceDao;
    @Autowired
    private IStepInstanceDao stepInstanceDao;
    @Autowired
    private IEventParameterDao eventParameterDao;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;
    private static final Integer ERROR_STATUS = 3;

    /**
     * 得到事件实例
     * @param eventInstantId        事件实例ID
     * @return                      事件实例对象
     */
    public EventInstance findEventInstanceById(Long eventInstantId) throws ServLayerException {
        EventInstance eventInstance = eventInstanceDao.findEventInstanceById(eventInstantId);
        if(null == eventInstance) {
            throw new ServLayerException(ExceptionConstants.EVENT_NOT_EXIST);
        }
        return eventInstance;
    }

    /**
     * 得到指定事件定义的未结束实例
     * @param eventDefinitionId     事件定义ID
     * @param owner                 拥有者
     * @return                      事件实例列表
     */
    public List<EventInstance> findRunningEventInstance(Long eventDefinitionId, String owner) {
        return eventInstanceDao.listEventInstanceByEventDefinitionId(eventDefinitionId, owner,
                RUNNING_STATUS);
    }

    /**
     * 得到事件实例下未结束的任务实例
     * @param eventInstanceId       事件实例ID
     * @param taskDefinitionId      任务定义ID
     * @return                      任务实例对象
     */
    public TaskInstance findRunningTaskInstance(Long eventInstanceId, Long taskDefinitionId) {
        Integer runningStatus = 1;
        List<TaskInstance> list = taskInstanceDao.listTaskInstanceByEventInstanceId(eventInstanceId, taskDefinitionId, runningStatus);
        return list.size()>0?list.get(0):null;
    }

    /**
     * 得到任务实例当前的步骤实例
     * @param taskInstanceId        任务实例ID
     * @return                      步骤实例对象
     */
    public StepInstance findLastStepInstance(Long taskInstanceId) {
        List<StepInstance> list = stepInstanceDao.listStepInstanceByTaskInstanceId(taskInstanceId);
        return list.size()>0?list.get(0):null;
    }

    /**
     * 得到拥有者指定的未结束步骤实例
     * @param owner                 拥有者
     * @param stepDefinitionId      步骤定义ID
     * @return                      步骤实例
     */
    public StepInstance findOwnerRunningStepInstance(String owner, Long stepDefinitionId) {
        List<StepInstance> list = stepInstanceDao.listStepInstanceByOwner(owner, stepDefinitionId, RUNNING_STATUS);
        return list.size()>0?list.get(0):null;
    }

    /**
     * 重试步骤实例
     * @param stepInstanceId        步骤实例ID
     * @return                      事件参数对象
     */
    public EventParameter retryStepInstance(Long stepInstanceId) throws ServLayerException {
        StepInstance stepInstance = stepInstanceDao.findStepInstanceById(stepInstanceId);
        stepInstance.setRetryCount(stepInstance.getRetryCount() + 1);
        if(stepInstance.getRetryCount().intValue() > stepInstance.getStepDefinition().getRetryLimit().intValue()) {
            throw new ServLayerException(ExceptionConstants.MORE_THAN_RETRY_COUNT);
        }
        stepInstanceDao.updateStepInstance(stepInstance);
        return eventParameterDao.findEventParameterByTypeAndStepIdAndName(2, stepInstanceId, "result");
    }

    /**
     * 完成运行的步骤实例
     * @param stepInstanceId        步骤实例ID
     * @param errorCode             错误代码
     * @return                      步骤实例对象
     */
    public StepInstance finishRunningStepInstance(Long stepInstanceId, Integer errorCode) {
        StepInstance stepInstance = stepInstanceDao.findStepInstanceById(stepInstanceId);
        stepInstance.setEndTime(new Date());
        if(null != errorCode) {
            stepInstance.setStatus(ERROR_STATUS);
            stepInstance.setErrorCode(errorCode);
        }
        else {
            stepInstance.setStatus(END_STATUS);
        }
        return stepInstanceDao.updateStepInstance(stepInstance);
    }

    /**
     * 完成运行的步骤实例
     * @param eventInstanceId       事件实例ID
     * @param stepDefinitionId      步骤定义ID
     * @param errorCode             错误代码
     * @return                      步骤实例对象
     */
    public StepInstance finishRunningStepInstance(Long eventInstanceId, Long stepDefinitionId, Integer errorCode) {
        List<StepInstance> list = stepInstanceDao.listStepInstanceByEventInstanceId(eventInstanceId,
                stepDefinitionId, RUNNING_STATUS);
        return list.size()>0?finishRunningStepInstance(list.get(0).getId(), errorCode):null;
    }

    /**
     * 完成运行的任务实例
     * @param taskInstanceId        任务实例ID
     * @param errorCode             错误代码
     * @return                      任务实例对象
     */
    public TaskInstance finishRunningTaskInstance(Long taskInstanceId, Integer errorCode) {
        TaskInstance taskInstance = taskInstanceDao.findTaskInstanceById(taskInstanceId);
        taskInstance.setEndTime(new Date());
        if(null != errorCode) {
            taskInstance.setStatus(ERROR_STATUS);
            taskInstance.setErrorCode(errorCode);
        }
        else {
            taskInstance.setStatus(END_STATUS);
        }
        return taskInstanceDao.updateTaskInstance(taskInstance);
    }

    /**
     * 完成运行的任务实例
     * @param eventInstanceId       事件实例ID
     * @param taskDefinitionId      任务定义ID
     * @param errorCode             错误代码
     * @return                      任务实例对象
     */
    public TaskInstance finishRunningTaskInstance(Long eventInstanceId, Long taskDefinitionId, Integer errorCode) {
        List<TaskInstance> list = taskInstanceDao.listTaskInstanceByEventInstanceId(eventInstanceId,
                taskDefinitionId, RUNNING_STATUS);
        return list.size()>0?finishRunningTaskInstance(list.get(0).getId(), errorCode):null;
    }

    /**
     * 完成运行的事件实例
     * @param eventInstanceId       事件实例ID
     * @param eventDefinitionId     事件定义ID
     * @param owner                 拥有者
     * @return                      事件实例对象
     */
    public EventInstance finishRunningEventInstance(Long eventInstanceId, Long eventDefinitionId, String owner) {
        EventInstance eventInstance = null;
        if(null != eventInstanceId) {
            eventInstance = eventInstanceDao.findEventInstanceById(eventInstanceId);
        }
        if(null == eventInstance) {
            List<EventInstance> list = eventInstanceDao.listEventInstanceByEventDefinitionId(eventDefinitionId,
                    owner, RUNNING_STATUS);
            if(list.size() > 0) {
                eventInstance = list.get(0);
            }
        }
        eventInstance.setEndTime(new Date());
        eventInstance.setStatus(END_STATUS);
        return eventInstanceDao.updateEventInstance(eventInstance);
    }

    /**
     * 保存结果
     * @param stepInstanceId        步骤实例ID
     * @param result                结果对象
     */
    public void saveResult(Long stepInstanceId, Object result) {
        Integer outputType = 2;
        EventParameter eventParameter = new EventParameter();
        eventParameter.setStepInstanceId(stepInstanceId);
        eventParameter.setType(outputType);
        eventParameter.setName("result");
        try {
            eventParameter.setValue(JSON.json(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventParameterDao.createEventParameter(eventParameter);
    }
}