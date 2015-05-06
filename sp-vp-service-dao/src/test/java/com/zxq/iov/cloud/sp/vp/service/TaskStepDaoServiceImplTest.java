package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskStepDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.ITaskStepRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-11-27
 * Time: 上午8:56
 */
@Transactional
public class TaskStepDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private ITaskStepDaoService taskStepDaoService;

    @Test
    @Rollback(true)
    public void testCreateTaskStep(){
        Long taskId = 8L;
        TaskStep taskStep = new TaskStep();
        taskStep.setTask(taskDaoService.findTaskById(taskId));
        taskStep.setTbox("001");
        taskStep.setAid("001");
        taskStep.setMid(1);
        taskStep.setStartTime(new Date());
        taskStep = taskStepDaoService.createTaskStep(taskStep);
        Assert.assertNotNull(taskStep);
    }

    @Test
    @Rollback(true)
    public void testUpdateTaskStep(){
        Long taskStepId = 9L;
        TaskStep taskStep = taskStepDaoService.findTaskStepById(taskStepId);
        System.out.println("=====" + taskStep.getTask().getCode());
        taskStep.setMid(2);
        taskStep = taskStepDaoService.updateTaskStep(taskStep);
        Assert.assertNotNull(taskStep);
    }

}
