package com.zxq.iov.cloud.sp.vp.dao.rvc.impl;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.dao.rvc.repo.IControlCommandRepository;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安防 控制命令持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-17 11:38
 * modify date 2015-6-29 10:39
 * @version 0.2, 2015-6-29
 */
@Service
public class ControlCommandDaoServiceImpl extends BaseServiceImpl<IControlCommandRepository, ControlCommand, Long> implements IControlCommandDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControlCommandDaoServiceImpl.class);

    @Autowired
	public ControlCommandDaoServiceImpl(IControlCommandRepository repo){
		super(repo);
	}

	@Override
	public ControlCommand createControlCommand(ControlCommand controlCommand) {
		if (controlCommand == null){
			LOGGER.error("controlCommand cannot be null");
		}
		controlCommand.setId(null);
		super.save(controlCommand);
		return controlCommand;
	}

	@Override
	public ControlCommand updateControlCommand(ControlCommand controlCommand) {
		if (controlCommand == null){
			LOGGER.error("controlCommand cannot be null");
		}
		super.update(controlCommand);
		return controlCommand;
	}

	@Override
	public void removeControlCommand(Long controlCommandId) {
		if (controlCommandId == null){
			LOGGER.error("controlCommandId cannot be null");
		}
		super.delete(controlCommandId);
	}

	@Override
	public ControlCommand findControlCommandById(Long controlCommandId) {
		if (controlCommandId == null){
			LOGGER.error("controlCommandId cannot be null");
		}
		return super.findOne(controlCommandId);
	}

	@Override
	public ControlCommand findControlCommandByEventId(Long eventId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		List<ControlCommand> list = super.findListViaBatis(paramMap);
		return (list.size()>0)?list.get(0):null;
	}

	@Override
	public List<ControlCommand> listControlCommandByVinAndCommand(String vin, String command, Integer status) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vin", vin);
		paramMap.put("command", command);
		paramMap.put("status", status);
		return super.findListViaBatis(paramMap);
	}
}