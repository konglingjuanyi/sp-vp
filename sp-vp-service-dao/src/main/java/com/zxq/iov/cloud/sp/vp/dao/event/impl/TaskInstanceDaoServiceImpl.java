package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.ITaskInstanceRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 任务实例持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 11:46
 * modify date 2015-6-8 10:38
 * @version 0.2, 2015-6-8
 */
@Service
public class TaskInstanceDaoServiceImpl extends BaseServiceImpl<ITaskInstanceRepository, TaskInstance, Long> implements ITaskInstanceDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskInstanceDaoServiceImpl.class);

    @Autowired
	public TaskInstanceDaoServiceImpl(ITaskInstanceRepository repo){
		super(repo);
	}

	public TaskInstance createTaskInstance(TaskInstance taskInstance) {
		if (taskInstance == null){
			LOGGER.error("taskInstance cannot be null");
		}
		taskInstance.setId(null);
		super.save(taskInstance);
		super.flush();
		return taskInstance;
	}

	@Override
	public TaskInstance updateTaskInstance(TaskInstance taskInstance) {
		if (taskInstance == null){
			LOGGER.error("taskInstance cannot be null");
		}
		super.update(taskInstance);
		return taskInstance;
	}

	@Override
	public void removeTaskInstance(Long taskInstanceId) {
		if (taskInstanceId == null){
			LOGGER.error("taskInstanceId cannot be null");
		}
		super.delete(taskInstanceId);
	}

	@Override
	public TaskInstance findTaskInstanceById(Long taskInstanceId) {
		if (taskInstanceId == null){
			LOGGER.error("taskInstanceId cannot be null");
		}
		return super.findOne(taskInstanceId);
	}

	@Override
	public List<TaskInstance> listTaskInstanceByEventInstanceId(Long eventInstanceId, Long taskDefinitionId, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventInstanceId", eventInstanceId);
		paramMap.put("taskDefinitionId", taskDefinitionId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

}