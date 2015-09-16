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
 * com.zxq.iov.cloud.sp.vp.dao.call.ICallRecordDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

import java.util.List;

/**
 * 安防 呼叫记录数据访问接口
 */
public interface ICallRecordDao extends BaseService<CallRecord, Long> {

	/**
	 * 创建呼叫记录
	 *
	 * @param callRecord 呼叫记录对象
	 * @return 呼叫记录对象
	 */
	CallRecord createCallRecord(CallRecord callRecord);

	/**
	 * 更新呼叫记录
	 *
	 * @param callRecord 呼叫记录对象
	 * @return 呼叫记录对象
	 */
	CallRecord updateCallRecord(CallRecord callRecord);

	/**
	 * 删除呼叫记录
	 *
	 * @param callRecordId 呼叫记录主键
	 */
	void removeCallRecord(Long callRecordId);

	/**
	 * 根据主键得到呼叫记录对象
	 *
	 * @param callRecordId 呼叫记录主键
	 * @return 呼叫记录对象
	 */
	CallRecord findCallRecordById(Long callRecordId);

	/**
	 * 根据呼叫ID得到呼叫记录对象列表
	 *
	 * @param callId 呼叫ID
	 * @param status 状态
	 * @return 呼叫记录对象列表
	 */
	List<CallRecord> listCallRecordByCallId(Long callId, Integer status);

}