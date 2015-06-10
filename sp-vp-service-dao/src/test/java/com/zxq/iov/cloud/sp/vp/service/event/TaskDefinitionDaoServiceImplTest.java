package com.zxq.iov.cloud.sp.vp.service.event;

import com.zxq.iov.cloud.core.dal.repo.mybatis.PageResult;
import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


/**
 * 安防 任务定义持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-3 16:49
 * modify date 2015-6-4 13:17
 * @version 0.2, 2015-6-4
 */
@Transactional
public class TaskDefinitionDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDefinitionDaoService taskDefinitionDaoService;
    @Autowired
    private IEventDefinitionDaoService eventDefinitionDaoService;

    @Test
    @Rollback(true)
    public void testCreateTaskDefinition(){
        Long eventDefinitionId = 8L;
        TaskDefinition taskDefinition = new TaskDefinition();
        taskDefinition.setEventDefinition(eventDefinitionDaoService.findEventDefinitionById(eventDefinitionId));
        taskDefinition.setName("TBOX发起被盗警告");
        taskDefinition.setCycleLimit(1);
        taskDefinition.setIsExclusive(true);
        taskDefinition.setIsContinue(true);
        taskDefinition.setIsRollback(false);
        taskDefinition.setIsLast(false);
        taskDefinition.setSort(0);
        taskDefinition = taskDefinitionDaoService.createTaskDefinition(taskDefinition);
        Assert.assertNotNull(taskDefinition);
    }

    @Test
    @Rollback(true)
    public void testUpdateTaskDefinition(){
        Long taskDefinitionId = 12L;
        TaskDefinition taskDefinition = taskDefinitionDaoService.findTaskDefinitionById(taskDefinitionId);
        taskDefinition.setName("TBOX发起被盗警告2");
        taskDefinition = taskDefinitionDaoService.updateTaskDefinition(taskDefinition);
        Assert.assertNotNull(taskDefinition);
    }

    @Test
    @Rollback(true)
    public void testRemoveTaskDefinition() {
        Long taskDefinitionId = 12L;
        taskDefinitionDaoService.removeTaskDefinition(taskDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testListTaskDefinitionByEventDefinitionId() {
        Long eventDefinitionId = 8L;
        List<TaskDefinition> list = taskDefinitionDaoService.listTaskDefinitionByEventDefinitionId(eventDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testPagingTaskDefinition() {
        PageResult<TaskDefinition> pageResult = new PageResult<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        pageResult = taskDefinitionDaoService.pagingTaskDefinition(pageResult, paramMap);
        Assert.assertTrue(pageResult.getTotalCount()>0);
    }
}
