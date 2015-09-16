/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-29       荣杰         1.0            Initial Version
 * 2015-08-07       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.EventApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.api.IEventApi;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防服务 事件API实现类
 */
@Service
public class EventApiImpl implements IEventApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventApiImpl.class);

    @Autowired
    private IEventService eventService;

    @Override
    public void dispatchTimeout(Long stepInstanceId) {
        eventService.dispatchTimeout(stepInstanceId);
    }

    @Override
    public void dispatchAck(Long tboxId, Long stepInstanceId) throws ServLayerException {
        String vin = "11111111111111111"; // 这里应该根据TBOX ID获得VIN
        eventService.dispatchAck(vin, stepInstanceId);
    }

    @Override
    public void checkTimeout() {
        eventService.checkTimeout();
    }
}
