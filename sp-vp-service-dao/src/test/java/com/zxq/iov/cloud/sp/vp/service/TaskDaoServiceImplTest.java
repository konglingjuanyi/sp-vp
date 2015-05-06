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
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-11-27
 * Time: 上午8:56
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
        Long eventId = 15L;
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
        Long taskId = 8L;
        Task task = taskDaoService.findTaskById(taskId);
        task.setCode("2");
        task = taskDaoService.updateTask(task);
        Assert.assertNotNull(task);
    }

}
