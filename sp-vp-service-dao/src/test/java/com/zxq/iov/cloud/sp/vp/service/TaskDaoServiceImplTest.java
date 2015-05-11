package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 任务持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-4-29 8:56
 * @version 0.1, 2015-4-29
 */
@Transactional
public class TaskDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private IEventDaoService eventDaoService;

    @Test
    @Rollback(true)
    public void testCreateTask(){
        Long eventId = 22L;
        Task task = new Task();
        task.setEvent(eventDaoService.findEventById(eventId));
        task.setVin("002");
        task.setTbox("001");
        task.setCode("1");
        task.setSort(1L);
        task.setStartTime(new Date());
        task = taskDaoService.createTask(task);
        Assert.assertNotNull(task);
    }

    @Test
    @Rollback(true)
    public void testUpdateTask(){
        Long taskId = 14L;
        Task task = taskDaoService.findTaskById(taskId);
        task.setCode("2");
        task = taskDaoService.updateTask(task);
        Assert.assertNotNull(task);
    }

}
