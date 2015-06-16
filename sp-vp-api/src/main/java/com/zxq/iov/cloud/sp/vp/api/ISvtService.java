package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ImmoDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.KeyAuthDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;

import java.util.List;


/**
 * 安防服务 被盗追踪接口
 * @author 叶荣杰
 * create date 2015-6-15 11:47
 * modify date 2015-6-16 11:07
 * @version 0.2, 2015-6-16
 */
public interface ISvtService {

    /**
     * 报警
     * @param stolenAlarmDtos       被盗警报传输对象列表
     * @param vehicleInfoDtos       车辆状态传输对象列表
     */
    void alarm(List<StolenAlarmDto> stolenAlarmDtos, List<VehicleInfoDto> vehicleInfoDtos);

    /**
     * 更新追踪点
     * @param vehicleInfoDto        车辆状态传输对象
     */
    void updateTrack(VehicleInfoDto vehicleInfoDto);

    /**
     * 请求手动修改追踪设置
     * @param tboxId                TBOX ID
     * @param trackInterval         追踪点间隔时间（秒）
     * @param tracks                追踪点数量
     */
    void requestTrackSetting(Long tboxId, Integer trackInterval, Integer tracks);

    /**
     * 请求单次跟踪
     * @param tboxId                TBOX ID
     */
    void requestSingleTrack(Long tboxId);

    /**
     * 请求关闭警报
     * @param tboxId                TBOX ID
     */
    void requestCloseAlarm(Long tboxId);

    /**
     * 响应警报关闭状态请求
     * @param allAlarmClosed        所有警报是否关闭
     * @param stolenAlarmDtos       被盗警报传输对象列表
     * @param vehicleInfoDtos       车辆状态传输对象列表
     */
    void responseCloseAlarm(Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos,
                            List<VehicleInfoDto> vehicleInfoDtos);

    /**
     * 请求验证钥匙
     * @param tboxId                TBOX ID
     * @param keyId                 钥匙ID
     */
    void requestAuthKey(Long tboxId, Long keyId);

    /**
     * 响应钥匙验证请求
     * @param keyAuthDto            钥匙验证传输对象
     */
    void responseAuthKey(KeyAuthDto keyAuthDto);

    /**
     * 请求阻止车辆启动
     * @param tboxId                TBOX ID
     * @param requestStatus         请求状态
     */
    void requestImmobilise(Long tboxId, Integer requestStatus);

    /**
     * 响应阻止车辆启动请求
     * @param immoDto               阻止车辆启动传输对象
     */
    void responseImmobilise(ImmoDto immoDto);

    /**
     * 请求修改保护策略
     * @param tboxId                TBOX ID
     */
    void requestUpdateProtectStrategy(Long tboxId);

    /**
     * 响应修改保护策略请求
     */
    void responseUpdateProtectStrategy();

    /**
     * 请求触发报警
     * @param tboxId                TBOX ID
     */
    void requestAlarm(Long tboxId);
}
