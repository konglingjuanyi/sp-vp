package com.zxq.iov.cloud.sp.vp.dao.event.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.repo.IStepInstanceRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 步骤实例持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-5 13:49
 * modify date 2015-6-15 14:30
 * @version 0.2, 2015-6-15
 */
@Service
public class StepInstanceDaoServiceImpl extends BaseServiceImpl<IStepInstanceRepository, StepInstance, Long> implements IStepInstanceDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepInstanceDaoServiceImpl.class);

    @Autowired
	public StepInstanceDaoServiceImpl(IStepInstanceRepository repo){
		super(repo);
	}

	public StepInstance createStepInstance(StepInstance stepInstance) {
		if (stepInstance == null){
			LOGGER.error("stepInstance cannot be null");
		}
		stepInstance.setId(null);
		super.save(stepInstance);
		super.flush();
		return stepInstance;
	}

	@Override
	public StepInstance updateStepInstance(StepInstance stepInstance) {
		if (stepInstance == null){
			LOGGER.error("stepInstance cannot be null");
		}
		super.update(stepInstance);
		return stepInstance;
	}

	@Override
	public void removeStepInstance(Long stepInstanceId) {
		if (stepInstanceId == null){
			LOGGER.error("stepInstanceId cannot be null");
		}
		super.delete(stepInstanceId);
	}

	@Override
	public StepInstance findStepInstanceById(Long stepInstanceId) {
		if (stepInstanceId == null){
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