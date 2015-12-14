package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventInstanceDao;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;
import com.zxq.iov.cloud.sp.vp.service.IEventInstanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 事件实例接口
 */
@Service
public class EventInstanceServiceImpl  extends BaseService implements IEventInstanceService {
    @Autowired
    private IEventInstanceDao eventInstanceDao;
    @Override
    public List<EventInstance> listEventInstanceByEventDefinitionId(Long eventDefinitionId, long ownerId, Integer status) {
        return eventInstanceDao.listEventInstanceByEventDefinitionId(eventDefinitionId,Long.toString(ownerId),status);
    }
}
