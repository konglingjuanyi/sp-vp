package com.zxq.iov.cloud.sp.vp.service;


import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;

import java.util.List;
import java.util.Map;

/**
 * 安防 事件定义接口
 *
 * @author 叶荣杰
 * create date 2015-6-2 16:50
 * modify date 2015-8-6 11:30
 * @version 0.4, 2015-8-6
 */
public interface IEventDefinitionService {

    /**
     * 创建事件定义
     * @param eventDefinition       事件定义对象
     */
    void createEventDefinition(EventDefinition eventDefinition);

    /**
     * 创建任务定义
     * @param taskDefinition        任务定义对象
     */
    void createTaskDefinition(TaskDefinition taskDefinition);

    /**
     * 创建步骤定义
     * @param stepDefinition        步骤定义对象
     */
    void createStepDefinition(StepDefinition stepDefinition);

    /**
     * 创建步骤定义
     * @param stepDefinition        步骤定义对象
     * @param eventRules            事件规则对象列表
     */
    void createStepDefinition(StepDefinition stepDefinition, List<EventRule> eventRules);

    /**
     * 根据主键得到事件定义对象
     * @param eventDefinitionId     事件定义主键
     * @return                      事件定义对象
     */
    EventDefinition findEventDefinitionById(Long eventDefinitionId) throws ServLayerException;

    /**
     * 根据主键得到任务定义对象
     * @param taskDefinitionId      任务定义主键
     * @return                      任务定义对象
     */
    TaskDefinition findTaskDefinitionById(Long taskDefinitionId) throws ServLayerException;

    /**
     * 根据主键得到步骤定义对象
     * @param stepDefinitionId      步骤定义主键
     * @return                      步骤定义对象
     */
    StepDefinition findStepDefinitionById(Long stepDefinitionId) throws ServLayerException;

    /**
     * 列出事件定义下的所有任务定义
     * @param eventDefinitionId     事件定义ID
     * @return                      任务定义对象列表
     */
    List<TaskDefinition> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId);

    /**
     * 列出任务定义下的所有步骤定义
     * @param taskDefinitionId      任务定义ID
     * @return                      步骤定义对象列表
     */
    List<StepDefinition> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId);

    /**
     * 查询事件定义
     * @param paramMap              参数MAP
     */
    void pagingEventDefinition(Map<String, Object> paramMap);

    /**
     * 删除事件定义
     * @param eventDefinitionId     事件定义主键
     */
    void removeEventDefinition(Long eventDefinitionId) throws ServLayerException;

    /**
     * 删除任务定义
     * @param taskDefinitionId      任务定义主键
     */
    void removeTaskDefinition(Long taskDefinitionId) throws ServLayerException;

    /**
     * 删除步骤定义
     * @param stepDefinitionId      步骤定义主键
     */
    void removeStepDefinition(Long stepDefinitionId);

    /**
     * 修改事件定义
     * @param eventDefinition       事件定义对象
     */
    void updateEventDefinition(EventDefinition eventDefinition);

    /**
     * 修改任务定义
     * @param taskDefinition        任务定义对象
     */
    void updateTaskDefinition(TaskDefinition taskDefinition);

    /**
     * 修改步骤定义
     * @param stepDefinition        步骤定义对象
     */
    void updateStepDefinition(StepDefinition stepDefinition);

}
