package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IEventDefinitionService;
import com.zxq.iov.cloud.sp.vp.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.api.dto.event.EventRuleDto;
import com.zxq.iov.cloud.sp.vp.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.api.dto.event.TaskDefinitionDto;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.event.EventDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.event.EventRuleDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.event.StepDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.event.TaskDefinitionDtoAssembler;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventRuleDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 安防 事件定义服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-3 14:24
 * modify date 2015-7-24 10:44
 * @version 0.3, 2015-7-24
 */
@Service
public class EventDefinitionServiceImpl extends BaseService implements IEventDefinitionService {

    @Autowired
    private IEventDefinitionDaoService eventDefinitionDaoService;
    @Autowired
    private ITaskDefinitionDaoService taskDefinitionDaoService;
    @Autowired
    private IStepDefinitionDaoService stepDefinitionDaoService;
    @Autowired
    private IEventRuleDaoService eventRuleDaoService;

    @Override
    public void createEventDefinition(EventDefinitionDto eventDefinitionDto) {
        EventDefinition eventDefinition = new EventDefinitionDtoAssembler().fromDto(eventDefinitionDto);
        eventDefinitionDaoService.createEventDefinition(eventDefinition);
    }

    @Override
    public void createTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
        TaskDefinition taskDefinition = new TaskDefinitionDtoAssembler().fromDto(taskDefinitionDto);
        taskDefinitionDaoService.createTaskDefinition(taskDefinition);
    }

    @Override
    public void createStepDefinition(StepDefinitionDto stepDefinitionDto) {
        StepDefinition stepDefinition = new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto);
        stepDefinitionDaoService.createStepDefinition(stepDefinition);
    }

    @Override
    public void createStepDefinition(StepDefinitionDto stepDefinitionDto, List<EventRuleDto> eventRuleDtos) {
        StepDefinition stepDefinition = new StepDefinitionDtoAssembler().fromDto(stepDefinitionDto);
        stepDefinitionDaoService.createStepDefinition(stepDefinition);
        EventRuleDtoAssembler eventRuleDtoAssembler = new EventRuleDtoAssembler();
        EventRule eventRule;
        for(EventRuleDto eventRuleDto : eventRuleDtos) {
            eventRule = eventRuleDtoAssembler.fromDto(eventRuleDto);
            eventRule.setStepDefinitionId(stepDefinition.getId());
            eventRuleDaoService.createEventRule(eventRule);
        }
    }

    @Override
    public EventDefinitionDto findEventDefinitionById(Long eventDefinitionId) throws Exception {
        AssertRequired("eventDefinitionId", eventDefinitionId);
        return new EventDefinitionDtoAssembler().toDto(
                eventDefinitionDaoService.findEventDefinitionById(eventDefinitionId));
    }

    @Override
    public TaskDefinitionDto findTaskDefinitionById(Long taskDefinitionId) throws Exception {
        AssertRequired("taskDefinitionId", taskDefinitionId);
        return new TaskDefinitionDtoAssembler().toDto(
                taskDefinitionDaoService.findTaskDefinitionById(taskDefinitionId));
    }

    @Override
    public StepDefinitionDto findStepDefinitionById(Long stepDefinitionId) throws Exception {
        AssertRequired("stepDefinitionId", stepDefinitionId);
        return new StepDefinitionDtoAssembler().toDto(
                stepDefinitionDaoService.findStepDefinitionById(stepDefinitionId));
    }

    @Override
    public List<TaskDefinitionDto> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId) {
        return new TaskDefinitionDtoAssembler().toDtoList(
                taskDefinitionDaoService.listTaskDefinitionByEventDefinitionId(eventDefinitionId));
    }

    @Override
    public List<StepDefinitionDto> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId) {
        return new StepDefinitionDtoAssembler().toDtoList(
                stepDefinitionDaoService.listStepDefinitionByTaskDefinitionId(taskDefinitionId));
    }

    @Override
    public void pagingEventDefinition(Map<String, Object> paramMap) {
        PageResult<EventDefinition> pageResult = new PageResult<>();
        eventDefinitionDaoService.pagingEventDefinition(pageResult, paramMap);
    }

    @Override
    public void removeEventDefinition(Long eventDefinitionId) throws Exception {
        if(listTaskDefinitionByEventDefinitionId(eventDefinitionId).size() > 0) {
            throw new ServLayerException(ExceptionConstants.HAS_CHILD);
        }
        eventDefinitionDaoService.removeEventDefinition(eventDefinitionId);
    }

    @Override
    public void removeTaskDefinition(Long taskDefinitionId) throws Exception {
        if(listStepDefinitionByTaskDefinitionId(taskDefinitionId).size() > 0) {
            throw new ServLayerException(ExceptionConstants.HAS_CHILD);
        }
        taskDefinitionDaoService.removeTaskDefinition(taskDefinitionId);
    }

    @Override
    public void removeStepDefinition(Long stepDefinitionId) {
        stepDefinitionDaoService.removeStepDefinition(stepDefinitionId);
    }

    @Override
    public void updateEventDefinition(EventDefinitionDto eventDefinitionDto) {
        EventDefinition eventDefinition = eventDefinitionDaoService.findEventDefinitionById(eventDefinitionDto.getId());
        eventDefinition.setName(eventDefinitionDto.getName());
        eventDefinition.setType(eventDefinitionDto.getType());
        eventDefinition.setLifecycle(eventDefinitionDto.getLifecycle());
        eventDefinition.setIsExclusive(eventDefinitionDto.isExclusive());
        eventDefinition.setIsContinue(eventDefinitionDto.isContinue());
        eventDefinition.setIsRollback(eventDefinitionDto.isRollback());
        eventDefinitionDaoService.updateEventDefinition(eventDefinition);
    }

    @Override
    public void updateTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
        TaskDefinition taskDefinition = taskDefinitionDaoService.findTaskDefinitionById(taskDefinitionDto.getId());
        taskDefinition.setName(taskDefinitionDto.getName());
        taskDefinition.setLifecycle(taskDefinitionDto.getLifecycle());
        taskDefinition.setPreTaskDefinitionId(taskDefinitionDto.getPreTaskDefinitionId());
        taskDefinition.setCycleLimit(taskDefinitionDto.getCycleLimit());
        taskDefinition.setIsExclusive(taskDefinitionDto.isExclusive());
        taskDefinition.setIsContinue(taskDefinitionDto.isContinue());
        taskDefinition.setIsRollback(taskDefinitionDto.isRollback());
        taskDefinition.setIsLast(taskDefinitionDto.isLast());
        taskDefinition.setSort(taskDefinitionDto.getSort());
        taskDefinitionDaoService.updateTaskDefinition(taskDefinition);
    }

    @Override
    public void updateStepDefinition(StepDefinitionDto stepDefinitionDto) {
        StepDefinition stepDefinition = stepDefinitionDaoService.findStepDefinitionById(stepDefinitionDto.getId());
        stepDefinition.setName(stepDefinitionDto.getName());
        stepDefinition.setLifecycle(stepDefinitionDto.getLifecycle());
        stepDefinition.setPreStepDefinitionId(stepDefinitionDto.getPreStepDefinitionId());
        stepDefinition.setRetryLimit(stepDefinitionDto.getRetryLimit());
        stepDefinition.setStartCode(stepDefinitionDto.getStartCode());
        stepDefinition.setIsRollback(stepDefinitionDto.isRollback());
        stepDefinition.setIsLast(stepDefinitionDto.isLast());
        stepDefinition.setSort(stepDefinitionDto.getSort());
        stepDefinitionDaoService.updateStepDefinition(stepDefinition);
    }
}
