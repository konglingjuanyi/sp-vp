package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.journey.JourneyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;


/**
 * 安防服务 车辆行程接口
 * @author 叶荣杰
 * create date 2015-6-9 13:00
 * @version 0.1, 2015-6-9
 */
public interface IJourneyService {

    /**
     * 开始车辆行程
     * @param journeyDto            行程传输对象
     */
    void startJourney(JourneyDto journeyDto);

    /**
     * 更新车辆行程状态
     * @param journeyDto            行程传输对象
     * @param vehicleInfoDto        车辆状态传输对象
     */
    void updateJourney(JourneyDto journeyDto, VehicleInfoDto vehicleInfoDto);

    /**
     * 结束车辆行程
     * @param journeyDto            行程传输对象
     * @param startVehicleInfoDto   开始状态传输对象
     * @param endVehicleInfoDto     结束状态传输对象
     */
    void endJourney(JourneyDto journeyDto, VehicleInfoDto startVehicleInfoDto, VehicleInfoDto endVehicleInfoDto);

}
