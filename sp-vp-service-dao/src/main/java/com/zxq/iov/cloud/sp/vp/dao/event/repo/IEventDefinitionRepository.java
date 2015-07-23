package com.zxq.iov.cloud.sp.vp.dao.event.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;

/**
 * 安防 事件定义持久化服务Repository
 *
 * @author 叶荣杰
 * create date 2015-6-3 14:45
 * @version 0.1, 2015-6-3
 */
public interface IEventDefinitionRepository extends BaseRepository<EventDefinition, Long> {
}
