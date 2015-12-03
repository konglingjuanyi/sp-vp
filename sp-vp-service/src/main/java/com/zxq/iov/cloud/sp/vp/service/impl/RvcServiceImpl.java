/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-17       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.RvcServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDao;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IRvcService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程控制服务接口实现类
 */
@Service
public class RvcServiceImpl extends BaseService implements IRvcService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RvcServiceImpl.class);

	@Autowired
	private IControlCommandDao controlCommandDao;
	@Autowired
	private IStatusService statusService;

	private static final String RVC_STATUS_PENDING = "00";
	private static final String RVC_STATUS_COMPLETED = "02";
	private static final String RVC_STATUS_FAILED = "03";
	private static final Integer RUNNING_STATUS = 1;
	private static final Integer END_STATUS = 2;

	@Override
	public ControlCommand requestControl(String requestClient, Long userId, Tbox tbox, String command,
			Map<String, Object> parameters, Long eventId) throws ServLayerException {
		AssertRequired("userId,vin,command", tbox.getUserId(), tbox.getTboxId(), command);
		ControlCommand controlCommand = null;
		try {
			controlCommand = new ControlCommand(tbox.getTboxId(), tbox.getVin(), requestClient,
					Constants.RVC_CMD.get(command), Constants.RVC_CMD_CODE.get(command), JSON.json(parameters));
		} catch (IOException e) {
			e.printStackTrace();
		}
		controlCommand.setCommandStatus(RVC_STATUS_PENDING);
		controlCommand.setIsCancel(false);
		controlCommand.setStatus(RUNNING_STATUS);
		if (null != eventId) {
			controlCommand.setEventId(eventId);
		}
		controlCommandDao.createControlCommand(controlCommand);
		pushRequestClient(controlCommand);
		return controlCommand;
	}

	@Override
	public void cancelControl(String requestClient, Long userId, Tbox tbox, String command) throws ServLayerException {
		AssertRequired("userId,vin,command", tbox.getUserId(), tbox.getVin(), command);
		List<ControlCommand> list = controlCommandDao
				.listControlCommandByVinAndCommand(tbox.getVin(), command, RUNNING_STATUS);
		if (list.size() > 0) {
			ControlCommand controlCommand = list.get(0);
			// 此处另一个用户是否可以取消别的用户的命令？
			controlCommand.setIsCancel(true);
			controlCommand.setCommandStatus(RVC_STATUS_FAILED);
			controlCommand.setStatus(END_STATUS);
			controlCommandDao.updateControlCommand(controlCommand);
			pushRequestClient(controlCommand);
		}
	}

	@Override
	public ControlCommand updateControlStatus(Tbox tbox, byte[] rvcStatus, Integer failureType, VehiclePos vehiclePos,
			List<VehicleStatus> vehicleStatuses, Long eventId) throws ServLayerException {
		AssertRequired("eventId,tboxId,userId,rvcStatus", eventId, tbox.getTboxId(), tbox.getUserId(), rvcStatus);
		ControlCommand controlCommand = controlCommandDao.findControlCommandByEventId(eventId);
		if (null != controlCommand) {
			controlCommand.setCommandStatus(new String(rvcStatus));
			if (null != failureType) {
				controlCommand.setFailureType(failureType);
			}
			if (rvcStatus.equals(RVC_STATUS_COMPLETED) || rvcStatus.equals(RVC_STATUS_FAILED)) {
				controlCommand.setStatus(END_STATUS);
			}
			controlCommandDao.updateControlCommand(controlCommand);
			statusService.logVehicleInfo(tbox, Constants.VEHICLE_INFO_SOURCE_RVC, controlCommand.getId(), vehiclePos,
					vehicleStatuses, null, null, null);
			pushRequestClient(controlCommand);
		}
		return controlCommand;
	}

	@Override
	public ControlCommand getControlStatus(Long controlCommandId, Long userId, Tbox tbox) throws ServLayerException {
		AssertRequired("controlCommandId,vin", controlCommandId, tbox.getVin());
		ControlCommand controlCommand = controlCommandDao.findControlCommandById(controlCommandId);
		if (null != controlCommand) {
			if (!controlCommand.getVin().equals(tbox.getVin())) {
				throw new ServLayerException(ExceptionConstants.NO_PRIVILEGE_TO_VEHICLE);
			}
			return controlCommand;
		}
		throw new ServLayerException(ExceptionConstants.CONTROL_CMD_NOT_EXIST);
	}

	/**
	 * 推送请求终端
	 *
	 * @param controlCommand 控制命令对象
	 */
	private void pushRequestClient(ControlCommand controlCommand) {

	}
}