/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-13       荣杰         1.0            Initial Version
 * 2015-08-05       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 车辆状态传输对象装配类
 */
public class VehicleStatusDtoAssembler {

	public VehicleStatus fromDto(final VehicleStatusDto vehicleStatusDto) {
		return new VehicleStatus(vehicleStatusDto.getCode(), vehicleStatusDto.getValue(), null, 1);
	}

	public List<VehicleStatus> fromDtoList(final List<VehicleStatusDto> vehicleStatusDtos) {
		List<VehicleStatus> vehicleStatuses = new ArrayList<>();
		for (VehicleStatusDto vehicleStatusDto : vehicleStatusDtos) {
			vehicleStatuses.add(fromDto(vehicleStatusDto));
		}
		return vehicleStatuses;
	}

	public VehicleStatusDto toDto(final VehicleStatus vehicleStatus) {
		return new VehicleStatusDto(vehicleStatus.getCode(), vehicleStatus.getValue());
	}

	public List<VehicleStatusDto> toDtoList(final List<VehicleStatus> vehicleStatuses) {
		List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
		for (VehicleStatus vehicleStatus : vehicleStatuses) {
			vehicleStatusDtos.add(toDto(vehicleStatus));
		}
		return vehicleStatusDtos;
	}
}
