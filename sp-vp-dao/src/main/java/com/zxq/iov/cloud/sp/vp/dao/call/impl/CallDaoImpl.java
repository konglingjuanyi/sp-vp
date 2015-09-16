/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 * 2015-08-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.call.impl.CallDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.call.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDao;
import com.zxq.iov.cloud.sp.vp.dao.call.repo.ICallRepository;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 呼叫数据访问接口实现类
 */
@Service
public class CallDaoImpl extends BaseServiceImpl<ICallRepository, Call, Long> implements ICallDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallDaoImpl.class);

	@Autowired
	public CallDaoImpl(ICallRepository repo) {
		super(repo);
	}

	@Override
	public Call createCall(Call call) {
		if (call == null) {
			LOGGER.error("call cannot be null");
		}
		call.setId(null);
		super.save(call);
		return call;
	}

	@Override
	public Call updateCall(Call call) {
		if (call == null) {
			LOGGER.error("call cannot be null");
		}
		super.update(call);
		return call;
	}

	@Override
	public void removeCall(Long callId) {
		if (callId == null) {
			LOGGER.error("callId cannot be null");
		}
		super.delete(callId);
	}

	@Override
	public Call findCallById(Long callId) {
		if (callId == null) {
			LOGGER.error("callId cannot be null");
		}
		return super.findOne(callId);
	}

	@Override
	public List<Call> listCallByTboxId(Long tboxId, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxId", tboxId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<Call> listCallByVin(String vin, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<Call> listCallByVinAndType(String vin, Integer type, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		paramMap.put("type", type);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}