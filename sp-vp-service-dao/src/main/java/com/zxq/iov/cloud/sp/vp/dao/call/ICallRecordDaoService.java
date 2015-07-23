package com.zxq.iov.cloud.sp.vp.dao.call;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

import java.util.List;

/**
 * 安防 呼叫记录持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-11 9:37
 * modify date
 * @version 0.1, 2015-6-11
 */
public interface ICallRecordDaoService extends BaseService<CallRecord, Long> {

	/**
	 * 创建呼叫记录
	 * @param callRecord	呼叫记录对象
	 * @return				呼叫记录对象
	 */
	CallRecord createCallRecord(CallRecord callRecord);

	/**
	 * 更新呼叫记录
	 * @param callRecord	呼叫记录对象
	 * @return				呼叫记录对象
	 */
	CallRecord updateCallRecord(CallRecord callRecord);

	/**
	 * 删除呼叫记录
	 * @param callRecordId  呼叫记录主键
	 */
	void removeCallRecord(Long callRecordId);

	/**
	 * 根据主键得到呼叫记录对象
	 * @param callRecordId 	呼叫记录主键
	 * @return				呼叫记录对象
	 */
	CallRecord findCallRecordById(Long callRecordId);

	/**
	 * 根据呼叫ID得到呼叫记录对象列表
	 * @param callId    	呼叫ID
	 * @param status      	状态
	 * @return           	呼叫记录对象列表
	 */
	List<CallRecord> listCallRecordByCallId(Long callId, Integer status);
	
}