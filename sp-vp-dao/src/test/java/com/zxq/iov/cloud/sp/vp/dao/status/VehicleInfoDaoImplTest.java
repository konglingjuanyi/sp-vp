/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-03       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.status.VehicleInfoDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防服务 车辆信息数据访问测试类
 */
@Transactional
public class VehicleInfoDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleInfoDaoImplTest.class);

	private Long tboxId = 1L;

	@Autowired
	private IVehicleInfoDao vehicleInfoDao;

	@Test
	@Rollback(false)
	public void testCreateVehicleInfo() {
		VehicleInfo vehicleInfo = new VehicleInfo();
		vehicleInfo.setOwnerId(1L);
		vehicleInfo.setSourceType(1);
		vehicleInfo.setVin("001");
		vehicleInfo.setTboxId(tboxId);
		vehicleInfo = vehicleInfoDao.createVehicleInfo(vehicleInfo);
		Assert.assertNotNull(vehicleInfo);
	}

	@Test
	@Rollback(false)
	public void testUpdateVehicleInfo() {
		Long vehicleInfoId = 6L;
		VehicleInfo vehicleInfo = vehicleInfoDao.findVehicleInfoById(vehicleInfoId);
		vehicleInfo.setVin("002");
		vehicleInfo = vehicleInfoDao.updateVehicleInfo(vehicleInfo);
		Assert.assertNotNull(vehicleInfo);
		LOGGER.info("vehicleInfo's vin = " + vehicleInfo.getVin());
	}

	@Test
	@Rollback(false)
	public void testFindLatestVehicleInfo() {
		String vin = "001";
		VehicleInfo vehicleInfo = vehicleInfoDao.findLatestVehicleInfo(vin);
		Assert.assertNotNull(vehicleInfo);
	}

	@Test
	@Rollback(false)
	public void testListVehicleInfoByEventId() {
		Long eventId = 1L;
		List<VehicleInfo> list = vehicleInfoDao.listVehicleInfoByEventId(eventId);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}
}
