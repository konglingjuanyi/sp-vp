package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;

/**
 * 安防服务 车辆状态接口
 * @author 叶荣杰
 * create time 2015-5-13 13:48
 * @version 0.1, 2015-5-13
 */
public interface IStatusService {

    /**
     * 请求车辆状态信息
     * @param vehicleInfoDto    车辆信息传输对象
     * @param statusType        请求车辆状态类别
     */
    void requestVehicleStatus(VehicleInfoDto vehicleInfoDto, Integer statusType);

    /**
     * 更新车辆状态
     * @param vehicleInfoDto    车辆信息传输对象
     */
    void updateVehicleStatus(VehicleInfoDto vehicleInfoDto);

    /**
     * 获得车辆状态信息
     * @param vehicleInfoDto    车辆信息传输对象
     * @return                  车辆信息传输对象
     */
    VehicleInfoDto getVehicleStatus(VehicleInfoDto vehicleInfoDto);

    /**
     * 记录车辆警告信息
     * @param vehicleInfoDto    车辆信息传输对象
     */
    void logVehicleAlert(VehicleInfoDto vehicleInfoDto);

}
