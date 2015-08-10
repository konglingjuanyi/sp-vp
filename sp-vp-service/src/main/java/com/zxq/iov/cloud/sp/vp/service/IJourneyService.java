package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.journey.Journey;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

import java.util.Date;


/**
 * 安防服务 车辆行程接口
 * @author 叶荣杰
 * create date 2015-6-9 13:00
 * modify date 2015-8-7 13:11
 * @version 0.6, 2015-8-7
 */
public interface IJourneyService {

    /**
     * 开始车辆行程
     * @param tboxId                TBOX ID
     * @param startTime             行程开始时间
     * @param tboxJourneyId         TBOX行程ID
     * @param keyId                 钥匙ID
     */
    void start(Long tboxId, Date startTime, Integer tboxJourneyId, Integer keyId)
            throws ServLayerException;

    /**
     * 更新车辆行程状态
     * @param tboxId                TBOX ID
     * @param tboxJourneyId         TBOX行程ID
     * @param instFuelConsumption   瞬时油耗
     * @param vehiclePos            车辆位置对象
     */
    void update(Long tboxId, Integer tboxJourneyId, Integer instFuelConsumption,
                       VehiclePos vehiclePos) throws ServLayerException;

    /**
     * 结束车辆行程
     * @param tboxId                TBOX ID
     * @param startVehiclePos       开始车辆位置对象
     * @param endVehiclePos         结束车辆位置对象
     * @param tboxJourneyId         TBOX行程ID
     * @param distance              行驶距离
     * @param avgSpeed              平均速度
     * @param fuelEco               平均油耗
     * @param odometer              流程表数据
     * @param fuelLevelPrc          燃油剩余量
     * @param fuelLevelDisp         剩余燃油刻度
     * @param fuelRange             剩余燃油可行驶距离
     */
    void end(Long tboxId, VehiclePos startVehiclePos, VehiclePos endVehiclePos,
                    Integer tboxJourneyId, Integer distance, Integer avgSpeed, Integer fuelEco,
                    Integer odometer, Integer fuelLevelPrc, Integer fuelLevelDisp, Integer fuelRange)
            throws ServLayerException;

    /**
     * 根据TBOX ID和TBOX行程ID得到行程对象
     * @param tboxJourneyId         TBOX行程ID
     * @param tboxId                TBOX ID
     * @return                      行程对象
     * @throws ServLayerException
     */
    Journey getByIdAndTboxJourneyId(Integer tboxJourneyId, Long tboxId) throws ServLayerException;

}
