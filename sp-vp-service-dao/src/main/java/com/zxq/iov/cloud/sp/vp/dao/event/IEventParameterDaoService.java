package com.zxq.iov.cloud.sp.vp.dao.event;

import com.zxq.iov.cloud.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;



/**
 * 安防 事件参数持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-8 15:48
 * modify date
 * @version 0.1, 2015-6-8
 */
public interface IEventParameterDaoService extends BaseService<EventParameter, Long> {

	/**
	 * 创建事件参数
	 * @param eventParameter	事件参数对象
	 * @return					事件参数对象
	 */
	EventParameter createEventParameter(EventParameter eventParameter);

	/**
	 * 更新事件参数
	 * @param eventParameter	事件参数对象
	 * @return					事件参数对象
	 */
	EventParameter updateEventParameter(EventParameter eventParameter);

	/**
	 * 根据主键删除事件参数
	 * @param eventParameterId 	事件参数主键
	 */
	void removeEventParameter(Long eventParameterId);

	/**
	 * 根据主键得到事件参数对象
	 * @param eventParameterId 	事件参数主键
	 * @return					事件参数对象
	 */
	EventParameter findEventParameterById(Long eventParameterId);

}