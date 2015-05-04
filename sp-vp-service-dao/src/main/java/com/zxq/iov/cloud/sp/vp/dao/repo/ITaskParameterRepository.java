package com.zxq.iov.cloud.sp.vp.dao.repo;

import com.zxq.iov.cloud.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskParameter;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;

/**
 * 安防 任务参数持久化服务Repository
 *
 * @author 叶荣杰
 * create date 2015-5-4 9:44:07
 * @version 0.1, 2015-5-4
 */
public interface ITaskParameterRepository extends BaseRepository<TaskParameter, Long> {
}
