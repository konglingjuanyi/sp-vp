package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 车辆警告信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-5-13 17:37
 * modify date 2015-5-14 17:32
 * @version 0.2, 2015-5-14
 */
public class VehicleAlertDtoAssembler {

    public VehicleStatus fromDto(final VehicleAlertDto vehicleAlertDto) {
        return new VehicleStatus(vehicleAlertDto.getId(), vehicleAlertDto.getCode(),
                vehicleAlertDto.getName(), vehicleAlertDto.getValue(), vehicleAlertDto.getData(), 2);
    }

    public VehicleAlertDto toDto(final VehicleStatus vehicleStatus) {
        return new VehicleAlertDto(vehicleStatus.getId(), vehicleStatus.getCode(),
                vehicleStatus.getName(), vehicleStatus.getValue(), vehicleStatus.getData());
    }

    public List<VehicleAlertDto> toDtoList(final List<VehicleStatus> vehicleStatuses) {
        List<VehicleAlertDto> vehicleAlertDtos = new ArrayList<>();
        for(VehicleStatus vehicleStatus : vehicleStatuses) {
            vehicleAlertDtos.add(toDto(vehicleStatus));
        }
        return vehicleAlertDtos;
    }
}
