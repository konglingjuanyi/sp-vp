package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

/**
 * 安防 车辆位置信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-5-14 11:37
 * modify date 2015-5-25 10:58
 * @version 0.2, 2015-5-25
 */
public class VehiclePosDtoAssembler {

    public VehiclePos fromDto(final VehiclePosDto vehiclePosDto) {
        return new VehiclePos(vehiclePosDto.getLongitude(), vehiclePosDto.getLatitude(),
                vehiclePosDto.getAltitude(), vehiclePosDto.getHeading(), vehiclePosDto.getSpeed(),
                vehiclePosDto.getHdop(), vehiclePosDto.getSatellites(), vehiclePosDto.getGpsTime(),
                vehiclePosDto.getGpsStatus());
    }

}
