/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-10-20       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.key.RemoteKeyDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.key;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.UserDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 智能钥匙传输对象装配类
 */
public class RemoteKeyDtoAssembler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyDtoAssembler.class);

	public RemoteKeyDto toDto(final RemoteKey remoteKey) {
		return new RemoteKeyDto(remoteKey.getId(), remoteKey.getSecretKey(), remoteKey.getReference(),
				remoteKey.getValidStartTime(), remoteKey.getValidEndTime(), remoteKey.getPrivilege(),
				remoteKey.getIsEnable(), remoteKey.getVin());
	}

	public RemoteKeyDto toDto(final RemoteKey remoteKey, final UserDto userDto) {
		RemoteKeyDto remoteKeyDto = new RemoteKeyDto(remoteKey.getId(), remoteKey.getSecretKey(), remoteKey.getReference(),
				remoteKey.getValidStartTime(), remoteKey.getValidEndTime(), remoteKey.getPrivilege(),
				remoteKey.getIsEnable(), remoteKey.getVin());
		if(null != userDto) {
		   	remoteKeyDto.setRealName(userDto.getNickName());
			remoteKeyDto.setUserName(userDto.getName());
			remoteKeyDto.setMobile(userDto.getMobilePhone());
		}
		else {
			LOGGER.warn("用户ID:" + remoteKey.getUserId() + "未得到用户信息");
		}
		return remoteKeyDto;
	}

	public RemoteKey fromDto(final RemoteKeyDto remoteKeyDto) {
		return new RemoteKey();
	}

	public List<RemoteKeyDto> toDtoList(final List<RemoteKey> remoteKeys) {
		List<RemoteKeyDto> remoteKeyDtos = new ArrayList<>();
		for (RemoteKey remoteKey : remoteKeys) {
			remoteKeyDtos.add(toDto(remoteKey));
		}
		return remoteKeyDtos;
	}
}
