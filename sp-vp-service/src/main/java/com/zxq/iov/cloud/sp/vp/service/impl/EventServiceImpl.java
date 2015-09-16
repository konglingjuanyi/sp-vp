/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-08-11       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.EventServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import com.zxq.iov.cloud.sp.vp.service.impl.event.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防服务 事件消息及回调服务接口实现类
 */
@Service
@Transactional
public class EventServiceImpl implements IEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

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