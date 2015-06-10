package com.zxq.iov.cloud.sp.vp.dao.event.repo;

import com.zxq.iov.cloud.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;

/**
 * 安防 任务实例持久化服务Repository
 *
 * @author 叶荣杰
 * create date 2015-6-5 11:44
 * @version 0.1, 2015-6-5
 */
public interface ITaskInstanceRepository extends BaseRepository<TaskInstance, Long> {
}
