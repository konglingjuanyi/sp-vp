package com.zxq.iov.cloud.sp.vp.dao.call.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDaoService;
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
 * modify date
 * @version 0.1, 2015-6-11
 */
@Service
public class CallDaoServiceImpl extends BaseServiceImpl<ICallRepository, Call, Long> implements ICallDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallDaoServiceImpl.class);

    @Autowired
	public CallDaoServiceImpl(ICallRepository repo){
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
}