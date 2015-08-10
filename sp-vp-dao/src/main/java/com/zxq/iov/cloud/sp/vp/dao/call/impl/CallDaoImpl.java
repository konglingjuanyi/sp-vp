package com.zxq.iov.cloud.sp.vp.dao.call.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDao;
import com.zxq.iov.cloud.sp.vp.dao.call.repo.ICallRepository;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 呼叫持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 9:41
 * modify date 2015-8-4 15:24
 * @version 0.3, 2015-8-4
 */
@Service
public class CallDaoImpl extends BaseServiceImpl<ICallRepository, Call, Long> implements ICallDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallDaoImpl.class);

    @Autowired
	public CallDaoImpl(ICallRepository repo){
		super(repo);
	}

	@Override
	public Call createCall(Call call) {
		if (call == null){
			LOGGER.error("call cannot be null");
		}
		call.setId(null);
		super.save(call);
		return call;
	}

	@Override
	public Call updateCall(Call call) {
		if (call == null){
			LOGGER.error("call cannot be null");
		}
		super.update(call);
		return call;
	}

	@Override
	public void removeCall(Long callId) {
		if (callId == null){
			LOGGER.error("callId cannot be null");
		}
		super.delete(callId);
	}

	@Override
	public Call findCallById(Long callId) {
		if (callId == null){
			LOGGER.error("callId cannot be null");
		}
		return super.findOne(callId);
	}

	@Override
	public List<Call> listCallByTboxId(Long tboxId, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tboxId", tboxId);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<Call> listCallByVin(String vin, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}

	@Override
	public List<Call> listCallByVinAndType(String vin, Integer type, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		paramMap.put("type", type);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}