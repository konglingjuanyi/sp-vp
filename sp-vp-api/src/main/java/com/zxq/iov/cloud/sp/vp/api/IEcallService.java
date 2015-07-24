package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;

import java.util.List;


/**
 * 安防服务 eCall接口
 * @author 叶荣杰
 * create date 2015-6-12 11:03
 * modify date 2015-7-24 11:02
 * @version 0.4, 2015-7-24
 */
public interface IEcallService {

    /**
     * 开始eCall
     * @param otaDto                OTA传输对象
     * @param vehiclePosDtos        车辆GPS位置传输对象列表
     * @param ecallType             呼叫方式
     * @param crashSeverity         严重程度
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @return                      bCall通话传输对象
     */
    EcallRecordDto startEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                              Integer crashSeverity, Integer tboxBatteryStatus,
                              Integer vehicleBatteryStatus) throws Exception;

    /**
     * 请求eCall状态
     * @param vin                   车辆唯一码
     */
    void requestEcallStatus(String vin) throws Exception;

    /**
     * 更新eCall状态
     * @param otaDto                OTA传输对象
     * @param vehiclePosDtos        车辆GPS位置传输对象列表
     * @param ecallType             呼叫方式
     * @param crashSeverity         严重程度
     * @param tboxBatteryStatus     TBOX电池状态
     * @param vehicleBatteryStatus  车辆电池状态
     * @return                      呼叫对象ID
     */
    Long updateEcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer ecallType,
                     Integer crashSeverity, Integer tboxBatteryStatus,
                     Integer vehicleBatteryStatus) throws Exception;

    /**
     * 请求挂断通话
     * @param vin                   车辆唯一码
     */
    void requestHangUp(String vin) throws Exception;

    /**
     * 请求车辆回拨
     * @param vin                   车辆唯一码
     * @param callNumber            呼叫号码
     */
    void requestCallBack(String vin, String callNumber) throws Exception;

    /**
     * 响应车辆回拨请求
     * @param otaDto                OTA传输对象
     * @param callbackAccepted      是否接受回拨
     * @param rejectReason          拒绝理由
     */
    void responseCallBack(OtaDto otaDto, Boolean callbackAccepted,
                          Integer rejectReason) throws Exception;

    /**
     * 请求结束eCall
     * @param vin                   车辆唯一码
     */
    void requestCloseEcall(String vin) throws Exception;

    /**
     * 结束eCall
     * @param otaDto                OTA传输对象
     */
    void closeEcall(OtaDto otaDto) throws Exception;
}
