package com.zxq.iov.cloud.sp.vp.dao.repo;

import com.zxq.iov.cloud.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;

/**
 * 安防 任务持久化服务Repository
 *
 * @author 叶荣杰
 * create date 2015-4-30 14:53:07
 * @version 0.1, 2015-4-30
 */
public interface ITaskRepository extends BaseRepository<Task, Long> {
}
