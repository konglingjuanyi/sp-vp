package com.zxq.iov.cloud.sp.vp.dao.event;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;

import java.util.List;
import java.util.Map;


/**
 * 安防 步骤定义持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-3 17:14
 * modify date 2015-6-18 12:37
 * @version 0.3, 2015-6-18
 */
public interface IStepDefinitionDaoService extends BaseService<StepDefinition, Long> {

	/**
	 * 创建步骤定义
	 * @param stepDefinition	步骤定义对象
	 * @return					步骤定义对象
	 */
	StepDefinition createStepDefinition(StepDefinition stepDefinition);

	/**
	 * 更新步骤定义
	 * @param stepDefinition	步骤定义对象
	 * @return					步骤定义对象
	 */
	StepDefinition updateStepDefinition(StepDefinition stepDefinition);

	/**
	 * 根据主键删除步骤定义
	 * @param stepDefinitionId 	步骤定义主键
	 */
	void removeStepDefinition(Long stepDefinitionId);

	/**
	 * 根据主键得到步骤定义对象
	 * @param stepDefinitionId 	步骤定义主键
	 * @return					步骤定义对象
	 */
	StepDefinition findStepDefinitionById(Long stepDefinitionId);

	/**
	 * 根据启动代码得到步骤定义列表
	 * @param startCode     	启动代码
	 * @param eventDefinitionId 事件定义ID
	 * @return                 	步骤定义列表
	 */
	List<StepDefinition> listStepDefinitionByStartCodeAndEventDefinitionId(String startCode, Long eventDefinitionId);

	/**
	 * 根据任务定义ID得到步骤定义列表
	 * @param taskDefinitionId 	任务定义ID
	 * @return                 	步骤定义列表走
	 */
	List<StepDefinition> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId);

	/**
	 * 根据参数MAP得到分页结果
	 * @param pageResult 		分页
	 * @param paramMap    		参数MAP
	 * @return                 	分页结果
	 */
	PageResult<StepDefinition> pagingStepDefinition(PageResult<StepDefinition> pageResult,
													Map<String, Object> paramMap);
	
}