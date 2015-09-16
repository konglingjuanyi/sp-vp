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
 * com.zxq.iov.cloud.sp.vp.service.BcallServiceImplTest
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 bCall服务测试类
 */
@Transactional
public class BcallServiceImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(BcallServiceImplTest.class);

	@Autowired
	private IBcallService bcallService;

	private String vin = "11111111111111111";
	private Long tboxId = 1L;
	private Long userId = 1L;

	@Test
	@Rollback(false)
	public void testStart() throws Exception {
		List<VehiclePos> vehiclePoses = new ArrayList<>();
		vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
		bcallService.start(new Tbox(tboxId, vin, userId), vehiclePoses, 0, 50, 60, null, new Date());
	}

	@Test
	@Rollback(false)
	public void testUpdate() throws Exception {
		List<VehiclePos> vehiclePoses = new ArrayList<>();
		vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
		bcallService.update(new Tbox(tboxId, vin, userId), vehiclePoses, 0, 50, 60, null, new Date());
	}

	@Test
	@Rollback(false)
	public void testHangUp() throws Exception {
		bcallService.hangUp(vin);
	}

	@Test
	@Rollback(false)
	public void testCallBack() throws Exception {
		String callNumber = "4008208888";
		bcallService.callBack(vin, callNumber);
	}

	@Test
	@Rollback(false)
	public void testResponseCallBack() throws Exception {
		bcallService.responseCallBack(tboxId, false, 1);
	}

	@Test
	@Rollback(false)
	public void testClose() throws Exception {
		bcallService.close(vin);
	}

}
