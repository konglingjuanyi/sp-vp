package com.zxq.iov.cloud.sp.vp.mgmt.api;


import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.TaskDefinitionDto;

import java.util.List;
import java.util.Map;

/**
 * 安防 事件定义接口
 *
 * @author 叶荣杰
 * create date 2015-6-2 16:50
 * modify date 2015-7-24 10:43
 * @version 0.3, 2015-7-24
 */
public interface IEventDefinitionApi {

    /**
     * 创建事件定义
     * @param eventDefinitionDto    事件定义DTO
     */
    void createEventDefinition(EventDefinitionDto eventDefinitionDto);

    /**
     * 创建任务定义
     * @param taskDefinitionDto     任务定义DTO
     */
    void createTaskDefinition(TaskDefinitionDto taskDefinitionDto);

    /**
     * 创建步骤定义
     * @param stepDefinitionDto     步骤定义DTO
     */
    void createStepDefinition(StepDefinitionDto stepDefinitionDto);

    /**
     * 创建步骤定义
     * @param stepDefinitionDto     步骤定义DTO
     * @param eventRuleDtos         事件规则DTO列表
     */
    void createStepDefinition(StepDefinitionDto stepDefinitionDto, List<EventRuleDto> eventRuleDtos);

    /**
     * 根据主键得到事件定义DTO
     * @param eventDefinitionId     事件定义主键
     * @return                      事件定义DTO
     */
    EventDefinitionDto findEventDefinitionById(Long eventDefinitionId) throws ApiException;

    /**
     * 根据主键得到任务定义DTO
     * @param taskDefinitionId      任务定义主键
     * @return                      任务定义DTO
     */
    TaskDefinitionDto findTaskDefinitionById(Long taskDefinitionId) throws ApiException;

    /**
     * 根据主键得到步骤定义DTO
     * @param stepDefinitionId      步骤定义主键
     * @return                      步骤定义DTO
     */
    StepDefinitionDto findStepDefinitionById(Long stepDefinitionId) throws ApiException;

    /**
     * 列出事件定义下的所有任务定义
     * @param eventDefinitionId
     * @return
     */
    List<TaskDefinitionDto> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId);

    /**
     * 列出任务定义下的所有步骤定义
     * @param taskDefinitionId
     * @return
     */
    List<StepDefinitionDto> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId);

    /**
     * 查询事件定义
     * @param paramMap
     */
    void pagingEventDefinition(Map<String, Object> paramMap);

    /**
     * 删除事件定义
     * @param eventDefinitionId     事件定义主键
     */
    void removeEventDefinition(Long eventDefinitionId) throws ApiException;

    /**
     * 删除任务定义
     * @param taskDefinitionId      任务定义主键
     */
    void removeTaskDefinition(Long taskDefinitionId) throws ApiException;

    /**
     * 删除步骤定义
     * @param stepDefinitionId      步骤定义主键
     */
    void removeStepDefinition(Long stepDefinitionId);

    /**
     * 修改事件定义
     * @param eventDefinitionDto    事件定义DTO
     */
    void updateEventDefinition(EventDefinitionDto eventDefinitionDto);

    /**
     * 修改任务定义
     * @param taskDefinitionDto     任务定义DTO
     */
    void updateTaskDefinition(TaskDefinitionDto taskDefinitionDto);

    /**
     * 修改步骤定义
     * @param stepDefinitionDto     步骤定义DTO
     */
    void updateStepDefinition(StepDefinitionDto stepDefinitionDto);

}
