package com.zxq.iov.cloud.sp.vp.api.impl.assembler.status;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

/**
 * 安防 车辆警告信息传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-5-13 17:37
 * modify date 2015-5-25 11:21
 * @version 0.3, 2015-5-25
 */
public class VehicleAlertDtoAssembler {

    public VehicleStatus fromDto(final VehicleAlertDto vehicleAlertDto) {
        Integer value = (vehicleAlertDto.isAlertStatus())?1:0;
        return new VehicleStatus(vehicleAlertDto.getAlertId().toString(), value,
                vehicleAlertDto.getAlertData().toString(), 1);
    }

}
