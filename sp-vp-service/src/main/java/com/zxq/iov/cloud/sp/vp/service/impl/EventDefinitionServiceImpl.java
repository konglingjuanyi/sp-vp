package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventDefinitionDao;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventRuleDao;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepDefinitionDao;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDao;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
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
 * modify date 2015-8-6 11:35
 * @version 0.4, 2015-8-6
 */
@Service
public class EventDefinitionServiceImpl extends BaseService implements IEventDefinitionService {

    @Autowired
    private IEventDefinitionDao eventDefinitionDao;
    @Autowired
    private ITaskDefinitionDao taskDefinitionDao;
    @Autowired
    private IStepDefinitionDao stepDefinitionDao;
    @Autowired
    private IEventRuleDao eventRuleDao;

    @Override
    public void createEventDefinition(EventDefinition eventDefinition) {
        eventDefinitionDao.createEventDefinition(eventDefinition);
    }

    @Override
    public void createTaskDefinition(TaskDefinition taskDefinition) {
        taskDefinitionDao.createTaskDefinition(taskDefinition);
    }

    @Override
    public void createStepDefinition(StepDefinition stepDefinition) {
        stepDefinitionDao.createStepDefinition(stepDefinition);
    }

    @Override
    public void createStepDefinition(StepDefinition stepDefinition, List<EventRule> eventRules) {
        stepDefinitionDao.createStepDefinition(stepDefinition);
        for(EventRule eventRule : eventRules) {
            eventRule.setStepDefinitionId(stepDefinition.getId());
            eventRuleDao.createEventRule(eventRule);
        }
    }

    @Override
    public EventDefinition findEventDefinitionById(Long eventDefinitionId) throws Exception {
        AssertRequired("eventDefinitionId", eventDefinitionId);
        return eventDefinitionDao.findEventDefinitionById(eventDefinitionId);
    }

    @Override
    public TaskDefinition findTaskDefinitionById(Long taskDefinitionId) throws Exception {
        AssertRequired("taskDefinitionId", taskDefinitionId);
        return taskDefinitionDao.findTaskDefinitionById(taskDefinitionId);
    }

    @Override
    public StepDefinition findStepDefinitionById(Long stepDefinitionId) throws Exception {
        AssertRequired("stepDefinitionId", stepDefinitionId);
        return stepDefinitionDao.findStepDefinitionById(stepDefinitionId);
    }

    @Override
    public List<TaskDefinition> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId) {
        return taskDefinitionDao.listTaskDefinitionByEventDefinitionId(eventDefinitionId);
    }

    @Override
    public List<StepDefinition> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId) {
        return stepDefinitionDao.listStepDefinitionByTaskDefinitionId(taskDefinitionId);
    }

    @Override
    public void pagingEventDefinition(Map<String, Object> paramMap) {
        PageResult<EventDefinition> pageResult = new PageResult<>();
        eventDefinitionDao.pagingEventDefinition(pageResult, paramMap);
    }

    @Override
    public void removeEventDefinition(Long eventDefinitionId) throws Exception {
        if(listTaskDefinitionByEventDefinitionId(eventDefinitionId).size() > 0) {
            throw new ServLayerException(ExceptionConstants.HAS_CHILD);
        }
        eventDefinitionDao.removeEventDefinition(eventDefinitionId);
    }

    @Override
    public void removeTaskDefinition(Long taskDefinitionId) throws Exception {
        if(listStepDefinitionByTaskDefinitionId(taskDefinitionId).size() > 0) {
            throw new ServLayerException(ExceptionConstants.HAS_CHILD);
        }
        taskDefinitionDao.removeTaskDefinition(taskDefinitionId);
    }

    @Override
    public void removeStepDefinition(Long stepDefinitionId) {
        stepDefinitionDao.removeStepDefinition(stepDefinitionId);
    }

    @Override
    public void updateEventDefinition(EventDefinition eventDefinition) {
        EventDefinition _eventDefinition = eventDefinitionDao.findEventDefinitionById(eventDefinition.getId());
        _eventDefinition.setName(eventDefinition.getName());
        _eventDefinition.setType(eventDefinition.getType());
        _eventDefinition.setLifecycle(eventDefinition.getLifecycle());
        _eventDefinition.setIsExclusive(eventDefinition.isExclusive());
        _eventDefinition.setIsContinue(eventDefinition.isContinue());
        _eventDefinition.setIsRollback(eventDefinition.isRollback());
        eventDefinitionDao.updateEventDefinition(_eventDefinition);
    }

    @Override
    public void updateTaskDefinition(TaskDefinition taskDefinition) {
        TaskDefinition _taskDefinition = taskDefinitionDao.findTaskDefinitionById(taskDefinition.getId());
        _taskDefinition.setName(taskDefinition.getName());
        _taskDefinition.setLifecycle(taskDefinition.getLifecycle());
        _taskDefinition.setPreTaskDefinitionId(taskDefinition.getPreTaskDefinitionId());
        _taskDefinition.setCycleLimit(taskDefinition.getCycleLimit());
        _taskDefinition.setIsExclusive(taskDefinition.isExclusive());
        _taskDefinition.setIsContinue(taskDefinition.isContinue());
        _taskDefinition.setIsRollback(taskDefinition.isRollback());
        _taskDefinition.setIsLast(taskDefinition.isLast());
        _taskDefinition.setSort(taskDefinition.getSort());
        taskDefinitionDao.updateTaskDefinition(_taskDefinition);
    }

    @Override
    public void updateStepDefinition(StepDefinition stepDefinition) {
        StepDefinition _stepDefinition = stepDefinitionDao.findStepDefinitionById(stepDefinition.getId());
        _stepDefinition.setName(stepDefinition.getName());
        _stepDefinition.setLifecycle(stepDefinition.getLifecycle());
        _stepDefinition.setPreStepDefinitionId(stepDefinition.getPreStepDefinitionId());
        _stepDefinition.setRetryLimit(stepDefinition.getRetryLimit());
        _stepDefinition.setStartCode(stepDefinition.getStartCode());
        _stepDefinition.setIsRollback(stepDefinition.isRollback());
        _stepDefinition.setIsLast(stepDefinition.isLast());
        _stepDefinition.setSort(stepDefinition.getSort());
        stepDefinitionDao.updateStepDefinition(_stepDefinition);
    }
}