package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;

import java.util.List;


/**
 * 安防服务 eCall接口
 * @author 叶荣杰
 * create date 2015-6-12 11:03
 * modify date
 * @version 0.1, 2015-6-12
 */
public interface IEcallService {

    /**
     * 开始eCall
     * @param ecallDto              eCall传输对象
     * @param vehicleInfoDtos       车辆状态传输对象列表
     * @return                      eCall通话传输对象
     */
    EcallRecordDto startEcall(EcallDto ecallDto, List<VehicleInfoDto> vehicleInfoDtos);

    /**
     * 请求eCall状态
     * @param tboxId                TBOX ID
     */
    void requestEcallStatus(Long tboxId);

    /**
     * 更新eCall状态
     * @param ecallDto              eCall传输对象
     * @param vehicleInfoDtos       车辆状态传输对象列表
     */
    void updateEcall(EcallDto ecallDto, List<VehicleInfoDto> vehicleInfoDtos);

    /**
     * 请求挂断通话
     * @param tboxId                TBOX ID
     * @param callRecordId          呼叫记录ID
     */
    void requestHangUp(Long tboxId, Long callRecordId);

    /**
     * 请求车辆回拨
     * @param tboxId                TBOX ID
     * @param callId                呼叫ID
     * @param callNumber            呼叫号码
     */
    void requestCallBack(Long tboxId, Long callId, String callNumber);

    /**
     * 回应车辆回拨
     * @param ecallRecordDto        呼叫记录传输对象
     */
    void responseCallBack(EcallRecordDto ecallRecordDto);

    /**
     * 请求结束eCall
     * @param tboxId                TBOX ID
     * @param callId                呼叫ID
     */
    void requestCloseEcall(Long tboxId, Long callId);

    /**
     * 结束eCall
     * @param ecallDto              呼叫传输对象
     */
    void closeEcall(EcallDto ecallDto);
}
