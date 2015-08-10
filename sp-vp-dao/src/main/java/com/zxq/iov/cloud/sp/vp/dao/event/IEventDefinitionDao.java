package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventDefinition;

import java.util.Map;


/**
 * 安防 事件定义持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-3 14:38
 * modify date
 * @version 0.1, 2015-6-3
 */
public interface IEventDefinitionDao extends BaseService<EventDefinition, Long> {

	/**
	 * 创建事件定义
	 * @param eventDefinition	事件定义对象
	 * @return					事件定义对象
	 */
	EventDefinition createEventDefinition(EventDefinition eventDefinition);

	/**
	 * 更新事件定义
	 * @param eventDefinition	事件定义对象
	 * @return					事件定义对象
	 */
	EventDefinition updateEventDefinition(EventDefinition eventDefinition);

	/**
	 * 根据主键删除事件定义
	 * @param eventDefinitionId 事件定义主键
	 */
	void removeEventDefinition(Long eventDefinitionId);

	/**
	 * 根据主键得到事件定义对象
	 * @param eventDefinitionId 事件定义主键
	 * @return					事件定义对象
	 */
	EventDefinition findEventDefinitionById(Long eventDefinitionId);

	/**
	 * 根据参数MAP得到分页结果
	 * @param pageResult 		分页
	 * @param paramMap    		参数MAP
	 * @return                 	分页结果
	 */
	PageResult<EventDefinition> pagingEventDefinition(PageResult<EventDefinition> pageResult,
															Map<String, Object> paramMap);
	
}