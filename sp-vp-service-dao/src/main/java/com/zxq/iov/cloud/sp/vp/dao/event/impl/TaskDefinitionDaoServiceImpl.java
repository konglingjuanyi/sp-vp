package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.ITaskDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.ITaskDefinitionRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskDefinition;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 任务定义持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-3 16:26
 * modify date 2015-6-4 13:14
 * @version 0.2, 2015-6-4
 */
@Service
public class TaskDefinitionDaoServiceImpl extends BaseServiceImpl<ITaskDefinitionRepository, TaskDefinition, Long> implements ITaskDefinitionDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskDefinitionDaoServiceImpl.class);

    @Autowired
	public TaskDefinitionDaoServiceImpl(ITaskDefinitionRepository repo){
		super(repo);
	}

	public TaskDefinition createTaskDefinition(TaskDefinition taskDefinition) {
		if (taskDefinition == null){
			LOGGER.error("taskDefinition cannot be null");
		}
		taskDefinition.setId(null);
		super.save(taskDefinition);
		return taskDefinition;
	}

	@Override
	public TaskDefinition updateTaskDefinition(TaskDefinition taskDefinition) {
		if (taskDefinition == null){
			LOGGER.error("taskDefinition cannot be null");
		}
		super.update(taskDefinition);
		return taskDefinition;
	}

	@Override
	public void removeTaskDefinition(Long taskDefinitionId) {
		if (taskDefinitionId == null){
			LOGGER.error("taskDefinitionId cannot be null");
		}
		super.delete(taskDefinitionId);
	}

	@Override
	public TaskDefinition findTaskDefinitionById(Long taskDefinitionId) {
		if (taskDefinitionId == null){
			LOGGER.error("taskDefinitionId cannot be null");
		}
		return super.findOne(taskDefinitionId);
	}

	@Override
	public List<TaskDefinition> listTaskDefinitionByEventDefinitionId(Long eventDefinitionId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventDefinitionId", eventDefinitionId);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public PageResult<TaskDefinition> pagingTaskDefinition(PageResult<TaskDefinition> pageResult,
													Map<String, Object> paramMap) {
		return super.getPagedListViaBatis(pageResult, paramMap);
	}
}