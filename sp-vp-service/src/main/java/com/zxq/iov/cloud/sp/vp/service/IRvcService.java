package com.zxq.iov.cloud.sp.vp.service;


import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;

import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程控制接口
 * @author 叶荣杰
 * create date 2015-6-17 11:18
 * modify date 2015-8-5 17:22
 * @version 0.11, 2015-8-5
 */
public interface IRvcService {

    /**
     * 请求控制
     * @param requestClient     请求终端
     * @param userId            用户ID
     * @param vin               车辆唯一码
     * @param command           命令代码
     * @param parameters        命令参数
     * @param eventId           事件ID
     * @return                  控制命令ID
     */
    ControlCommand requestControl(String requestClient, Long userId, String vin, String command,
                        Map<String, Object> parameters, Long eventId) throws ServLayerException;

    /**
     * 取消控制
     * @param requestClient     请求终端
     * @param userId            用户ID
     * @param vin               车辆唯一码
     * @param command           命令代码
     */
    void cancelControl(String requestClient, Long userId, String vin,
                       String command) throws ServLayerException;

    /**
     * 更新控制请求状态
     * @param tboxId            TBOX ID
     * @param rvcStatus         控制状态
     * @param failureType       错误类型
     * @param vehiclePos        车辆位置对象
     * @param vehicleStatuses   车辆状态对象列表
     * @param eventId           事件ID
     */
    void updateControlStatus(Long tboxId, byte[] rvcStatus, Integer failureType, VehiclePos vehiclePos,
                             List<VehicleStatus> vehicleStatuses, Long eventId) throws ServLayerException;

    /**
     * 根据控制命令ID得到命令状态传输对象
     * @param controlCommandId  控制命令ID
     * @param vin               车辆唯一码
     * @param userId            用户ID
     * @return                  控制命令状态对象
     */
    ControlCommand getControlStatus(Long controlCommandId, String vin, Long userId) throws ServLayerException;

}
