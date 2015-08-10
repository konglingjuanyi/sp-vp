package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleAlertDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;

import java.util.Date;
import java.util.List;

/**
 * 安防服务 车辆状态接口
 * @author 叶荣杰
 * create date 2015-5-13 13:48
 * modify date 2015-8-6 9:35
 * @version 0.9, 2015-8-6
 */
public interface IStatusApi {

    /**
     * 请求车辆状态信息
     * @param vin               车辆唯一码
     * @param statusType        请求车辆状态类别
     * @return                  事件ID
     */
    Long requestVehicleStatus(String vin, Integer statusType) throws Exception;

    /**
     * TBOX响应车辆状态请求
     * @param otaDto            OTA传输对象
     * @param statusTime        状态时间
     * @param vehiclePosDto     车辆位置传输对象
     * @param vehicleStatusDtos 车辆状态传输对象列表
     * @param vehicleAlertDtos  车辆报警传输对象列表
     */
    void responseVehicleStatus(OtaDto otaDto, Date statusTime, VehiclePosDto vehiclePosDto,
                             List<VehicleStatusDto> vehicleStatusDtos,
                             List<VehicleAlertDto> vehicleAlertDtos) throws Exception;

    /**
     * 得到车辆状态信息快照
     * 存在事件ID，则返回事件ID对应的状态快照，不存在则返回最新状态
     * @param vin               车辆唯一码
     * @param eventId           事件ID
     * @return                  车辆状态快照传输对象
     */
    VehicleInfoDto getVehicleStatus(String vin, Long eventId) throws Exception;

    /**
     * 记录车辆警告信息
     * @param otaDto            OTA传输对象
     * @param vehicleAlertDtos  车辆报警信息传输对象列表
     */
    void logVehicleAlert(OtaDto otaDto, List<VehicleAlertDto> vehicleAlertDtos) throws Exception;

}
