package com.zxq.iov.cloud.sp.vp.service;

import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;

import java.util.Date;
import java.util.List;


/**
 * 安防服务 eCall服务接口
 * @author 叶荣杰
 * create date 2015-6-12 11:03
 * modify date 2015-8-5 11:01
 * @version 0.5, 2015-8-5
 */
public interface IEcallService {

    /**
     * 开始eCall
     * @param tboxId                TBOX ID
     * @param vehiclePoses          车辆位置对象列表
     * @param ecallType             呼叫方式
     * @param crashSeverity         严重程度
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @param ecallTime             eCall发生时间
     * @return                      eCall通话对象
     */
    CallRecord start(Long tboxId, List<VehiclePos> vehiclePoses, Integer ecallType,
                              Integer crashSeverity, Integer tboxBatteryStatus,
                              Integer vehicleBatteryStatus, Date ecallTime) throws Exception;

    /**
     * 更新eCall状态
     * @param tboxId                TBOX ID
     * @param vehiclePoses          车辆位置对象列表
     * @param ecallType             呼叫方式
     * @param crashSeverity         严重程度
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @param ecallTime             eCall发生时间
     * @return                      呼叫对象
     */
    Call update(Long tboxId, List<VehiclePos> vehiclePoses, Integer ecallType,
                     Integer crashSeverity, Integer tboxBatteryStatus,
                     Integer vehicleBatteryStatus, Date ecallTime) throws Exception;

    /**
     * 挂断eCall通话
     * @param vin                   车辆唯一码
     */
    void hangUp(String vin) throws Exception;

    /**
     * 车辆回拨
     * @param vin                   车辆唯一码
     * @param callNumber            呼叫号码
     * @return                      eCall通话对象
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
     * 结束eCall
     * @param vin                   车辆唯一码
     */
    void close(String vin) throws Exception;

    /**
     * 结束eCall
     * @param tboxId                TBOX ID
     */
    void close(Long tboxId) throws Exception;
}
