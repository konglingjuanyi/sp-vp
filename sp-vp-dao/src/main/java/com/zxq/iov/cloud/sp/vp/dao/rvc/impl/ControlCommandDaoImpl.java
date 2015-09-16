/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-06-29       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.rvc.impl.ControlCommandDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.rvc.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDao;
import com.zxq.iov.cloud.sp.vp.dao.rvc.repo.IControlCommandRepository;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 控制命令数据访问接口实现类
 */
@Service
public class ControlCommandDaoImpl extends BaseServiceImpl<IControlCommandRepository, ControlCommand, Long>
		implements IControlCommandDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControlCommandDaoImpl.class);

	@Autowired
	public ControlCommandDaoImpl(IControlCommandRepository repo) {
		super(repo);
	}

	@Override
	public ControlCommand createControlCommand(ControlCommand controlCommand) {
		if (controlCommand == null) {
			LOGGER.error("controlCommand cannot be null");
		}
		controlCommand.setId(null);
		super.save(controlCommand);
		return controlCommand;
	}

	@Override
	public ControlCommand updateControlCommand(ControlCommand controlCommand) {
		if (controlCommand == null) {
			LOGGER.error("controlCommand cannot be null");
		}
		super.update(controlCommand);
		return controlCommand;
	}

	@Override
	public void removeControlCommand(Long controlCommandId) {
		if (controlCommandId == null) {
			LOGGER.error("controlCommandId cannot be null");
		}
		super.delete(controlCommandId);
	}

	@Override
	public ControlCommand findControlCommandById(Long controlCommandId) {
		if (controlCommandId == null) {
			LOGGER.error("controlCommandId cannot be null");
		}
		return super.findOne(controlCommandId);
	}

	@Override
	public ControlCommand findControlCommandByEventId(Long eventId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		List<ControlCommand> list = super.findListViaBatis(paramMap);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<ControlCommand> listControlCommandByVinAndCommand(String vin, String command, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		paramMap.put("command", command);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}