/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-23       荣杰         1.0            Initial Version
 * 2015-08-13       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleInfoDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

/**
 * 安防服务 车辆状态快照信息传输对象装配类
 */
public class VehicleInfoDtoAssembler {

	public VehicleInfoDto toDto(final VehicleInfo vehicleInfo) {
		if(null == vehicleInfo) {
			return null;
		}
		VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
		vehicleInfoDto.setVehiclePosDto(new VehiclePosDtoAssembler().toDto(vehicleInfo.getVehiclePos()));
		vehicleInfoDto
				.setVehicleStatusDtos(new VehicleStatusDtoAssembler().toDtoList(vehicleInfo.getVehicleStatuses()));
		vehicleInfoDto.setVehicleAlertDtos(new VehicleAlertDtoAssembler().toDtoList(vehicleInfo.getVehicleAlerts()));
		return vehicleInfoDto;
	}

}
