package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;

/**
 * 安防 车辆状态快照信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-7-23 14:34
 * modify date
 * @version 0.1, 2015-7-23
 */
public class VehicleInfoDtoAssembler {

    public VehicleInfoDto toDto(final VehicleInfo vehicleInfo) {
        return new VehicleInfoDto();
    }

}
