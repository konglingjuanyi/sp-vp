package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.TaskInstance;

import java.util.List;


/**
 * 安防 任务实例持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-5 11:35
 * modify date 2015-6-8 10:37
 * @version 0.2, 2015-6-8
 */
public interface ITaskInstanceDao extends BaseService<TaskInstance, Long> {

	/**
	 * 创建任务实例
	 * @param taskInstance		任务实例对象
	 * @return					任务实例对象
	 */
	TaskInstance createTaskInstance(TaskInstance taskInstance);

	/**
	 * 更新任务实例
	 * @param taskInstance		任务实例对象
	 * @return					任务实例对象
	 */
	TaskInstance updateTaskInstance(TaskInstance taskInstance);

	/**
	 * 根据主键删除任务实例
	 * @param taskInstanceId 	任务实例主键
	 */
	void removeTaskInstance(Long taskInstanceId);

	/**
	 * 根据主键得到任务实例对象
	 * @param taskInstanceId 	任务实例主键
	 * @return					任务实例对象
	 */
	TaskInstance findTaskInstanceById(Long taskInstanceId);

	/**
	 * 根据事件实例ID得到任务实例列表
	 * @param eventInstanceId 	事件实例ID
	 * @param taskDefinitionId 	任务定义ID
	 * @param status 			状态
	 * @return   				任务实例列表
	 */
	List<TaskInstance> listTaskInstanceByEventInstanceId(Long eventInstanceId, Long taskDefinitionId,
														 Integer status);
	
}