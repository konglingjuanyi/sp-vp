package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IStepDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Step;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 步骤持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-4-29 8:56
 * @version 0.1, 2015-4-29
 */
@Transactional
public class StepDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private IStepDaoService stepDaoService;

    @Test
    @Rollback(true)
    public void testCreateStep(){
        Long taskId = 14L;
        Step step = new Step();
        step.setTask(taskDaoService.findTaskById(taskId));
        step.setTbox("001");
        step.setAid("001");
        step.setMid(1);
        step.setStartTime(new Date());
        step = stepDaoService.createStep(step);
        Assert.assertNotNull(step);
    }

    @Test
    @Rollback(true)
    public void testUpdateStep(){
        Long stepId = 1L;
        Step step = stepDaoService.findStepById(stepId);
        step.setMid(2);
        step = stepDaoService.updateStep(step);
        Assert.assertNotNull(step);
    }

}
