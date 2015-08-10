package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;

import java.util.List;


/**
 * 安防 事件规则持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-5 16:59
 * modify date
 * @version 0.1, 2015-6-5
 */
public interface IEventRuleDao extends BaseService<EventRule, Long> {

	/**
	 * 创建事件规则
	 * @param eventRule			事件规则对象
	 * @return					事件规则对象
	 */
	EventRule createEventRule(EventRule eventRule);

	/**
	 * 更新事件规则
	 * @param eventRule			事件规则对象
	 * @return					事件规则对象
	 */
	EventRule updateEventRule(EventRule eventRule);

	/**
	 * 根据主键删除事件规则
	 * @param eventRuleId 		事件规则主键
	 */
	void removeEventRule(Long eventRuleId);

	/**
	 * 根据主键得到事件规则对象
	 * @param eventRuleId 		事件规则主键
	 * @return					事件规则对象
	 */
	EventRule findEventRuleById(Long eventRuleId);

	/**
	 * 根据步骤定义ID得到事件规则列表
	 * @param stepDefinitionId 	步骤定义ID
	 * @return                 	事件规则列表
	 */
	List<EventRule> listEventRuleByStepDefinitionId(Long stepDefinitionId);

}