package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 车辆状态传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-5-13 17:30
 * modify date 2015-5-25 11:20
 * @version 0.3, 2015-5-25
 */
public class VehicleStatusDtoAssembler {

    public VehicleStatus fromDto(final VehicleStatusDto vehicleStatusDto) {
        return new VehicleStatus(vehicleStatusDto.getCode(), vehicleStatusDto.getValue(), null, 1);
    }

    public VehicleStatusDto toDto(final VehicleStatus vehicleStatus) {
        return new VehicleStatusDto(vehicleStatus.getCode(), vehicleStatus.getValue());
    }

    public List<VehicleStatusDto> toDtoList(final List<VehicleStatus> vehicleStatuses) {
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        for(VehicleStatus vehicleStatus : vehicleStatuses) {
            vehicleStatusDtos.add(toDto(vehicleStatus));
        }
        return vehicleStatusDtos;
    }
}
