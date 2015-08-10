package com.zxq.iov.cloud.sp.vp.api.mgmt.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler.EventDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler.EventRuleDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler.StepDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.mgmt.impl.assembler.TaskDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import com.zxq.iov.cloud.sp.vp.mgmt.api.IEventDefinitionApi;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.event.TaskDefinitionDto;
import com.zxq.iov.cloud.sp.vp.service.IEventDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 安防 事件定义服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-3 14:24
 * modify date 2015-8-6 14:12
 * @version 0.4, 2015-8-6
 */
@Service
public class EventDefinitionApiImpl implements IEventDefinitionApi {

    @Autowired
    private IEventDefinitionService eventDefinitionService;

    @Override
    public void createEventDefinition(EventDefinitionDto eventDefinitionDto) {
        EventDefinition eventDefinition = new EventDefinitionDtoAssembler().fromDto(eventDefinitionDto);
        eventDefinitionService.createEventDefinition(eventDefinition);
    }

    @Override
    public void createTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
        TaskDefinition taskDefinition = new TaskDefinitionDtoAssembler().fromDto(taskDefinitionDto);
        eventDefinitionService.createTaskDefinition(taskDefinition);
    }

    @Override
    public void createStepDefinition(StepDefinitionDto stepDefinitionDto) {
        StepDefinition stepDefinition = new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto);
        eventDefinitionService.createStepDefinition(stepDefinition);
    }

    @Override
    public void createStepDefinition(StepDefinitionDto stepDefinitionDto, List<EventRuleDto> eventRuleDtos) {
        eventDefinitionService.createStepDefinition(new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto),
                new EventRuleDtoAssembler().fromDtoList(eventRuleDtos));
    }

    @Override
    public EventDefinitionDto findEventDefinitionById(Long eventDefinitionId) throws ServLayerException {
        return new EventDefinitionDtoAssembler().toDto(
                eventDefinitionService.findEventDefinitionById(eventDefinitionId));
    }

    @Override
    public TaskDefinitionDto findTaskDefinitionById(Long taskDefinitionId) throws ServLayerException {
        return new TaskDefinitionDtoAssembler().toDto(
                eventDefinitionService.findTaskDefinitionById(taskDefinitionId));
    }

    @Override
    public StepDefinitionDto findStepDefinitionById(Long stepDefinitionId) throws ServLayerException {
        return new StepDefinitionDtoAssembler().toDto(
                eventDefinitionService.findStepDefinitionById(stepDefinitionId));
    }

    @Override
    public List<TaskDefinitionDto> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId) {
        return new TaskDefinitionDtoAssembler().toDtoList(
                eventDefinitionService.listTaskDefinitionByEventDefinitionId(eventDefinitionId));
    }

    @Override
    public List<StepDefinitionDto> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId) {
        return new StepDefinitionDtoAssembler().toDtoList(
                eventDefinitionService.listStepDefinitionByTaskDefinitionId(taskDefinitionId));
    }

    @Override
    public void pagingEventDefinition(Map<String, Object> paramMap) {
        eventDefinitionService.pagingEventDefinition(paramMap);
    }

    @Override
    public void removeEventDefinition(Long eventDefinitionId) throws ServLayerException {
        if(listTaskDefinitionByEventDefinitionId(eventDefinitionId).size() > 0) {
            throw new ServLayerException(ExceptionConstants.HAS_CHILD);
        }
        eventDefinitionService.removeEventDefinition(eventDefinitionId);
    }

    @Override
    public void removeTaskDefinition(Long taskDefinitionId) throws ServLayerException {
        if(listStepDefinitionByTaskDefinitionId(taskDefinitionId).size() > 0) {
            throw new ServLayerException(ExceptionConstants.HAS_CHILD);
        }
        eventDefinitionService.removeTaskDefinition(taskDefinitionId);
    }

    @Override
    public void removeStepDefinition(Long stepDefinitionId) {
        eventDefinitionService.removeStepDefinition(stepDefinitionId);
    }

    @Override
    public void updateEventDefinition(EventDefinitionDto eventDefinitionDto) {
        eventDefinitionService.updateEventDefinition(new EventDefinitionDtoAssembler().fromDto(eventDefinitionDto));
    }

    @Override
    public void updateTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
        eventDefinitionService.updateTaskDefinition(new TaskDefinitionDtoAssembler().fromDto(taskDefinitionDto));
    }

    @Override
    public void updateStepDefinition(StepDefinitionDto stepDefinitionDto) {
        eventDefinitionService.updateStepDefinition(new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto));
    }
}