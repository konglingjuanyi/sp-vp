package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 事件服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-30 17:44
 * modify date 2015-8-7 16:13
 * @version 0.2, 2015-8-7
 */
@Transactional
public class EventServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IEventApi eventService;

    @Test
    @Rollback(false)
    public void testDispatchAck() throws Exception {
        Long stepId = 535L;
        Long tboxId = 1L;
        eventService.dispatchAck(tboxId, stepId);
    }

}
