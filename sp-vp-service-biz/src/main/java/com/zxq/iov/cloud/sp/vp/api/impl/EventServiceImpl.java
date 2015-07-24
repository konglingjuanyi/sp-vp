package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.api.impl.event.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 事件服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-29 17:07
 * modify date 2015-7-24 10:46
 * @version 0.3, 2015-7-24
 */
@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventDispatcher eventDispatcher;

    @Override
    public void dispatchTimeout(Long stepInstanceId) {
        eventDispatcher.timeout(stepInstanceId);
    }

    @Override
    public void dispatchAck(Long stepInstanceId) throws Exception {
        eventDispatcher.end(null, stepInstanceId);
    }

    @Override
    public void checkTimeout() {

    }
}
