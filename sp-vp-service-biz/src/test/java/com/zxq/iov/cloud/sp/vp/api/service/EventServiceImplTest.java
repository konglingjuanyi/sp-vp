package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IEventService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 事件服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-30 17:44
 * modify date
 * @version 0.1, 2015-6-30
 */
@Transactional
public class EventServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventService eventService;

    @Test
    @Rollback(false)
    public void testDispatchAck() {
        Long stepId = 289L;
        eventService.dispatchAck(stepId);
    }

}
