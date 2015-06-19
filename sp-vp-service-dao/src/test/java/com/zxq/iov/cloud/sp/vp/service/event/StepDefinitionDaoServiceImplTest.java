package com.zxq.iov.cloud.sp.vp.service.event;

import com.zxq.iov.cloud.core.dal.repo.mybatis.PageResult;
import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


/**
 * 安防 步骤定义持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-3 17:47
 * modify date 2015-6-18 12:45
 * @version 0.3, 2015-6-18
 */
@Transactional
public class StepDefinitionDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDefinitionDaoService taskDefinitionDaoService;
    @Autowired
    private IStepDefinitionDaoService stepDefinitionDaoService;

    @Test
    @Rollback(true)
    public void testCreateStepDefinition(){
        Long taskDefinitionId = 13L;
        StepDefinition stepDefinition = new StepDefinition();
        stepDefinition.setTaskDefinition(taskDefinitionDaoService.findTaskDefinitionById(taskDefinitionId));
        stepDefinition.setName("下发锁定车辆命令");
        stepDefinition.setStartCode("1149");
        stepDefinition.setIsRollback(false);
        stepDefinition.setIsLast(true);
        stepDefinition.setSort(0);
        stepDefinition = stepDefinitionDaoService.createStepDefinition(stepDefinition);
        Assert.assertNotNull(stepDefinition);
    }

    @Test
    @Rollback(true)
    public void testUpdateStepDefinition(){
        Long stepDefinitionId = 8L;
        StepDefinition stepDefinition = stepDefinitionDaoService.findStepDefinitionById(stepDefinitionId);
        stepDefinition.setName("下发锁定车辆命令2");
        stepDefinition = stepDefinitionDaoService.updateStepDefinition(stepDefinition);
        Assert.assertNotNull(stepDefinition);
    }

    @Test
    @Rollback(true)
    public void testRemoveStepDefinition() {
        Long stepDefinitionId = 8L;
        stepDefinitionDaoService.removeStepDefinition(stepDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testListStepDefinitionByCodeAndEventDefinitionId() {
        String startCode = "1113";
        Long eventDefinitionId = 23L;
        List<StepDefinition> list = stepDefinitionDaoService.listStepDefinitionByStartCodeAndEventDefinitionId(startCode,
                eventDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testListStepDefinitionByTaskDefinitionId() {
        Long taskDefinitionId = 13L;
        List<StepDefinition> list = stepDefinitionDaoService.listStepDefinitionByTaskDefinitionId(taskDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testPagingStepDefinition() {
        PageResult<StepDefinition> pageResult = new PageResult<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        pageResult = stepDefinitionDaoService.pagingStepDefinition(pageResult, paramMap);
        Assert.assertTrue(pageResult.getTotalCount()>0);
    }
}
