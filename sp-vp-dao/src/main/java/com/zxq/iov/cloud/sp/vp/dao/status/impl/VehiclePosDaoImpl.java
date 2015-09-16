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
 * com.zxq.iov.cloud.sp.vp.dao.status.impl.VehiclePosDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDao;
import com.zxq.iov.cloud.sp.vp.dao.status.repo.IVehiclePosRepository;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 车辆位置信息数据访问接口实现类
 */
@Service
public class VehiclePosDaoImpl extends BaseServiceImpl<IVehiclePosRepository, VehiclePos, Long>
		implements IVehiclePosDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiclePosDaoImpl.class);

	@Autowired
	public VehiclePosDaoImpl(IVehiclePosRepository repo) {
		super(repo);
	}

	@Override
	public VehiclePos createVehiclePos(VehiclePos vehiclePos) {
		if (vehiclePos == null) {
			LOGGER.error("VehiclePos cannot be null");
		}
		vehiclePos.setId(null);
		super.save(vehiclePos);
		// 写入缓存
		return vehiclePos;
	}

	@Override
	public VehiclePos updateVehiclePos(VehiclePos vehiclePos) {
		if (vehiclePos == null) {
			LOGGER.error("VehiclePos cannot be null");
		}
		super.update(vehiclePos);
		return vehiclePos;
	}

	@Override
	public VehiclePos findVehiclePosById(Long vehiclePosId) {
		if (vehiclePosId == null) {
			LOGGER.error("VehiclePos ID cannot be null");
		}
		return super.findOne(vehiclePosId);
	}

	@Override
	public VehiclePos findLatestVehiclePos(String vin) {
		VehiclePos vehiclePos = new VehiclePos();
		// 读取缓存
		return vehiclePos;
	}

	@Override
	public VehiclePos findVehiclePosByVehicleInfoId(Long vehicleInfoId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vehicleInfoId", vehicleInfoId);
		List<VehiclePos> list = super.findListViaBatis(paramMap);
		return list.size() > 0 ? list.get(0) : null;
	}
}