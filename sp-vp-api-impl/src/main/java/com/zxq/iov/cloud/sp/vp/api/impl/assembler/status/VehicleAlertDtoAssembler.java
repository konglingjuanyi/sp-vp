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
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleAlertDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 车辆警告信息传输对象装配类
 */
public class VehicleAlertDtoAssembler {

	public VehicleStatus fromDto(final VehicleAlertDto vehicleAlertDto) {
		Integer value = vehicleAlertDto.isAlertStatus() ? 1 : 0;
		return new VehicleStatus(vehicleAlertDto.getAlertId().toString(), value,
				vehicleAlertDto.getAlertData().toString(), 1);
	}

	public List<VehicleStatus> fromDtoList(final List<VehicleAlertDto> vehicleAlertDtos) {
		List<VehicleStatus> vehicleStatuses = new ArrayList<>();
		if (null != vehicleAlertDtos) {
			for (VehicleAlertDto vehicleAlertDto : vehicleAlertDtos) {
				vehicleStatuses.add(fromDto(vehicleAlertDto));
			}
		}
		return vehicleStatuses;
	}

	public VehicleAlertDto toDto(final VehicleStatus vehicleStatus) {
		return new VehicleAlertDto(Integer.parseInt(vehicleStatus.getCode()), null, null,
				vehicleStatus.getStatus() == 1 ? true : false, Integer.parseInt(vehicleStatus.getData()));
	}

	public List<VehicleAlertDto> toDtoList(final List<VehicleStatus> vehicleStatuses) {
		List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
		for (VehicleStatus vehicleStatus : vehicleStatuses) {
			vehicleAlertDtos.add(toDto(vehicleStatus));
		}
		return vehicleAlertDtos;
	}
}
