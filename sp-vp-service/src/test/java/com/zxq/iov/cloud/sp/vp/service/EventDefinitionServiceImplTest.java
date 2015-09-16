/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-04       荣杰         1.0            Initial Version
 * 2015-08-06       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.EventDefinitionServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防服务 事件定义服务测试类
 */
@Transactional
public class EventDefinitionServiceImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDefinitionServiceImplTest.class);

    @Autowired
    private IEventDefinitionService eventDefinitionService;

    @Test
    @Rollback(false)
    public void testCreateEventDefinition() {
        EventDefinition eventDefinition = new EventDefinition();
        eventDefinition.setName("远程诊断事件");
        eventDefinition.setLifecycle(3600);
        eventDefinition.setIsExclusive(true);
        eventDefinition.setIsContinue(false);
        eventDefinition.setIsRollback(false);
        eventDefinitionService.createEventDefinition(eventDefinition);
    }

    @Test
    @Rollback(false)
    public void testCreateTaskDefinition() {
        Long eventDefinitionId = 36L;
        TaskDefinition taskDefinition = new TaskDefinition();
        taskDefinition.setEventDefinitionId(eventDefinitionId);
        taskDefinition.setName("响应远程诊断");
        taskDefinition.setPreTaskDefinitionId(88L);
        taskDefinition.setLifecycle(300);
        taskDefinition.setCycleLimit(1);
        taskDefinition.setIsExclusive(true);
        taskDefinition.setIsContinue(false);
        taskDefinition.setIsRollback(false);
        taskDefinition.setIsLast(true);
        taskDefinition.setSort(2);
        eventDefinitionService.createTaskDefinition(taskDefinition);
    }

    @Test
    @Rollback(false)
    public void testCreateStepDefinition() {
        Long taskDefinitionId = 89L;
        StepDefinition stepDefinition = new StepDefinition();
        stepDefinition.setTaskDefinitionId(taskDefinitionId);
        stepDefinition.setName("响应远程诊断");
        stepDefinition.setStartCode("8012");
        stepDefinition.setLifecycle(30);
        stepDefinition.setRetryLimit(5);
        //stepDefinition.setPreStepDefinitionId(110L);
        stepDefinition.setIsRollback(false);
        stepDefinition.setIsLast(true);
        stepDefinition.setSort(1);
//        List<EventRule> eventRules = new ArrayList<>();
//        eventRules.add(new EventRule("status", "eq", "3"));
//        eventRules.add(new EventRule("cancelFlag", "eq", "1"));
        eventDefinitionService.createStepDefinition(stepDefinition);
    }

    @Test
    @Rollback(true)
    public void testListTaskDefinitionByEventDefinitionId() {
        Long eventDefinitionId = 14L;
        List<TaskDefinition> list = eventDefinitionService.listTaskDefinitionByEventDefinitionId(eventDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testListStepDefinitionByTaskDefinitionId() {
        Long taskDefinitionId = 25L;
        List<StepDefinition> list = eventDefinitionService.listStepDefinitionByTaskDefinitionId(taskDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testRemoveEventDefinition() throws Exception {
        Long eventDefinitionId = 14L;
        eventDefinitionService.removeEventDefinition(eventDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testRemoveTaskDefinition() throws Exception {
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
    public void testUpdateEventDefinition() throws Exception {
        Long eventDefinitionId = 8L;
        EventDefinition eventDefinition = eventDefinitionService.findEventDefinitionById(eventDefinitionId);
        eventDefinition.setType(1);
        eventDefinition.setLifecycle(600);
        eventDefinitionService.updateEventDefinition(eventDefinition);
    }

    @Test
    @Rollback(true)
    public void testUpdateTaskDefinition() throws Exception {
        Long taskDefinitionId = 13L;
        TaskDefinition taskDefinition = eventDefinitionService.findTaskDefinitionById(taskDefinitionId);
        taskDefinition.setLifecycle(600);
        eventDefinitionService.updateTaskDefinition(taskDefinition);
    }

    @Test
    @Rollback(false)
    public void testUpdateStepDefinition() throws Exception {
        Long stepDefinitionId = 9L;
        StepDefinition stepDefinition = eventDefinitionService.findStepDefinitionById(stepDefinitionId);
        stepDefinition.setLifecycle(600);
        eventDefinitionService.updateStepDefinition(stepDefinition);
    }
}