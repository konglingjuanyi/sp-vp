package com.zxq.iov.cloud.sp.vp.api;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程控制接口
 * @author 叶荣杰
 * create date 2015-6-17 11:18
 * modify date 2015-7-30 10:29
 * @version 0.10, 2015-7-30
 */
public interface IRvcService {

    /**
     * 请求控制
     * @param requestClient     请求终端
     * @param userId            用户ID
     * @param vin               车辆唯一码
     * @param command           命令代码
     * @param parameters        命令参数
     * @return                  控制命令ID
     */
    Long requestControl(String requestClient, Long userId, String vin, String command,
                        Map<String, Object> parameters) throws Exception;

    /**
     * 取消控制
     * @param requestClient     请求终端
     * @param userId            用户ID
     * @param vin               车辆唯一码
     * @param command           命令代码
     */
    void cancelControl(String requestClient, Long userId, String vin,
                       String command) throws Exception;

    /**
     * 更新控制请求状态
     * @param otaDto            OTA传输对象
     * @param rvcStatus         控制状态
     * @param failureType       错误类型
     * @param vehiclePosDto     车辆位置传输对象
     * @param vehicleStatusDtos 车辆状态传输对象列表
     */
    void updateControlStatus(OtaDto otaDto, byte[] rvcStatus, Integer failureType,
                             VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos)
            throws Exception;

    /**
     * 根据控制命令ID得到命令状态传输对象
     * @param controlCommandId  控制命令ID
     * @param vin               车辆唯一码
     * @param userId            用户ID
     * @return                  控制命令状态传输对象
     */
    RvcStatusDto getControlStatus(Long controlCommandId, String vin, Long userId) throws Exception;

}
