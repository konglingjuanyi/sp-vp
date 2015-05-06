package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskParameterDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskStepDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskParameter;
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
public class TaskParameterDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private ITaskParameterDaoService taskParameterDaoService;

    @Test
    @Rollback(true)
    public void testCreateTaskParameter(){
        Long taskId = 8L;
        TaskParameter taskParameter = new TaskParameter();
        taskParameter.setTask(taskDaoService.findTaskById(taskId));
        taskParameter.setName("a");
        taskParameter.setValue("1");
        taskParameter = taskParameterDaoService.createTaskParameter(taskParameter);
        Assert.assertNotNull(taskParameter);
    }

    @Test
    @Rollback(false)
    public void testUpdateTaskParameter(){
        Long taskParameterId = 1L;
        TaskParameter taskParameter = taskParameterDaoService.findTaskParameterById(taskParameterId);
        taskParameter.setValue("2");
        taskParameter = taskParameterDaoService.updateTaskParameter(taskParameter);
        Assert.assertNotNull(taskParameter);
    }

}
