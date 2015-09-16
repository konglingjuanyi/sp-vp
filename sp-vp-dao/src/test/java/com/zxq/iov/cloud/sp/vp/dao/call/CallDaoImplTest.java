/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 * 2015-07-09       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.call.CallDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 呼叫数据访问测试类
 */
@Transactional
public class CallDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallDaoImplTest.class);

	private String vin = "11111111111111111";
	private Long tboxId = 1L;

	@Autowired
	private ICallDao callDao;

	@Test
	@Rollback(false)
	public void testCreateCall() {
		Integer type = 1;
		Integer callType = 1;
		Integer crashSeverity = 1;
		Assert.assertNotNull(callDao.createCall(new Call(vin, tboxId, type, callType, crashSeverity, new Date())));
	}

	@Test
	@Rollback(false)
	public void testUpdateCall() {
		Long callId = 60L;
		Call call = callDao.findCallById(callId);
		call.setCallType(2);
		call = callDao.updateCall(call);
		Assert.assertNotNull(call);
		LOGGER.info("call's callType = " + call.getCallType());
	}

	@Test
	@Rollback(false)
	public void testRemoveCall() {
		Long callId = 60L;
		callDao.removeCall(callId);
	}

	@Test
	@Rollback(false)
	public void testListCallByTboxId() {
		List<Call> list = callDao.listCallByTboxId(tboxId, 1);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(false)
	public void testListCallByVin() {
		List<Call> list = callDao.listCallByVin(vin, 2);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	@Test
	@Rollback(false)
	public void testListCallByVinAndType() {
		Integer type = 1;
		List<Call> list = callDao.listCallByVinAndType(vin, type, 1);
		Assert.assertTrue(list.size() > 0);
		LOGGER.info("list size = " + list.size());
	}

	/**
	 * 压力测试内部类，用以测试DAO接口的并发情况
	 */
	class LoadTest extends Thread {
		@Override
		public void run() {
			LOGGER.info("线程" + Thread.currentThread().getName() + "开始");
			double begin = System.currentTimeMillis();
			testCreateCall();
			double end = System.currentTimeMillis();
			double time = end - begin;
			LOGGER.info("current thread time is " + time + "millis");
			LOGGER.info("线程" + Thread.currentThread().getName() + "结束");
		}
	}

	/**
	 * 压力测试执行方法
	 *
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void testLoad() throws Exception {
		LoadTest test = new LoadTest();
		int base = 1;
		for (int i = 50; i < 60; i++) {
			System.out.println("==========开始" + i * base + "线程并发==========");
			for (int j = 0; j < i * base; j++) {
				new Thread(test).start();
			}
			Thread.sleep(i * base * 500);
			System.out.println("==========并发结束==========");
		}
	}
}
