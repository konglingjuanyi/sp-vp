package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;

import java.util.Date;


/**
 * 安防服务 车辆行程接口
 * @author 叶荣杰
 * create date 2015-6-9 13:00
 * modify date 2015-7-24 11:15
 * @version 0.4, 2015-7-24
 */
public interface IJourneyService {

    /**
     * 开始车辆行程
     * @param otaDto                OTA传输对象
     * @param startTime             行程开始时间
     * @param tboxJourneyId         TBOX行程ID
     * @param keyId                 钥匙ID
     */
    void startJourney(OtaDto otaDto, Date startTime, Integer tboxJourneyId, Integer keyId)
            throws Exception;

    /**
     * 更新车辆行程状态
     * @param otaDto                OTA传输对象
     * @param tboxJourneyId         TBOX行程ID
     * @param instFuelConsumption   瞬时油耗
     * @param vehiclePosDto         车辆位置传输对象
     */
    void updateJourney(OtaDto otaDto, Integer tboxJourneyId, Integer instFuelConsumption,
                       VehiclePosDto vehiclePosDto) throws Exception;

    /**
     * 结束车辆行程
     * @param otaDto                OTA传输对象
     * @param startVehiclePosDto    开始车辆位置传输对象
     * @param endVehiclePosDto      结束车辆位置传输对象
     * @param tboxJourneyId         TBOX行程ID
     * @param distance              行驶距离
     * @param avgSpeed              平均速度
     * @param fuelEco               平均油耗
     * @param odometer              流程表数据
     * @param fuelLevelPrc          燃油剩余量
     * @param fuelLevelDisp         剩余燃油刻度
     * @param fuelRange             剩余燃油可行驶距离
     */
    void endJourney(OtaDto otaDto, VehiclePosDto startVehiclePosDto, VehiclePosDto endVehiclePosDto,
                    Integer tboxJourneyId, Integer distance, Integer avgSpeed, Integer fuelEco,
                    Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp, Integer fuelRange)
            throws Exception;

}
