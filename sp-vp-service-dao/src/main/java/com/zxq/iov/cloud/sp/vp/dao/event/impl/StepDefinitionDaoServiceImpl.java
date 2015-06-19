package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.zxq.iov.cloud.core.dal.repo.mybatis.PageResult;
import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IStepDefinitionRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 步骤定义持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-3 17:18
 * modify date 2015-6-18 12:39
 * @version 0.3, 2015-6-18
 */
@Service
public class StepDefinitionDaoServiceImpl extends BaseServiceImpl<IStepDefinitionRepository, StepDefinition, Long> implements IStepDefinitionDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitionDaoServiceImpl.class);

    @Autowired
	public StepDefinitionDaoServiceImpl(IStepDefinitionRepository repo){
		super(repo);
	}

	public StepDefinition createStepDefinition(StepDefinition stepDefinition) {
		if (stepDefinition == null){
			LOGGER.error("stepDefinition cannot be null");
		}
		stepDefinition.setId(null);
		super.save(stepDefinition);
		return stepDefinition;
	}

	@Override
	public StepDefinition updateStepDefinition(StepDefinition stepDefinition) {
		if (stepDefinition == null){
			LOGGER.error("stepDefinition cannot be null");
		}
		super.update(stepDefinition);
		return stepDefinition;
	}

	@Override
	public void removeStepDefinition(Long stepDefinitionId) {
		if (stepDefinitionId == null){
			LOGGER.error("stepDefinitionId cannot be null");
		}
		super.delete(stepDefinitionId);
	}

	@Override
	public StepDefinition findStepDefinitionById(Long stepDefinitionId) {
		if (stepDefinitionId == null){
			LOGGER.error("stepDefinitionId cannot be null");
		}
		return super.findOne(stepDefinitionId);
	}

	@Override
	public List<StepDefinition> listStepDefinitionByStartCodeAndEventDefinitionId(String startCode,
																				  Long eventDefinitionId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startCode", startCode);
		paramMap.put("eventDefinitionId", eventDefinitionId);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<StepDefinition> listStepDefinitionByTaskDefinitionId(Long taskDefinitionId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taskDefinitionId", taskDefinitionId);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public PageResult<StepDefinition> pagingStepDefinition(PageResult<StepDefinition> pageResult,
													Map<String, Object> paramMap) {
		return super.getPagedListViaBatis(pageResult, paramMap);
	}
}