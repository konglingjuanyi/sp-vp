/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: VehicleServiceImpl.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.service
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.dao.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.IStepDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.IStepRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Step;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 步骤持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-30 15:54
 * modify date 2015-5-8 15:33
 * @version 0.2, 2015-5-8
 */
@Service
public class StepDaoServiceImpl extends BaseServiceImpl<IStepRepository, Step, Long> implements IStepDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepDaoServiceImpl.class);

    @Autowired
	public StepDaoServiceImpl(IStepRepository repo){
		super(repo);
	}

	@Override
	public Step createStep(Step step) {
		if (step == null){
			LOGGER.error("Step cannot be null");
		}
		step.setId(null);
		super.save(step);
		return step;
	}

	@Override
	public Step updateStep(Step step) {
		if (step == null){
			LOGGER.error("step cannot be null");
		}
		super.update(step);
		return step;
	}

	@Override
	public Step findStepById(Long stepId) {
		if (stepId == null){
			LOGGER.error("stepId cannot be null");
		}
		return super.findOne(stepId);
	}
}