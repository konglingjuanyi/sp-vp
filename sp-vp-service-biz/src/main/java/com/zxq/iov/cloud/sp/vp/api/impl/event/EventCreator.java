package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.exception.CycleLimitException;
import com.zxq.iov.cloud.sp.vp.api.exception.PreNotFindException;
import com.zxq.iov.cloud.sp.vp.dao.event.*;
import com.zxq.iov.cloud.sp.vp.entity.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 事件分派器
 *
 * @author 叶荣杰
 * create date 2015-6-8 11:09
 * modify date 2015-6-15 14:25
 * @version 0.3, 2015-6-15
 */
@Service
public class EventCreator {

    @Autowired
    private IEventInstanceDaoService eventInstanceDaoService;
    @Autowired
    private ITaskDefinitionDaoService taskDefinitionDaoService;
    @Autowired
    private ITaskInstanceDaoService taskInstanceDaoService;
    @Autowired
    private IStepDefinitionDaoService stepDefinitionDaoService;
    @Autowired
    private IStepInstanceDaoService stepInstanceDaoService;

    private static final Integer RUNNING_STATUS = 1;

    /**
     * 创建步骤实例
     * @param taskInstanceId        任务实例ID
     * @param stepDefinitionId      步骤定义ID
     * @param owner                 拥有者
     * @param eventCreateTime       事件时间
     * @return                      步骤实例对象
     */
    public StepInstance createStepInstance(Long taskInstanceId, Long stepDefinitionId, String owner, Date eventCreateTime) {
        validateStep(taskInstanceId, stepDefinitionId);
        StepInstance stepInstance = new StepInstance();
        stepInstance.setTaskInstanceId(taskInstanceId);
        stepInstance.setStepDefinitionId(stepDefinitionId);
        stepInstance.setOwner(owner);
        stepInstance.setStartTime(eventCreateTime);
        stepInstance.setRetryCount(0);
        stepInstance.setStatus(RUNNING_STATUS);
        stepInstance = stepInstanceDaoService.createStepInstance(stepInstance);
        TaskInstance taskInstance = taskInstanceDaoService.findTaskInstanceById(stepInstance.getTaskInstanceId());
        if(stepInstance.getStartTime().before(taskInstance.getStartTime())) {
            taskInstance.setStartTime(stepInstance.getStartTime());
            taskInstanceDaoService.updateTaskInstance(taskInstance);
            EventInstance eventInstance = eventInstanceDaoService.findEventInstanceById(taskInstance.getEventInstanceId());
            if(taskInstance.getStartTime().before(eventInstance.getStartTime())) {
                eventInstance.setStartTime(taskInstance.getStartTime());
                eventInstanceDaoService.updateEventInstance(eventInstance);
            }
        }
        return stepInstance;
    }

    /**
     * 创建任务实例
     * @param eventInstanceId       事件实例ID
     * @param taskDefinitionId      任务定义ID
     * @param owner                 拥有者
     * @param eventCreateTime       事件时间
     * @return                      任务实例对象
     */
    public TaskInstance createTaskInstance(Long eventInstanceId, Long taskDefinitionId, String owner, Date eventCreateTime) {
        validateTask(eventInstanceId, taskDefinitionId);
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setEventInstanceId(eventInstanceId);
        taskInstance.setTaskDefinitionId(taskDefinitionId);
        taskInstance.setOwner(owner);
        taskInstance.setStartTime(eventCreateTime);
        taskInstance.setStatus(RUNNING_STATUS);
        taskInstance = taskInstanceDaoService.createTaskInstance(taskInstance);
        EventInstance eventInstance = eventInstanceDaoService.findEventInstanceById(taskInstance.getEventInstanceId());
        if(taskInstance.getStartTime().before(eventInstance.getStartTime())) {
            eventInstance.setStartTime(taskInstance.getStartTime());
            eventInstanceDaoService.updateEventInstance(eventInstance);
        }
        return taskInstance;
    }

    /**
     * 创建事件实例
     * @param eventDefinitionId     事件定义ID
     * @param owner                 事件拥有者
     * @param eventCreateTime       事件时间
     * @return                      事件实例对象
     */
    public EventInstance createEventInstance(Long eventDefinitionId, String owner, Date eventCreateTime) {
        EventInstance eventInstance = new EventInstance();
        eventInstance.setEventDefinitionId(eventDefinitionId);
        eventInstance.setOwner(owner);
        eventInstance.setStartTime(eventCreateTime);
        eventInstance.setStatus(RUNNING_STATUS);
        return eventInstanceDaoService.createEventInstance(eventInstance);
    }

    /**
     * 验证启动步骤
     * @param taskInstanceId        任务实例ID
     * @param stepDefinitionId      步骤定义ID
     */
    private void validateStep(Long taskInstanceId, Long stepDefinitionId) {
        StepDefinition stepDefinition = stepDefinitionDaoService.findStepDefinitionById(stepDefinitionId);
        if(null != stepDefinition.getPreStepDefinitionId()) {
            boolean preNotFind = true;
            for(StepInstance stepInstance : stepInstanceDaoService.listStepInstanceByTaskInstanceId(taskInstanceId)) {
                if(stepInstance.getStepDefinitionId().longValue() ==
                        stepDefinition.getPreStepDefinitionId().longValue()) {
                    preNotFind = false;
                }
            }
            if(preNotFind) {
                throw new PreNotFindException();
            }
        }
    }

    /**
     * 验证启动任务
     * @param eventIntanceId        事件实例ID
     * @param taskDefinitionId      任务定义ID
     */
    private void validateTask(Long eventIntanceId, Long taskDefinitionId) {
        TaskDefinition taskDefinition = taskDefinitionDaoService.findTaskDefinitionById(taskDefinitionId);
        if(null != taskDefinition.getPreTaskDefinitionId()) {
            boolean preNotFind = true;
            for(TaskInstance taskInstance : taskInstanceDaoService.listTaskInstanceByEventInstanceId(eventIntanceId, null, null)) {
                if(taskInstance.getTaskDefinitionId().longValue() == taskDefinition.getPreTaskDefinitionId().longValue()) {
                    preNotFind = false;
                }
            }
            if(preNotFind) {
                throw new PreNotFindException();
            }
        }
        if(taskDefinition.getCycleLimit() > 0) {
            if(taskInstanceDaoService.listTaskInstanceByEventInstanceId(eventIntanceId, taskDefinitionId, null).size()
                    >= taskDefinition.getCycleLimit().intValue()) {
                throw new CycleLimitException();
            }
        }

    }

}