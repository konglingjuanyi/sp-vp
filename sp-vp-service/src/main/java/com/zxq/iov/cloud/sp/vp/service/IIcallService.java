package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

import java.util.Date;
import java.util.List;


/**
 * 安防服务 iCall接口
 * @author 叶荣杰
 * create date 2015-6-12 15:11
 * modify date 2015-8-5 13:45
 * @version 0.5, 2015-8-5
 */
public interface IIcallService {

    /**
     * 开始iCall
     * @param tboxId                TBOX ID
     * @param vehiclePoses          车辆位置对象列表
     * @param icallType             呼叫方式
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @param icallTime             iCall发生时间
     * @return                      iCall通话传输对象
     */
    CallRecord start(Long tboxId, List<VehiclePos> vehiclePoses, Integer icallType,
                              Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                     Date icallTime) throws Exception;

    /**
     * 更新iCall状态
     * @param tboxId                TBOX ID
     * @param vehiclePoses          车辆位置对象列表
     * @param icallType             呼叫方式
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @param icallTime             iCall发生时间
     * @return                      呼叫对象
     */
    Call update(Long tboxId, List<VehiclePos> vehiclePoses, Integer icallType,
                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus,
                Date icallTime) throws Exception;

    /**
     * 请求挂断通话
     * @param vin                   车辆唯一码
     */
    void hangUp(String vin) throws Exception;

    /**
     * 车辆回拨
     * @param vin                   车辆唯一码
     * @param callNumber            呼叫号码
     * @return                      iCall通话对象
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
     * 结束iCall
     * @param vin                   车辆唯一码
     */
    void close(String vin) throws Exception;

    /**
     * 结束iCall
     * @param tboxId                TBOX ID
     */
    void close(Long tboxId) throws Exception;
}
