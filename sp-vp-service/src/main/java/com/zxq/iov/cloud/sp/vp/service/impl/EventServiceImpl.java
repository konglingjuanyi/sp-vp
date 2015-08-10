package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.impl.event.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 安防 事件消息及回调实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 15:45
 * modify date 2015-8-7 13:46
 * @version 0.15, 2015-8-7
 */
@Service
@Transactional
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventDispatcher eventDispatch;
    @Autowired
    private ITboxDao tboxDaoService;

    @Override
    public Long start(String owner, String code) throws ServLayerException {
        return start(owner, code, null, null);
    }

    @Override
    public Long start(String owner, String code, Long eventId) throws ServLayerException {
        return start(owner, code, null, eventId);
    }

    @Override
    public Long start(String owner, String code, Map<String, Object> paramMap)
            throws ServLayerException {
        return start(owner, code, paramMap, null);
    }

    @Override
    public Long start(String owner, String code, Map<String, Object> paramMap, Long eventId)
            throws ServLayerException {
        return eventDispatch.start(owner, eventId, code, paramMap);
    }

    @Override
    public void end(String owner, String code) throws ServLayerException {
        end(owner, code, null, null, null);
    }

    @Override
    public void end(String owner, String code, Map<String, Object> paramMap) throws ServLayerException {
        end(owner, code, paramMap, null, null);
    }

    @Override
    public void end(String owner, String code, Map<String, Object> paramMap,
                    Object result) throws ServLayerException {
        end(owner, code, paramMap, result, null);
    }

    @Override
    public void end(String owner, String code, Object result) throws ServLayerException {
        end(owner, code, null, result, null);
    }

    @Override
    public void end(String owner, String code, Map<String, Object> paramMap, Object result,
                    Long eventId) throws ServLayerException {
        eventDispatch.end(owner, eventId, code, paramMap, result);
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