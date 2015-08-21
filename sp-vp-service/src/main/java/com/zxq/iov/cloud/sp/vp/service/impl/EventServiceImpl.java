package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import com.zxq.iov.cloud.sp.vp.service.impl.event.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 事件消息及回调实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 15:45
 * modify date 2015-8-11 9:53
 * @version 0.16, 2015-8-11
 */
@Service
@Transactional
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventDispatcher eventDispatch;

    @Override
    public Event start(Event event) throws ServLayerException {
        return eventDispatch.start(event);
    }

    @Override
    public void end(Event event) throws ServLayerException {
        eventDispatch.end(event);
    }

    @Override
    public void error(String owner, String code, Integer errorCode, Long eventId) throws ServLayerException {
        eventDispatch.error(eventId, code, null, errorCode);
    }

    @Override
    public StepInstance findInstance(String owner, String code) throws ServLayerException {
        return eventDispatch.findInstance(owner, code);
    }

    @Autowired
    private EventDispatcher eventDispatcher;

    @Override
    public void dispatchTimeout(Long stepInstanceId) {
        eventDispatcher.timeout(stepInstanceId);
    }

    @Override
    public void dispatchAck(String owner, Long stepInstanceId) throws ServLayerException {
        eventDispatcher.end(owner, stepInstanceId);
    }

    @Override
    public void checkTimeout() {

    }
}