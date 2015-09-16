/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-11       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.call.CallRecordDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防服务 呼叫记录数据访问测试类
 */
@Transactional
public class CallRecordDaoImplTest extends BaseServiceTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallRecordDaoImplTest.class);

	@Autowired
	private ICallRecordDao callRecordDao;

	@Test
	@Rollback(false)
	public void testCreateCallRecord() {
		Long callId = 46L;
		String callNumber = "4008888888";
		CallRecord callRecord = new CallRecord();
		callRecord.setCallId(callId);
		callRecord.setCallNumber(callNumber);
		callRecord = callRecordDao.createCallRecord(callRecord);
		Assert.assertNotNull(callRecord);
	}

	@Test
	@Rollback(false)
	public void testUpdateCallRecord() {
		Long callRecordId = 4L;
		CallRecord callRecord = callRecordDao.findCallRecordById(callRecordId);
		callRecord.setCallTime(new Date());
		callRecord = callRecordDao.updateCallRecord(callRecord);
		Assert.assertNotNull(callRecord);
		LOGGER.info("callRecord's callTime = " + callRecord.getCreateDate());
	}

	@Test
	@Rollback(false)
	public void testRemoveCallRecord() {
		Long callRecordId = 4L;
		callRecordDao.removeCallRecord(callRecordId);
	}

}
