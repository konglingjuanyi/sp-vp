package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 车辆位置信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-5-14 11:37
 * modify date 2015-8-4 17:12
 * @version 0.4, 2015-8-4
 */
public class VehiclePosDtoAssembler {

    public VehiclePos fromDto(final VehiclePosDto vehiclePosDto) {
        return new VehiclePos(vehiclePosDto.getLongitude(), vehiclePosDto.getLatitude(),
                vehiclePosDto.getAltitude(), vehiclePosDto.getHeading(), vehiclePosDto.getSpeed(),
                vehiclePosDto.getHdop(), vehiclePosDto.getSatellites(), vehiclePosDto.getGpsTime(),
                vehiclePosDto.getGpsStatus());
    }

    public List<VehiclePos> fromDtoList(final List<VehiclePosDto> vehiclePosDtos) {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        for(VehiclePosDto vehiclePosDto : vehiclePosDtos) {
            vehiclePoses.add(fromDto(vehiclePosDto));
        }
        return vehiclePoses;
    }

    public VehiclePosDto toDto(final VehiclePos vehiclePos) {
        return new VehiclePosDto(vehiclePos.getLongitude(), vehiclePos.getLatitude(),
                vehiclePos.getAltitude(), vehiclePos.getHeading(), vehiclePos.getSpeed(),
                vehiclePos.getHdop(), vehiclePos.getSatellites(), vehiclePos.getGpsTime(),
                vehiclePos.getGpsStatus());
    }

}
