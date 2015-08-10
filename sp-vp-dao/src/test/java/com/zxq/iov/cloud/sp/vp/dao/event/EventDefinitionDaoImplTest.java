package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


/**
 * 安防 事件定义持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-3 15:07
 * @version 0.1, 2015-6-3
 */
@Transactional
public class EventDefinitionDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventDefinitionDao eventDefinitionDao;

    @Test
    @Rollback(true)
    public void testCreateEventDefinition(){
        EventDefinition eventDefinition = new EventDefinition();
        eventDefinition.setName("被盗追踪事件");
        eventDefinition.setIsExclusive(true);
        eventDefinition.setIsContinue(true);
        eventDefinition.setIsRollback(false);
        eventDefinition = eventDefinitionDao.createEventDefinition(eventDefinition);
        Assert.assertNotNull(eventDefinition);
    }

    @Test
    @Rollback(true)
    public void testUpdateEventDefinition(){
        Long eventDefinitionId = 7L;
        EventDefinition eventDefinition = eventDefinitionDao.findEventDefinitionById(eventDefinitionId);
        eventDefinition.setName("被盗追踪事件2");
        eventDefinition = eventDefinitionDao.updateEventDefinition(eventDefinition);
        Assert.assertNotNull(eventDefinition);
    }

    @Test
    @Rollback(true)
    public void testRemoveEventDefinition() {
        Long eventDefinitionId = 7L;
        eventDefinitionDao.removeEventDefinition(eventDefinitionId);
    }

    @Test
    @Rollback(true)
    public void testPagingEventDefinition() {
        PageResult<EventDefinition> pageResult = new PageResult<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        pageResult = eventDefinitionDao.pagingEventDefinition(pageResult, paramMap);
        Assert.assertTrue(pageResult.getTotalCount()>0);
    }
}
