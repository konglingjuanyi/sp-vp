package com.zxq.iov.cloud.sp.vp.dao.event;

import com.zxq.iov.cloud.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventInstance;

import java.util.List;


/**
 * 安防 事件实例持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-5 10:28
 * modify date 2015-6-24 17:36
 * @version 0.3, 2015-6-24
 */
public interface IEventInstanceDaoService extends BaseService<EventInstance, Long> {

	/**
	 * 创建事件实例
	 * @param eventInstance		事件实例对象
	 * @return					事件实例对象
	 */
	EventInstance createEventInstance(EventInstance eventInstance);

	/**
	 * 更新事件实例
	 * @param eventInstance		事件实例对象
	 * @return					事件实例对象
	 */
	EventInstance updateEventInstance(EventInstance eventInstance);

	/**
	 * 根据主键删除事件实例
	 * @param eventInstanceId 	事件实例主键
	 */
	void removeEventInstance(Long eventInstanceId);

	/**
	 * 根据主键得到事件实例对象
	 * @param eventInstanceId 	事件实例主键
	 * @return					事件实例对象
	 */
	EventInstance findEventInstanceById(Long eventInstanceId);

	/**
	 * 根据事件定义ID得到事件实例列表
	 * @param eventDefinitionId 事件定义ID
	 * @param owner 			拥有者
	 * @param status           	状态
	 * @return                 	事件实例列表
	 */
	List<EventInstance> listEventInstanceByEventDefinitionId(Long eventDefinitionId, String owner, Integer status);
	
}