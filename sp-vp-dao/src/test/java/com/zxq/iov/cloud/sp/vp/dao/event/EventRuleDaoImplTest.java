package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 安防 事件规则持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-5 17:28
 * modify date
 * @version 0.1, 2015-6-5
 */
@Transactional
public class EventRuleDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventRuleDao eventRuleDao;

    @Test
    @Rollback(true)
    public void testCreateEventRule(){
        Long stepDefinitionId = 9L;
        EventRule eventRule = new EventRule();
        eventRule.setStepDefinitionId(stepDefinitionId);
        eventRule.setName("type");
        eventRule.setOperator("eq");
        eventRule = eventRuleDao.createEventRule(eventRule);
        Assert.assertNotNull(eventRule);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventRule(){
        Long eventRuleId = 4L;
        EventRule eventRule = eventRuleDao.findEventRuleById(eventRuleId);
        eventRule.setValue("1");
        eventRule = eventRuleDao.updateEventRule(eventRule);
        Assert.assertNotNull(eventRule);
    }

    @Test
    @Rollback(true)
    public void testRemoveEventRule() {
        Long eventRuleId = 4L;
        eventRuleDao.removeEventRule(eventRuleId);
    }

    @Test
    @Rollback(true)
    public void testListEventRulenByStepDefinitionId() {
        Long stepDefinitionId = 9L;
        List<EventRule> list = eventRuleDao.listEventRuleByStepDefinitionId(stepDefinitionId);
        Assert.assertTrue(list.size() > 0);
    }

}
