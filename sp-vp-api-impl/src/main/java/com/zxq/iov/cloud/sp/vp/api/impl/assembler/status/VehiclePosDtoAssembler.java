/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-14       荣杰         1.0            Initial Version
 * 2015-08-04       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler
 *
 * sp - sp-vp-api-impl
 */

package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防服务 车辆位置信息传输对象装配类
 */
public class VehiclePosDtoAssembler {

	public VehiclePos fromDto(final VehiclePosDto vehiclePosDto) {
		return new VehiclePos(vehiclePosDto.getLongitude(), vehiclePosDto.getLatitude(), vehiclePosDto.getAltitude(),
				vehiclePosDto.getHeading(), vehiclePosDto.getSpeed(), vehiclePosDto.getHdop(),
				vehiclePosDto.getSatellites(), vehiclePosDto.getGpsTime(), vehiclePosDto.getGpsStatus());
	}

	public List<VehiclePos> fromDtoList(final List<VehiclePosDto> vehiclePosDtos) {
		List<VehiclePos> vehiclePoses = new ArrayList<>();
		for (VehiclePosDto vehiclePosDto : vehiclePosDtos) {
			vehiclePoses.add(fromDto(vehiclePosDto));
		}
		return vehiclePoses;
	}

	public VehiclePosDto toDto(final VehiclePos vehiclePos) {
		return new VehiclePosDto(vehiclePos.getLongitude(), vehiclePos.getLatitude(), vehiclePos.getAltitude(),
				vehiclePos.getHeading(), vehiclePos.getSpeed(), vehiclePos.getHdop(), vehiclePos.getSatellites(),
				vehiclePos.getGpsTime(), vehiclePos.getGpsStatus());
	}

}
