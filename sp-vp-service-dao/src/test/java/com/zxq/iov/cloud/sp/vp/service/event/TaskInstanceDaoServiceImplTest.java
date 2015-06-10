package com.zxq.iov.cloud.sp.vp.service.event;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 安防 任务实例持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-5 13:07
 * modify date 2015-6-8 10:38
 * @version 0.2, 2015-6-8
 */
@Transactional
public class TaskInstanceDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskInstanceDaoService taskInstanceDaoService;

    @Test
    @Rollback(true)
    public void testCreateTaskInstance(){
        Long taskDefinitionId = 13L;
        Long eventInstanceId = 9L;
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setTaskDefinitionId(taskDefinitionId);
        taskInstance.setEventInstanceId(eventInstanceId);
        taskInstance = taskInstanceDaoService.createTaskInstance(taskInstance);
        Assert.assertNotNull(taskInstance);
    }

    @Test
    @Rollback(true)
    public void testUpdateTaskInstance(){
        Long taskInstanceId = 5L;
        TaskInstance taskInstance = taskInstanceDaoService.findTaskInstanceById(taskInstanceId);
        taskInstance.setStartTime(new Date());
        taskInstance = taskInstanceDaoService.updateTaskInstance(taskInstance);
        Assert.assertNotNull(taskInstance);
    }

    @Test
    @Rollback(true)
    public void testRemoveTaskInstance() {
        Long taskInstanceId = 5L;
        taskInstanceDaoService.removeTaskInstance(taskInstanceId);
    }

    @Test
    @Rollback(true)
    public void testListTaskInstanceByEventInstanceId() {
        Long eventInstanceId = 9L;
        Long taskDefinitionId = 13L;
        Integer status = 1;
        List<TaskInstance> list = taskInstanceDaoService.listTaskInstanceByEventInstanceId(eventInstanceId, taskDefinitionId, status);
        Assert.assertTrue(list.size() > 0);
    }
}
