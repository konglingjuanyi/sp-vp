package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEventApi;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 事件服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-29 17:07
 * modify date 2015-8-7 15:22
 * @version 0.4, 2015-8-7
 */
@Service
public class EventApiImpl implements IEventApi {

    @Autowired
    private IEventService eventService;

    @Override
    public void dispatchTimeout(Long stepInstanceId) {
        eventService.dispatchTimeout(stepInstanceId);
    }

    @Override
    public void dispatchAck(Long tboxId, Long stepInstanceId) throws Exception {
        String vin = "11111111111111111"; // 这里应该根据TBOX ID获得VIN
        eventService.dispatchAck(vin, stepInstanceId);
    }

    @Override
    public void checkTimeout() {
        eventService.checkTimeout();
    }
}
