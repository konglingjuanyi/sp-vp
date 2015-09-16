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
 * com.zxq.iov.cloud.sp.vp.dao.status.VehicleStatusDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防服务 车辆状态信息数据访问测试类
 */
@Transactional
public class VehicleStatusDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStatusDaoImplTest.class);

	@Autowired
	private IVehicleInfoDao vehicleInfoDao;
	@Autowired
	private IVehicleStatusDao vehicleStatusDao;

	@Test
	@Rollback(true)
	public void testCreateVehicleStatus() {
		Long vehicleInfoId = 6L;
		VehicleStatus vehicleStatus = new VehicleStatus();
		vehicleStatus.setCode("001");
		vehicleStatus.setName("001");
		vehicleStatus.setType(1);
		vehicleStatus.setValue(1);
		vehicleStatus.setVehicleInfo(vehicleInfoDao.findVehicleInfoById(vehicleInfoId));
		vehicleStatus = vehicleStatusDao.createVehicleStatus(vehicleStatus);
		Assert.assertNotNull(vehicleStatus);
	}

	@Test
	@Rollback(true)
	public void testUpdateVehicleStatus() {
		Long vehicleStatusId = 8L;
		VehicleStatus vehicleStatus = vehicleStatusDao.findVehicleStatusById(vehicleStatusId);
		vehicleStatus.setName("002");
		vehicleStatus = vehicleStatusDao.updateVehicleStatus(vehicleStatus);
		Assert.assertNotNull(vehicleStatus);
		LOGGER.info("vehicleStatus's name = " + vehicleStatus.getName());
	}

	@Test
	@Rollback(true)
	public void testFindLatestVehicleStatus() {
		String vin = "001";
		List<VehicleStatus> vehicleStatuses = vehicleStatusDao.findLatestVehicleStatus(vin, 1);
		Assert.assertTrue(vehicleStatuses.size() >= 0);
	}

	@Test
	@Rollback(true)
	public void testFindVehicleStatusByVehicleInfoId() {
		Long vehicleInfoId = 6L;
		List<VehicleStatus> vehicleStatuses = vehicleStatusDao.findVehicleStatusByVehicleInfoId(vehicleInfoId, 1);
		Assert.assertTrue(vehicleStatuses.size() > 0);
		LOGGER.info("list size = " + vehicleStatuses.size());
	}
}
