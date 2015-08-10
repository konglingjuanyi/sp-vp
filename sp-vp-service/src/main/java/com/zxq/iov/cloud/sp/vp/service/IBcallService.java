package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.Date;
import java.util.List;


/**
 * 安防服务 bCall服务接口
 * @author 叶荣杰
 * create date 2015-6-10 16:47
 * modify date 2015-8-4 11:45
 * @version 0.7, 2015-8-4
 */
public interface IBcallService {

    /**
     * 开始bCall
     * @param tboxId                TBOX ID
     * @param vehiclePoses          最近车辆位置对象列表
     * @param bcallType             呼叫方式
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @param vehicleAlerts         车辆报警对象列表
     * @param bcallTime             bCall发生时间
     * @return                      bCall通话对象
     */
    CallRecord start(Long tboxId, List<VehiclePos> vehiclePoses, Integer bcallType,
                              Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                              List<VehicleStatus> vehicleAlerts, Date bcallTime) throws Exception;

    /**
     * 更新bCall状态
     * @param tboxId                TBOX ID
     * @param vehiclePoses          车辆位置对象列表
     * @param bcallType             呼叫方式
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @param vehicleAlerts         车辆报警对象列表
     * @param bcallTime             bCall发生时间
     * @return                      呼叫对象
     */
    Call update(Long tboxId, List<VehiclePos> vehiclePoses, Integer bcallType,
                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                     List<VehicleStatus> vehicleAlerts, Date bcallTime) throws Exception;

    /**
     * 挂断bCall通话
     * @param vin                   车辆唯一码
     */
    void hangUp(String vin) throws Exception;

    /**
     * 车辆回拨
     * @param vin                   车辆唯一码
     * @param callNumber            呼叫号码
     * @return                      bCall通话对象
     */
    CallRecord callBack(String vin, String callNumber) throws Exception;

    /**
     * 响应车辆回拨
     * @param tboxId                TBOX ID
     * @param callbackAccepted      是否接受回拨
     * @param rejectReason          拒绝理由
     */
    void responseCallBack(Long tboxId, Boolean callbackAccepted,
                          Integer rejectReason) throws Exception;

    /**
     * 结束bCall
     * @param vin                   车辆唯一码
     */
    void close(String vin) throws Exception;

    /**
     * 结束bCall
     * @param tboxId                TBOX ID
     */
    void close(Long tboxId) throws Exception;
}
