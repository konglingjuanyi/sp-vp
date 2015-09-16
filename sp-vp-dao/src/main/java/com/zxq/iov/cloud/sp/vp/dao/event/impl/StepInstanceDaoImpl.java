/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-05       荣杰         1.0            Initial Version
 * 2015-06-15       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.event.impl.StepInstanceDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepInstanceDao;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IStepInstanceRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 步骤实例数据访问接口实现类
 */
@Service
public class StepInstanceDaoImpl extends BaseServiceImpl<IStepInstanceRepository, StepInstance, Long>
		implements IStepInstanceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepInstanceDaoImpl.class);

	@Autowired
	public StepInstanceDaoImpl(IStepInstanceRepository repo) {
		super(repo);
	}

	public StepInstance createStepInstance(StepInstance stepInstance) {
		if (stepInstance == null) {
			LOGGER.error("stepInstance cannot be null");
		}
		stepInstance.setId(null);
		super.save(stepInstance);
		super.flush();
		return stepInstance;
	}

	@Override
	public StepInstance updateStepInstance(StepInstance stepInstance) {
		if (stepInstance == null) {
			LOGGER.error("stepInstance cannot be null");
		}
		super.update(stepInstance);
		return stepInstance;
	}

	@Override
	public void removeStepInstance(Long stepInstanceId) {
		if (stepInstanceId == null) {
			LOGGER.error("stepInstanceId cannot be null");
		}
		super.delete(stepInstanceId);
	}

	@Override
	public StepInstance findStepInstanceById(Long stepInstanceId) {
		if (stepInstanceId == null) {
			LOGGER.error("stepInstanceId cannot be null");
		}
		return super.findOne(stepInstanceId);
	}

	@Override
	public List<StepInstance> listStepInstanceByTaskInstanceId(Long taskInstanceId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("taskInstanceId", taskInstanceId);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<StepInstance> listStepInstanceByEventInstanceId(Long eventInstanceId, Long stepDefinitionId,
			Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventInstanceId", eventInstanceId);
		paramMap.put("stepDefinitionId", stepDefinitionId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<StepInstance> listStepInstanceByOwner(String owner, Long stepDefinitionId, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("owner", owner);
		paramMap.put("stepDefinitionId", stepDefinitionId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}