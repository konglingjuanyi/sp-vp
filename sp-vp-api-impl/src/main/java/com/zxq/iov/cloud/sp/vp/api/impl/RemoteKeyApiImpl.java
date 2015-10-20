/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-10-19       荣杰         1.2            增加手机端相关功能
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.RemoteKeyApiImpl
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ApiException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.mds.tcmp.api.IUserApi;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.UserDto;
import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.DeleteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.WriteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.key.RemoteKeyDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防服务 智能钥匙API实现类
 */
@Service
public class RemoteKeyApiImpl extends BaseApi implements IRemoteKeyApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyApiImpl.class);

	@Autowired
	private IRemoteKeyService remoteKeyService;
	@Autowired
	private IEventService eventService;
	@Autowired
	private IUserApi userApi;

	@Override
	public void createKey(Long ownerId, String vin) throws ApiException {
		AssertRequired("ownerId,vin", ownerId, vin);
		checkVinOwner(vin, ownerId);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_REMOTE_KEY, 1);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		RemoteKey remoteKey = null;
		eventService.start(event);
		if (!event.isRetry()) {
			remoteKey = remoteKeyService.createKey(ownerId, getTboxByVin(vin).getTboxId(), vin);
			event.setResult(remoteKey);
			eventService.end(event);
		} else {
			LOGGER.info("重试创建钥匙操作");
			remoteKey = event.getResult(RemoteKey.class);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto,
				new WriteKeyDto(remoteKey.getType(), remoteKey.getSecretKey().getBytes(), remoteKey.getReference(),
						remoteKey.getValidStartTime(), remoteKey.getValidEndTime()));
	}

	@Override
	public void grantKey(Long ownerId, String vin, String mobile, Date startTime, Date endTime, Integer privilege,
			String pin) throws ApiException {
		AssertRequired("ownerId,vin,mobile,pin", ownerId, vin, mobile, pin);
		checkVinOwner(vin, ownerId);
		OtaDto otaDto = new OtaDto(getTboxByVin(vin).getTboxId(), vin, Constants.AID_REMOTE_KEY, 1);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		RemoteKey remoteKey = null;
		eventService.start(event);
		if (!event.isRetry()) {
			Long userId = null; //TODO 调用根据手机号得到用户的接口（唐善华）
			boolean isPinOk = true;
			if (isPinOk) { //TODO 调用主数据检查用户PIN码的接口（唐善华）
				remoteKey = remoteKeyService.grantKey(
						new RemoteKey(getTboxByVin(vin).getTboxId(), vin, startTime, endTime, privilege, userId));
				//TODO 调用消息接口通知被授权用户（吕春田）
			}
		} else {
			LOGGER.info("重试授权钥匙操作");
			remoteKey = event.getResult(RemoteKey.class);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto,
				new WriteKeyDto(remoteKey.getType(), remoteKey.getSecretKey().getBytes(), remoteKey.getReference(),
						remoteKey.getValidStartTime(), remoteKey.getValidEndTime()));
	}

	@Override
	public void updateKey(Long ownerId, Long keyReference, Date startTime, Date endTime, Integer privilege)
			throws ApiException {
		AssertRequired("ownerId,keyReference", ownerId, keyReference);
		remoteKeyService.updateKey(keyReference, startTime, endTime, privilege);
		//TODO 调用消息接口通知被授权用户（吕春田）
		//TODO 修改协议待定（刘凯），确定后发队列
	}

	@Override
	public void disableKey(Long ownerId, Long keyReference) throws ApiException {
		AssertRequired("ownerId,keyReference", ownerId, keyReference);
		RemoteKey remoteKey = remoteKeyService.disableKey(keyReference);
		if (null != remoteKey) {
			if (ownerId.equals(remoteKey.getUserId())) {
				//TODO 调用消息接口通知被授权用户（吕春田）
			}
			//TODO 修改协议待定（刘凯），确定后发队列
		}
	}

	@Override
	public void enableKey(Long ownerId, Long keyReference) throws ApiException {
		AssertRequired("ownerId,keyReference", ownerId, keyReference);
		RemoteKey remoteKey = remoteKeyService.enableKey(keyReference);
		if (null != remoteKey) {
			if (ownerId.equals(remoteKey.getUserId())) {
				//TODO 调用消息接口通知被授权用户（吕春田）
			}
			//TODO 修改协议待定（刘凯），确定后发队列
		}
	}

	@Override
	public void removeKey(Long ownerId, Long keyReference) throws ApiException {
		AssertRequired("ownerId,keyReference", ownerId, keyReference);
		RemoteKey remoteKey = remoteKeyService.findKeyByReference(keyReference);
		OtaDto otaDto = new OtaDto(remoteKey.getTboxId(), remoteKey.getVin(), Constants.AID_REMOTE_KEY, 3);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			remoteKeyService.removeKey(keyReference);
			eventService.end(event);
		}
		otaDto.setEventId(event.getId());
		sendQueue(otaDto, new DeleteKeyDto(keyReference));
	}

	@Override
	public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason)
			throws ServLayerException {
		AssertRequired("otaDto,writeSuccess", otaDto, writeSuccess);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			remoteKeyService.responseWriteKey(otaDto.getTboxId(), writeSuccess, writeFailureReason);
			eventService.end(event);
		}
	}

	@Override
	public void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess, Integer deleteFailureReason)
			throws ServLayerException {
		AssertRequired("otaDto,deleteSuccess", otaDto, deleteSuccess);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			remoteKeyService.responseDeleteKey(otaDto.getTboxId(), deleteSuccess, deleteFailureReason);
			eventService.end(event);
		}
	}

	@Override
	public void keyAlarm(OtaDto otaDto) throws ServLayerException {
		AssertRequired("otaDto", otaDto);
		Event event = new EventAssembler().fromOtaDto(otaDto);
		eventService.start(event);
		if (!event.isRetry()) {
			remoteKeyService.keyAlarm(otaDto.getTboxId());
			eventService.end(event);
		}
	}

	@Override
	public List listUserKey(Long userId) throws ApiException {
		AssertRequired("userId", userId);
		List<RemoteKeyDto> remoteKeyDtos = new ArrayList<>();
		List<RemoteKey> remoteKeys = remoteKeyService.listUserKey(userId);
		RemoteKeyDtoAssembler assembler = new RemoteKeyDtoAssembler();
		UserDto userDto = null;
		for(RemoteKey remoteKey : remoteKeys) {
			if(null != remoteKey.getUserId()) {
				userDto = userApi.findUserById(remoteKey.getUserId());
			}
			remoteKeyDtos.add(assembler.toDto(remoteKey, userDto));
			userDto = null;
		}
		return remoteKeyDtos;
	}

	@Override
	public List listVehicleKey(Long ownerId, String vin) throws ApiException {
		AssertRequired("ownerId,vin", ownerId, vin);
		checkVinOwner(vin, ownerId);
		List<RemoteKeyDto> remoteKeyDtos = new ArrayList<>();
		List<RemoteKey> remoteKeys = remoteKeyService.listVehicleKey(vin);
		RemoteKeyDtoAssembler assembler = new RemoteKeyDtoAssembler();
		UserDto userDto = null;
		for(RemoteKey remoteKey : remoteKeys) {
			if(null != remoteKey.getUserId()) {
				userDto = userApi.findUserById(remoteKey.getUserId());
			}
			remoteKeyDtos.add(assembler.toDto(remoteKey, userDto));
			userDto = null;
		}
		return remoteKeyDtos;
	}
}