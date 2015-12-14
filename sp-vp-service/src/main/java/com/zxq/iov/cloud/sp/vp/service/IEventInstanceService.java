package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;

import java.util.List;

/**
 * 安防服务，事件实例接口
 */
public interface IEventInstanceService {

    List<EventInstance> listEventInstanceByEventDefinitionId(Long eventDefinitionId, long ownerId, Integer status);
}
