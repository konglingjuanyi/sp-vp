/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-05-15       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.status.VehiclePosDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 车辆位置信息数据访问测试类
 */
@Transactional
public class VehiclePosDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiclePosDaoImplTest.class);

	@Autowired
	private IVehicleInfoDao vehicleInfoDao;
	@Autowired
	private IVehiclePosDao vehiclePosDao;

	@Test
	@Rollback(true)
	public void testCreateVehiclePos() {
		Long vehicleInfoId = 6L;
		VehiclePos vehiclePos = new VehiclePos();
		vehiclePos.setAltitude(1);
		vehiclePos.setGpsStatus(1);
		vehiclePos.setGpsTime(new Date());
		vehiclePos.setHdop(1);
		vehiclePos.setHeading(0);
		vehiclePos.setLatitude(1);
		vehiclePos.setLongitude(1);
		vehiclePos.setSatellites(1);
		vehiclePos.setSpeed(10);
		vehiclePos.setVehicleInfo(vehicleInfoDao.findVehicleInfoById(vehicleInfoId));
		vehiclePos = vehiclePosDao.createVehiclePos(vehiclePos);
		Assert.assertNotNull(vehiclePos);
	}

	@Test
	@Rollback(true)
	public void testUpdateVehiclePos() {
		Long vehiclePosId = 13L;
		VehiclePos vehiclePos = vehiclePosDao.findVehiclePosById(vehiclePosId);
		vehiclePos.setGpsStatus(2);
		vehiclePos = vehiclePosDao.updateVehiclePos(vehiclePos);
		Assert.assertNotNull(vehiclePos);
		LOGGER.info("vehiclePos's gpsStatus = " + vehiclePos.getGpsStatus());
	}

	@Test
	@Rollback(true)
	public void testFindLatestVehiclePos() {
		String vin = "001";
		VehiclePos vehiclePos = vehiclePosDao.findLatestVehiclePos(vin);
		Assert.assertNotNull(vehiclePos);
	}

	@Test
	@Rollback(true)
	public void testFindVehiclePosByVehicleInfoId() {
		Long vehicleInfoId = 6L;
		VehiclePos vehiclePos = vehiclePosDao.findVehiclePosByVehicleInfoId(vehicleInfoId);
		Assert.assertNotNull(vehiclePos);
	}

}
