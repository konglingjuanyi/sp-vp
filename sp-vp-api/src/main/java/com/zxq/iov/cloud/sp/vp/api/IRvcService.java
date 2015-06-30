package com.zxq.iov.cloud.sp.vp.api;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;

import java.util.List;

/**
 * 安防服务 远程控制接口
 * @author 叶荣杰
 * create date 2015-6-17 11:18
 * modify date 2015-6-29 9:55
 * @version 0.3, 2015-6-29
 */
public interface IRvcService {

    /**
     * 请求控制
     * @param vin               车辆唯一码
     * @param command           命令代码
     * @param parameter         命令参数
     * @return                  控制命令ID
     */
    Long requestControl(String vin, String command, String parameter);

    /**
     * 取消控制
     * @param vin               车辆唯一码
     * @param command           命令代码
     */
    void cancelControl(String vin, String command);

    /**
     * 更新控制请求状态
     * @param otaDto            OTA传输对象
     * @param rvcStatus         控制状态
     * @param failureType       错误类型
     * @param vehiclePosDto     车辆位置传输对象
     * @param vehicleStatusDtos 车辆状态传输对象列表
     */
    void updateControlStatus(OtaDto otaDto, String rvcStatus, Integer failureType,
                             VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos);

}
