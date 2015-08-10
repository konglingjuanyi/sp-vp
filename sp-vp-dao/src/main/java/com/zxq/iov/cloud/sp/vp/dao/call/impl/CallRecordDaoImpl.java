package com.zxq.iov.cloud.sp.vp.dao.call.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallRecordDao;
import com.zxq.iov.cloud.sp.vp.dao.call.repo.ICallRecordRepository;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 呼叫记录持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 10:17
 * modify date
 * @version 0.1, 2015-6-11
 */
@Service
public class CallRecordDaoImpl extends BaseServiceImpl<ICallRecordRepository, CallRecord, Long> implements ICallRecordDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallRecordDaoImpl.class);

    @Autowired
	public CallRecordDaoImpl(ICallRecordRepository repo){
		super(repo);
	}

	@Override
	public CallRecord createCallRecord(CallRecord callRecord) {
		if (callRecord == null){
			LOGGER.error("callRecord cannot be null");
		}
		callRecord.setId(null);
		super.save(callRecord);
		return callRecord;
	}

	@Override
	public CallRecord updateCallRecord(CallRecord callRecord) {
		if (callRecord == null){
			LOGGER.error("callRecord cannot be null");
		}
		super.update(callRecord);
		return callRecord;
	}

	@Override
	public void removeCallRecord(Long callRecordId) {
		if (callRecordId == null){
			LOGGER.error("callRecordId cannot be null");
		}
		super.delete(callRecordId);
	}

	@Override
	public CallRecord findCallRecordById(Long callRecordId) {
		if (callRecordId == null){
			LOGGER.error("callRecordId cannot be null");
		}
		return super.findOne(callRecordId);
	}

	@Override
	public List<CallRecord> listCallRecordByCallId(Long callId, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("callId", callId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}