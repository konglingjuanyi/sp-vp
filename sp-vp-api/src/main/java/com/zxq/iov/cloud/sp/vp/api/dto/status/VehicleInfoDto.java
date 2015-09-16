/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-07-21       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.status;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.List;

/**
 * 安防服务 车辆状态快照传输对象
 */
public class VehicleInfoDto extends OtaDto {

    // 车辆位置
    private VehiclePosDto vehiclePosDto;
    // 车辆状态列表
    private List<VehicleStatusDto> vehicleStatusDtos;
    // 车辆报警列表
    private List<VehicleAlertDto> vehicleAlertDtos;

    public VehicleInfoDto() {}

    public VehiclePosDto getVehiclePosDto() {
        return vehiclePosDto;
    }

    public void setVehiclePosDto(VehiclePosDto vehiclePosDto) {
        this.vehiclePosDto = vehiclePosDto;
    }

    public List<VehicleStatusDto> getVehicleStatusDtos() {
        return vehicleStatusDtos;
    }

    public void setVehicleStatusDtos(List<VehicleStatusDto> vehicleStatusDtos) {
        this.vehicleStatusDtos = vehicleStatusDtos;
    }

    public List<VehicleAlertDto> getVehicleAlertDtos() {
        return vehicleAlertDtos;
    }

    public void setVehicleAlertDtos(List<VehicleAlertDto> vehicleAlertDtos) {
        this.vehicleAlertDtos = vehicleAlertDtos;
    }
}
