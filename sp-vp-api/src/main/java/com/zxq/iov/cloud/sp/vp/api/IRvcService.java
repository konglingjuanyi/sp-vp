package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;

/**
 * 安防服务 远程控制接口
 * @author 叶荣杰
 * create date 2015-6-17 11:18
 * modify date 2015-6-18 9:43
 * @version 0.2, 2015-6-18
 */
public interface IRvcService {

    /**
     * 请求控制
     * @param tboxId            TBOX ID
     * @param command           命令代码
     * @param parameter         命令参数
     * @return                  控制命令ID
     */
    Long requestControl(Long tboxId, Integer command, String parameter);

    /**
     * 取消控制
     * @param controlCommandId  控制命令ID
     */
    void cancelControl(Long controlCommandId);

    /**
     * 响应控制请求
     * @param rvcStatus         命令状态
     * @param failureType       错误类型
     * @param vehicleInfoDto    车辆信息传输对象
     */
    void responseControl(Integer rvcStatus, Integer failureType, VehicleInfoDto vehicleInfoDto);

}
