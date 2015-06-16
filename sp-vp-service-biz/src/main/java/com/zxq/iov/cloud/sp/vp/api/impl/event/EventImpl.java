package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 安防 事件消息及回调实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 15:45
 * modify date 2015-6-15 16:46
 * @version 0.6, 2015-6-15
 */
@Service
public class EventImpl implements IEvent {

    @Autowired
    private EventDispatcher eventDispatch;

    @Override
    public void start(OtaDto otaDto) {
        start(otaDto, null, null);
    }

    @Override
    public Object start(OtaDto otaDto, Class clazz) {
        return start(otaDto, null, clazz);
    }

    @Override
    public Object start(OtaDto otaDto, Map<String, Object> paramMap, Class clazz) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        String owner = otaDto.getTboxId().toString();
        if(null == owner) {
            owner = otaDto.getTboxSn();
        }
        return eventDispatch.start(owner, otaDto.getEventId(), otaDto.getEventCreateTime(), code, paramMap, clazz);
    }

    @Override
    public void end(OtaDto otaDto) {
        end(otaDto, null, null);
    }

    @Override
    public void end(OtaDto otaDto, Object result) {
        end(otaDto, null, result);
    }

    @Override
    public void end(OtaDto otaDto, Map<String, Object> paramMap, Object result) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        eventDispatch.end(otaDto.getEventId(), otaDto.getEventCreateTime(), code, paramMap, result);
    }

    @Override
    public void error(OtaDto otaDto, Integer errorCode) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        eventDispatch.error(otaDto.getEventId(), otaDto.getEventCreateTime(), code, null, errorCode);
    }

    @Override
    public StepInstance findInstance(String owner, String code) {
        return eventDispatch.findInstance(owner, code);
    }
}