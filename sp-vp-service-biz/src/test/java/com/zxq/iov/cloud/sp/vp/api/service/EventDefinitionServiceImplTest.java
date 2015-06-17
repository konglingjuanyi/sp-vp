package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
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
 * modify date 2015-6-16 10:03
 * @version 0.5, 2015-6-16
 */
@Transactional
public class EventDefinitionServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventDefinitionService eventDefinitionService;

    @Test
    @Rollback(false)
    public void testCreateEventDefinition() {
        EventDefinitionDto eventDefinitionDto = new EventDefinitionDto();
        eventDefinitionDto.setName("车辆状态警报事件");
        eventDefinitionDto.setLifecycle(600);
        eventDefinitionDto.setIsExclusive(false);
        eventDefinitionDto.setIsContinue(false);
        eventDefinitionDto.setIsRollback(false);
        eventDefinitionService.createEventDefinition(eventDefinitionDto);
    }

    @Test
    @Rollback(false)
    public void testCreateTaskDefinition() {
        Long eventDefinitionId = 21L;
        TaskDefinitionDto taskDefinitionDto = new TaskDefinitionDto();
        taskDefinitionDto.setEventDefinitionId(eventDefinitionId);
        taskDefinitionDto.setName("车辆状态警报");
        //taskDefinitionDto.setPreTaskDefinitionId(47L);
        taskDefinitionDto.setLifecycle(600);
        taskDefinitionDto.setCycleLimit(1);
        taskDefinitionDto.setIsExclusive(true);
        taskDefinitionDto.setIsContinue(false);
        taskDefinitionDto.setIsRollback(false);
        taskDefinitionDto.setIsLast(true);
        taskDefinitionDto.setSort(1);
        eventDefinitionService.createTaskDefinition(taskDefinitionDto);
    }

    @Test
    @Rollback(false)
    public void testCreateStepDefinition() {
        Long taskDefinitionId = 55L;
        StepDefinitionDto stepDefinitionDto = new StepDefinitionDto();
        stepDefinitionDto.setTaskDefinitionId(taskDefinitionId);
        stepDefinitionDto.setName("车辆状态警报");
        stepDefinitionDto.setStartCode("1133");
        stepDefinitionDto.setLifecycle(60);
        stepDefinitionDto.setRetryLimit(5);
        //stepDefinitionDto.setPreStepDefinitionId(59L);
        stepDefinitionDto.setIsRollback(false);
        stepDefinitionDto.setIsLast(true);
        stepDefinitionDto.setSort(1);
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
