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
 * modify date 2015-5-14 14:25
 * @version 0.2, 2015-5-14
 */
public class VehicleStatusDtoAssembler {

    public VehicleStatus fromDto(final VehicleStatusDto vehicleStatusDto) {
        VehicleStatus vehicleStatus = new VehicleStatus();
        vehicleStatus.setCode(vehicleStatusDto.getCode());
        vehicleStatus.setValue(vehicleStatusDto.getValue());
        return new VehicleStatus(vehicleStatusDto.getId(), vehicleStatusDto.getCode(),
                vehicleStatusDto.getName(), vehicleStatusDto.getValue(), null, 1);
    }

    public VehicleStatusDto toDto(final VehicleStatus vehicleStatus) {
        return new VehicleStatusDto(vehicleStatus.getId(), vehicleStatus.getCode(),
                vehicleStatus.getName(), vehicleStatus.getValue());
    }

    public List<VehicleStatusDto> toDtoList(final List<VehicleStatus> vehicleStatuses) {
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        for(VehicleStatus vehicleStatus : vehicleStatuses) {
            vehicleStatusDtos.add(toDto(vehicleStatus));
        }
        return vehicleStatusDtos;
    }
}
