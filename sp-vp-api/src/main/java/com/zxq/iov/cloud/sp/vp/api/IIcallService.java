package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;

import java.util.List;


/**
 * 安防服务 iCall接口
 * @author 叶荣杰
 * create date 2015-6-12 15:11
 * modify date
 * @version 0.1, 2015-6-12
 */
public interface IIcallService {

    /**
     * 开始iCall
     * @param icallDto              iCall传输对象
     * @param vehicleInfoDtos       车辆状态传输对象列表
     * @return                      iCall通话传输对象
     */
    IcallRecordDto startIcall(IcallDto icallDto, List<VehicleInfoDto> vehicleInfoDtos);

    /**
     * 请求iCall状态
     * @param tboxId                TBOX ID
     */
    void requestIcallStatus(Long tboxId);

    /**
     * 更新iCall状态
     * @param icallDto              iCall传输对象
     * @param vehicleInfoDtos       车辆状态传输对象列表
     */
    void updateIcall(IcallDto icallDto, List<VehicleInfoDto> vehicleInfoDtos);

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
     * @param icallRecordDto        呼叫记录传输对象
     */
    void responseCallBack(IcallRecordDto icallRecordDto);

    /**
     * 请求结束iCall
     * @param tboxId                TBOX ID
     * @param callId                呼叫ID
     */
    void requestCloseIcall(Long tboxId, Long callId);

    /**
     * 结束iCall
     * @param icallDto              呼叫传输对象
     */
    void closeIcall(IcallDto icallDto);
}
