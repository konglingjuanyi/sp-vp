package com.zxq.iov.cloud.sp.vp.dao.repo;

import com.zxq.iov.cloud.core.dal.repo.datajpa.BaseRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskStep;

/**
 * 安防 任务步骤持久化服务Repository
 *
 * @author 叶荣杰
 * create date 2015-4-30 15:53:07
 * @version 0.1, 2015-4-30
 */
public interface ITaskStepRepository extends BaseRepository<TaskStep, Long> {
}
