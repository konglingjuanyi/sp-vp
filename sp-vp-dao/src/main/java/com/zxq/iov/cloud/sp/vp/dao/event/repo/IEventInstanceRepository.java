package com.zxq.iov.cloud.sp.vp.dao.event.repo;

import com.saicmotor.telematics.framework.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;

/**
 * 安防 事件实例持久化服务Repository
 *
 * @author 叶荣杰
 * create date 2015-6-5 10:40
 * @version 0.1, 2015-6-5
 */
public interface IEventInstanceRepository extends BaseRepository<EventInstance, Long> {
}
