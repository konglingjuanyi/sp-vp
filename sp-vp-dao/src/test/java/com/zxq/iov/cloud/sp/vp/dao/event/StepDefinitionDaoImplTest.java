package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
public class StepDefinitionDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDefinitionDao taskDefinitionDao;
    @Autowired
    private IStepDefinitionDao stepDefinitionDao;

    @Test
    @Rollback(true)
    public void testCreateStepDefinition(){
        Long taskDefinitionId = 13L;
        StepDefinition stepDefinition = new StepDefinition();
        stepDefinition.setTaskDefinition(taskDefinitionDao.findTaskDefinitionById(taskDefinitionId));
        stepDefinition.setName("下发锁定车辆命令");
        stepDefinition.setStartCode("1149");
        stepDefinition.setIsRollback(false);
        stepDefinition.setIsLast(true);
        stepDefinition.setSort(0);
        stepDefinition = stepDefinitionDao.createStepDefinition(stepDefinition);
        Assert.assertNotNull(stepDefinition);
    }

    @Test
    @Rollback(true)
    public void testUpdateStepDefinition(){
        Long stepDefinitionId = 8L;
        StepDefinition stepDefinition = stepDefinitionDao.findStepDefinitionById(stepDefinitionId);
        stepDefinition.setName("下发锁定车辆命令2");
        stepDefinition = stepDefinitionDao.updateStepDefinition(stepDefinition);
        Assert.assertNotNull(stepDefinition);
    }

    @Test
    @Rollback(true)
    public void testRemoveStepDefinition() {
        Long stepDefinitionId = 8L;
        stepDefinitionDao.removeStepDefinition(stepDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testListStepDefinitionByCodeAndEventDefinitionId() {
        String startCode = "1113";
        Long eventDefinitionId = 23L;
        List<StepDefinition> list = stepDefinitionDao.listStepDefinitionByStartCodeAndEventDefinitionId(startCode,
                eventDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testListStepDefinitionByTaskDefinitionId() {
        Long taskDefinitionId = 13L;
        List<StepDefinition> list = stepDefinitionDao.listStepDefinitionByTaskDefinitionId(taskDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testPagingStepDefinition() {
        PageResult<StepDefinition> pageResult = new PageResult<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        pageResult = stepDefinitionDao.pagingStepDefinition(pageResult, paramMap);
        Assert.assertTrue(pageResult.getTotalCount()>0);
    }
}
