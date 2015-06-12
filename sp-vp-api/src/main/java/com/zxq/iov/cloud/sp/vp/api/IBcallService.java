package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;

import java.util.List;


/**
 * 安防服务 bCall接口
 * @author 叶荣杰
 * create date 2015-6-10 16:47
 * modify date 2015-6-11 11:45
 * @version 0.2, 2015-6-11
 */
public interface IBcallService {

    /**
     * 开始bCall
     * @param bcallDto              bCall传输对象
     * @param vehicleInfoDtos       车辆状态传输对象列表
     * @return                      bCall通话传输对象
     */
    BcallRecordDto startBcall(BcallDto bcallDto, List<VehicleInfoDto> vehicleInfoDtos);

    /**
     * 请求bCall状态
     * @param tboxId                TBOX ID
     */
    void requestBcallStatus(Long tboxId);

    /**
     * 更新bCall状态
     * @param bcallDto              bCall传输对象
     * @param vehicleInfoDtos       车辆状态传输对象列表
     */
    void updateBcall(BcallDto bcallDto, List<VehicleInfoDto> vehicleInfoDtos);

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
     * @param bcallRecordDto        呼叫记录传输对象
     */
    void responseCallBack(BcallRecordDto bcallRecordDto);

    /**
     * 请求结束bCall
     * @param tboxId                TBOX ID
     * @param callId                呼叫ID
     */
    void requestCloseBcall(Long tboxId, Long callId);

    /**
     * 结束bCall
     * @param bcallDto              呼叫传输对象
     */
    void closeBcall(BcallDto bcallDto);
}
