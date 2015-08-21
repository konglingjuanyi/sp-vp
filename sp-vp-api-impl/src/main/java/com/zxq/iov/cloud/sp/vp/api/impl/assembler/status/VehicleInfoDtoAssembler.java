package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

/**
 * 安防 车辆状态快照信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-7-23 14:34
 * modify date 2015-8-13 9:37
 * @version 0.2, 2015-8-13
 */
public class VehicleInfoDtoAssembler {

    public VehicleInfoDto toDto(final VehicleInfo vehicleInfo) {
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        vehicleInfoDto.setVehiclePosDto(new VehiclePosDtoAssembler().toDto(vehicleInfo.getVehiclePos()));
        vehicleInfoDto.setVehicleStatusDtos(new VehicleStatusDtoAssembler().toDtoList(vehicleInfo.getVehicleStatuses()));
        vehicleInfoDto.setVehicleAlertDtos(new VehicleAlertDtoAssembler().toDtoList(vehicleInfo.getVehicleAlerts()));
        return vehicleInfoDto;
    }

}
