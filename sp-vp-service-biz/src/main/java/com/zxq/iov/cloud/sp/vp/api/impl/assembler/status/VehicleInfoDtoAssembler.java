package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

/**
 * 安防 车辆信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-5-13 16:58
 * modify date 2015-5-15 13:51
 * @version 0.3, 2015-5-15
 */
public class VehicleInfoDtoAssembler {

    public VehicleInfo fromDto(final VehicleInfoDto vehicleInfoDto) {
        return new VehicleInfo(vehicleInfoDto.getSourceType(), vehicleInfoDto.getEventId(),
                vehicleInfoDto.getStatusTime());
    }

    public VehicleInfoDto toDto(final VehicleInfo vehicleInfo) {
        return new VehicleInfoDto(vehicleInfo.getId(), vehicleInfo.getSourceType(),
                vehicleInfo.getStatusTime(), vehicleInfo.getOwnerId(), vehicleInfo.getUserId());
    }
}
