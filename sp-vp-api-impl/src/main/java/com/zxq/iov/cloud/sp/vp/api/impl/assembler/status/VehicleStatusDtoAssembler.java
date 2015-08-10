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
 * modify date 2015-8-5 18:06
 * @version 0.4, 2015-8-5
 */
public class VehicleStatusDtoAssembler {

    public VehicleStatus fromDto(final VehicleStatusDto vehicleStatusDto) {
        return new VehicleStatus(vehicleStatusDto.getCode(), vehicleStatusDto.getValue(), null, 1);
    }

    public List<VehicleStatus> fromDtoList(final List<VehicleStatusDto> vehicleStatusDtos) {
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        for(VehicleStatusDto vehicleStatusDto : vehicleStatusDtos) {
            vehicleStatuses.add(fromDto(vehicleStatusDto));
        }
        return vehicleStatuses;
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
