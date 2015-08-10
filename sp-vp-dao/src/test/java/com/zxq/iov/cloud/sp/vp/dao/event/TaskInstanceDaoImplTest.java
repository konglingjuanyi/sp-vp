package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
public class TaskInstanceDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskInstanceDao taskInstanceDao;

    @Test
    @Rollback(true)
    public void testCreateTaskInstance(){
        Long taskDefinitionId = 13L;
        Long eventInstanceId = 9L;
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setTaskDefinitionId(taskDefinitionId);
        taskInstance.setEventInstanceId(eventInstanceId);
        taskInstance = taskInstanceDao.createTaskInstance(taskInstance);
        Assert.assertNotNull(taskInstance);
    }

    @Test
    @Rollback(true)
    public void testUpdateTaskInstance(){
        Long taskInstanceId = 5L;
        TaskInstance taskInstance = taskInstanceDao.findTaskInstanceById(taskInstanceId);
        taskInstance.setStartTime(new Date());
        taskInstance = taskInstanceDao.updateTaskInstance(taskInstance);
        Assert.assertNotNull(taskInstance);
    }

    @Test
    @Rollback(true)
    public void testRemoveTaskInstance() {
        Long taskInstanceId = 5L;
        taskInstanceDao.removeTaskInstance(taskInstanceId);
    }

    @Test
    @Rollback(true)
    public void testListTaskInstanceByEventInstanceId() {
        Long eventInstanceId = 9L;
        Long taskDefinitionId = 13L;
        Integer status = 1;
        List<TaskInstance> list = taskInstanceDao.listTaskInstanceByEventInstanceId(eventInstanceId, taskDefinitionId, status);
        Assert.assertTrue(list.size() > 0);
    }
}
