package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;

import java.util.List;


/**
 * 安防服务 被盗追踪接口
 * @author 叶荣杰
 * create date 2015-6-15 11:47
 * modify date 2015-8-6 9:52
 * @version 0.6, 2015-8-6
 */
public interface ISvtService {

    /**
     * 车辆报警
     * @param tboxId                        TBOX ID
     * @param stolenAlarm                   被盗警报对象
     * @param vehiclePos                    车辆位置对象
     * @param eventId                       事件ID
     */
    void alarm(Long tboxId, StolenAlarm stolenAlarm, VehiclePos vehiclePos,
               Long eventId) throws Exception;

    /**
     * 更新追踪点
     * @param tboxId                        TBOX ID
     * @param vehicleStatuses               车辆状态对象列表
     * @param vehiclePos                    车辆位置对象
     * @param eventId                       事件ID
     */
    void updateTrack(Long tboxId, List<VehicleStatus> vehicleStatuses,
                     VehiclePos vehiclePos, Long eventId) throws Exception;

}
