package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 安防 事件消息及回调实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 15:45
 * modify date 2015-6-10 9:28
 * @version 0.3, 2015-6-10
 */
@Service
public class EventImpl implements IEvent {

    @Autowired
    private EventDispatcher eventDispatch;

    @Override
    public void start(OtaDto otaDto) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        eventDispatch.start(otaDto.getVin(), otaDto.getEventId(), otaDto.getEventCreateTime(), code, null, null);
    }

    @Override
    public Object start(OtaDto otaDto, Map<String, Object> paramMap, Class clazz) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        return eventDispatch.start(otaDto.getVin(), otaDto.getEventId(), otaDto.getEventCreateTime(), code, paramMap, clazz);
    }

    @Override
    public void end(OtaDto otaDto) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        eventDispatch.end(otaDto.getEventId(), otaDto.getEventCreateTime(), code, null, null);
    }

    @Override
    public void end(OtaDto otaDto, Map<String, Object> paramMap, Object result) {
        String code = otaDto.getAid().toString() + otaDto.getMid().toString();
        eventDispatch.end(otaDto.getEventId(), otaDto.getEventCreateTime(), code, paramMap, result);
    }
}