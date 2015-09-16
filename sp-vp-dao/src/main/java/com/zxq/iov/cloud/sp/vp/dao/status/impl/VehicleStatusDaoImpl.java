/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-05-14       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.status.impl.VehicleStatusDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDao;
import com.zxq.iov.cloud.sp.vp.dao.status.repo.IVehicleStatusRepository;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 车辆状态信息数据访问接口实现类
 */
@Service
public class VehicleStatusDaoImpl extends BaseServiceImpl<IVehicleStatusRepository, VehicleStatus, Long>
		implements IVehicleStatusDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStatusDaoImpl.class);

	@Autowired
	public VehicleStatusDaoImpl(IVehicleStatusRepository repo) {
		super(repo);
	}

	@Override
	public VehicleStatus createVehicleStatus(VehicleStatus vehicleStatus) {
		if (vehicleStatus == null) {
			LOGGER.error("VehicleStatus cannot be null");
		}
		vehicleStatus.setId(null);
		super.save(vehicleStatus);
		// 写入缓存
		return vehicleStatus;
	}

	@Override
	public VehicleStatus updateVehicleStatus(VehicleStatus vehicleStatus) {
		if (vehicleStatus == null) {
			LOGGER.error("VehicleStatus cannot be null");
		}
		super.update(vehicleStatus);
		return vehicleStatus;
	}

	@Override
	public VehicleStatus findVehicleStatusById(Long vehicleStatusId) {
		if (vehicleStatusId == null) {
			LOGGER.error("VehicleStatus ID cannot be null");
		}
		return super.findOne(vehicleStatusId);
	}

	@Override
	public List<VehicleStatus> findLatestVehicleStatus(String vin, Integer type) {
		List<VehicleStatus> vehicleStatuses = new ArrayList<>();
		// 读取缓存
		return vehicleStatuses;
	}

	@Override
	public List<VehicleStatus> findVehicleStatusByVehicleInfoId(Long vehicleInfoId, Integer type) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vehicleInfoId", vehicleInfoId);
		paramMap.put("type", type);
		return super.findListViaBatis(paramMap);
	}
}