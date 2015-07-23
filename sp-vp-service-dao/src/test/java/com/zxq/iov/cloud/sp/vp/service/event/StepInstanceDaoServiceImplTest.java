package com.zxq.iov.cloud.sp.vp.service.event;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 安防 步骤实例持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-5 14:04
 * modify date 2015-6-15 14:36
 * @version 0.2, 2015-6-15
 */
@Transactional
public class StepInstanceDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskInstanceDaoService taskInstanceDaoService;
    @Autowired
    private IStepInstanceDaoService stepInstanceDaoService;

    @Test
    @Rollback(true)
    public void testCreateStepInstance(){
        Long stepDefinitionId = 9L;
        Long taskInstanceId = 6L;
        StepInstance stepInstance = new StepInstance();
        stepInstance.setStepDefinitionId(stepDefinitionId);
        stepInstance.setTaskInstanceId(taskInstanceId);
        stepInstance.setRetryCount(3);
        stepInstance = stepInstanceDaoService.createStepInstance(stepInstance);
        Assert.assertNotNull(stepInstance);
    }

    @Test
    @Rollback(true)
    public void testUpdateStepInstance(){
        Long stepInstanceId = 8L;
        StepInstance stepInstance = stepInstanceDaoService.findStepInstanceById(stepInstanceId);
        stepInstance.setStartTime(new Date());
        stepInstance = stepInstanceDaoService.updateStepInstance(stepInstance);
        Assert.assertNotNull(stepInstance);
    }

    @Test
    @Rollback(true)
    public void testRemoveStepInstance() {
        Long stepInstanceId = 8L;
        stepInstanceDaoService.removeStepInstance(stepInstanceId);
    }

    @Test
    @Rollback(true)
    public void testListStepInstanceByTaskInstanceId() {
        Long taskInstanceId = 6L;
        List<StepInstance> list = stepInstanceDaoService.listStepInstanceByTaskInstanceId(taskInstanceId);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testListStepInstanceByEventInstanceId() {
        Long eventInstanceId = 9L;
        Long stepDefinitionId = 9L;
        Integer status = 1;
        List<StepInstance> list = stepInstanceDaoService.listStepInstanceByEventInstanceId(eventInstanceId,
                stepDefinitionId, status);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    @Rollback(true)
    public void testListStepInstanceByOwner() {
        String owner = "1";
        Long stepDefinitionId = 47L;
        Integer status = 2;
        List<StepInstance> list = stepInstanceDaoService.listStepInstanceByOwner(owner,
                stepDefinitionId, status);
        Assert.assertTrue(list.size() > 0);
    }

}
