/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.StatusServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDao;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDao;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleStatusDao;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态服务接口实现类
 */
@Service
public class StatusServiceImpl extends BaseService implements IStatusService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImpl.class);

	@Autowired
	private IVehicleInfoDao vehicleInfoDao;
	@Autowired
	private IVehiclePosDao vehiclePosDao;
	@Autowired
	private IVehicleStatusDao vehicleStatusDao;

	@Override
	public VehicleInfo getVehicleInfo(String vin, Long eventId) throws ServLayerException {
		AssertRequired("vin", vin);
		VehicleInfo vehicleInfo = null;
		if (null != eventId) {
			List<VehicleInfo> vehicleInfos = vehicleInfoDao.listVehicleInfoByEventId(eventId);
			if (vehicleInfos.size() > 0) {
				vehicleInfo = vehicleInfos.get(0);
				vehicleInfo.setVehiclePos(vehiclePosDao.findVehiclePosByVehicleInfoId(vehicleInfo.getId()));
				vehicleInfo.setVehicleStatuses(vehicleStatusDao
						.findVehicleStatusByVehicleInfoId(vehicleInfo.getId(), Constants.VEHICLE_STATUS_BASIC));
				vehicleInfo.setVehicleAlerts(vehicleStatusDao
						.findVehicleStatusByVehicleInfoId(vehicleInfo.getId(), Constants.VEHICLE_STATUS_ALERT));
			}
		} else {
			vehicleInfo = vehicleInfoDao.readVehicleInfo(vin);
		}
		return vehicleInfo;
	}

	@Override
	public VehicleInfo logVehicleInfo(Tbox tbox, Integer sourceType, Long sourceId, VehiclePos vehiclePos,
			List<VehicleStatus> vehicleStatuses, List<VehicleStatus> vehicleAlerts, Date statusTime, Long eventId)
			throws ServLayerException {
		AssertRequired("tboxId,vin,userId", tbox.getTboxId(), tbox.getVin(), tbox.getUserId());
		VehicleInfo vehicleInfo = new VehicleInfo(tbox.getTboxId(), tbox.getVin(), sourceType, sourceId);
		vehicleInfo.setOwnerId(tbox.getUserId());
		if (null != eventId) {
			vehicleInfo.setEventId(eventId);
		}
		vehicleInfo.setStatusTime((null != statusTime) ? statusTime : new Date());
		vehicleInfoDao.createVehicleInfo(vehicleInfo);
		if (null != vehiclePos) {
			vehiclePos.setVehicleInfo(vehicleInfo);
			vehiclePosDao.createVehiclePos(vehiclePos);
		}
		if (null != vehicleStatuses) {
			for (VehicleStatus vehicleStatus : vehicleStatuses) {
				vehicleStatus.setVehicleInfo(vehicleInfo);
				vehicleStatusDao.createVehicleStatus(vehicleStatus);
			}
		}
		if (null != vehicleAlerts) {
			for (VehicleStatus vehicleStatus : vehicleAlerts) {
				vehicleStatus.setVehicleInfo(vehicleInfo);
				vehicleStatusDao.createVehicleStatus(vehicleStatus);
			}
		}
		return vehicleInfoDao.writeVehicleInfo(vehicleInfo);
	}

	@Override
	public VehicleInfo logVehicleAlert(Tbox tbox, Date alertTime, VehiclePos vehiclePos, VehicleStatus vehicleAlert)
			throws ServLayerException {
		AssertRequired("tboxId,alertTime,vehicleAlert", tbox.getTboxId(), alertTime, vehicleAlert);
		List<VehicleStatus> vehicleAlerts = new ArrayList<>();
		vehicleAlerts.add(vehicleAlert);
		return logVehicleInfo(tbox, null, null, vehiclePos, null, vehicleAlerts, alertTime, null);
	}
}
