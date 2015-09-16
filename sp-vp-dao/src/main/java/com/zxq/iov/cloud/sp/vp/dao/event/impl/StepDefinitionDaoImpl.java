/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-03       荣杰         1.0            Initial Version
 * 2015-06-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.StepDefinitionDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepDefinitionDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IStepDefinitionRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 步骤定义数据访问接口实现类
 */
@Service
public class StepDefinitionDaoImpl extends BaseServiceImpl<IStepDefinitionRepository, StepDefinition, Long>
		implements IStepDefinitionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitionDaoImpl.class);

	@Autowired
	public StepDefinitionDaoImpl(IStepDefinitionRepository repo) {
		super(repo);
	}

	public StepDefinition createStepDefinition(StepDefinition stepDefinition) {
		if (stepDefinition == null) {
			LOGGER.error("stepDefinition cannot be null");
		}
		stepDefinition.setId(null);
		super.save(stepDefinition);
		return stepDefinition;
	}

	@Override
	public StepDefinition updateStepDefinition(StepDefinition stepDefinition) {
		if (stepDefinition == null) {
			LOGGER.error("stepDefinition cannot be null");
		}
		super.update(stepDefinition);
		return stepDefinition;
	}

	@Override
	public void removeStepDefinition(Long stepDefinitionId) {
		if (stepDefinitionId == null) {
			LOGGER.error("stepDefinitionId cannot be null");
		}
		super.delete(stepDefinitionId);
	}

	@Override
	public StepDefinition findStepDefinitionById(Long stepDefinitionId) {
		if (stepDefinitionId == null) {
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