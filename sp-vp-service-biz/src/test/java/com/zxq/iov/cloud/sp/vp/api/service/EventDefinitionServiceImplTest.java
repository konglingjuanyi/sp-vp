package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IEventDefinitionService;
import com.zxq.iov.cloud.sp.vp.api.dto.event.EventDefinitionDto;
import com.zxq.iov.cloud.sp.vp.api.dto.event.StepDefinitionDto;
import com.zxq.iov.cloud.sp.vp.api.dto.event.TaskDefinitionDto;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 事件定义服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-4 13:55
 * modify date 2015-6-23 10:02
 * @version 0.8, 2015-6-23
 */
@Transactional
public class EventDefinitionServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventDefinitionService eventDefinitionService;

    @Test
    @Rollback(false)
    public void testCreateEventDefinition() {
        EventDefinitionDto eventDefinitionDto = new EventDefinitionDto();
        eventDefinitionDto.setName("远程诊断事件");
        eventDefinitionDto.setLifecycle(3600);
        eventDefinitionDto.setIsExclusive(true);
        eventDefinitionDto.setIsContinue(false);
        eventDefinitionDto.setIsRollback(false);
        eventDefinitionService.createEventDefinition(eventDefinitionDto);
    }

    @Test
    @Rollback(false)
    public void testCreateTaskDefinition() {
        Long eventDefinitionId = 36L;
        TaskDefinitionDto taskDefinitionDto = new TaskDefinitionDto();
        taskDefinitionDto.setEventDefinitionId(eventDefinitionId);
        taskDefinitionDto.setName("响应远程诊断");
        taskDefinitionDto.setPreTaskDefinitionId(88L);
        taskDefinitionDto.setLifecycle(300);
        taskDefinitionDto.setCycleLimit(1);
        taskDefinitionDto.setIsExclusive(true);
        taskDefinitionDto.setIsContinue(false);
        taskDefinitionDto.setIsRollback(false);
        taskDefinitionDto.setIsLast(true);
        taskDefinitionDto.setSort(2);
        eventDefinitionService.createTaskDefinition(taskDefinitionDto);
    }

    @Test
    @Rollback(false)
    public void testCreateStepDefinition() {
        Long taskDefinitionId = 89L;
        StepDefinitionDto stepDefinitionDto = new StepDefinitionDto();
        stepDefinitionDto.setTaskDefinitionId(taskDefinitionId);
        stepDefinitionDto.setName("响应远程诊断");
        stepDefinitionDto.setStartCode("8012");
        stepDefinitionDto.setLifecycle(30);
        stepDefinitionDto.setRetryLimit(5);
        //stepDefinitionDto.setPreStepDefinitionId(110L);
        stepDefinitionDto.setIsRollback(false);
        stepDefinitionDto.setIsLast(true);
        stepDefinitionDto.setSort(1);
//        List<EventRuleDto> eventRuleDtos = new ArrayList<>();
//        eventRuleDtos.add(new EventRuleDto("status", "eq", "3"));
//        eventRuleDtos.add(new EventRuleDto("cancelFlag", "eq", "1"));
        eventDefinitionService.createStepDefinition(stepDefinitionDto);
    }

    @Test
    @Rollback(true)
    public void testListTaskDefinitionByEventDefinitionId() {
        Long eventDefinitionId = 14L;
        List<TaskDefinitionDto> list = eventDefinitionService.listTaskDefinitionByEventDefinitionId(eventDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testListStepDefinitionByTaskDefinitionId() {
        Long taskDefinitionId = 25L;
        List<StepDefinitionDto> list = eventDefinitionService.listStepDefinitionByTaskDefinitionId(taskDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testRemoveEventDefinition() {
        Long eventDefinitionId = 14L;
        eventDefinitionService.removeEventDefinition(eventDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testRemoveTaskDefinition() {
        Long taskDefinitionId = 25L;
        eventDefinitionService.removeTaskDefinition(taskDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testRemoveStepDefinition() {
        Long stepDefinitionId = 17L;
        eventDefinitionService.removeStepDefinition(stepDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventDefinition() {
        Long eventDefinitionId = 8L;
        EventDefinitionDto eventDefinitionDto = eventDefinitionService.findEventDefinitionById(eventDefinitionId);
        eventDefinitionDto.setType(1);
        eventDefinitionDto.setLifecycle(600);
        eventDefinitionService.updateEventDefinition(eventDefinitionDto);
    }

    @Test
    @Rollback(true)
    public void testUpdateTaskDefinition() {
        Long taskDefinitionId = 13L;
        TaskDefinitionDto taskDefinitionDto = eventDefinitionService.findTaskDefinitionById(taskDefinitionId);
        taskDefinitionDto.setLifecycle(600);
        eventDefinitionService.updateTaskDefinition(taskDefinitionDto);
    }

    @Test
    @Rollback(false)
    public void testUpdateStepDefinition() {
        Long stepDefinitionId = 9L;
        StepDefinitionDto stepDefinitionDto = eventDefinitionService.findStepDefinitionById(stepDefinitionId);
        stepDefinitionDto.setLifecycle(600);
        eventDefinitionService.updateStepDefinition(stepDefinitionDto);
    }
}
