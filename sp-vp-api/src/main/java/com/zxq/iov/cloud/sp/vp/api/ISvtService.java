package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.ProtectStrategySettingDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.StolenAlarmDto;
import com.zxq.iov.cloud.sp.vp.api.dto.svt.TrackDto;

import java.util.Date;
import java.util.List;


/**
 * 安防服务 被盗追踪接口
 * @author 叶荣杰
 * create date 2015-6-15 11:47
 * modify date 2015-6-26 9:23
 * @version 0.4, 2015-6-26
 */
public interface ISvtService {

    /**
     * 报警
     * @param otaDto                        OTA传输对象
     * @param stolenAlarmDtos               被盗警报传输对象列表
     */
    void alarm(OtaDto otaDto, List<StolenAlarmDto> stolenAlarmDtos);

    /**
     * 更新追踪点
     * @param otaDto                        OTA传输对象
     * @param trackDtos                     追踪点传输对象列表
     */
    void updateTrack(OtaDto otaDto, List<TrackDto> trackDtos);

    /**
     * 请求手动修改追踪设置
     * @param vin                           车辆唯一码
     * @param trackInterval                 追踪点间隔时间（秒）
     * @param tracks                        追踪点数量
     */
    void requestTrackSetting(String vin, Integer trackInterval, Integer tracks);

    /**
     * 请求单次跟踪
     * @param vin                           车辆唯一码
     */
    void requestSingleTrack(String vin);

    /**
     * 请求关闭警报
     * @param vin                           车辆唯一码
     */
    void requestCloseAlarm(String vin);

    /**
     * 响应警报关闭状态请求
     * @param otaDto                        OTA传输对象
     * @param allAlarmClosed                所有警报是否关闭
     * @param stolenAlarmDtos               被盗警报传输对象列表
     */
    void responseCloseAlarm(OtaDto otaDto, Boolean allAlarmClosed, List<StolenAlarmDto> stolenAlarmDtos);

    /**
     * 请求验证钥匙
     * @param vin                           车辆唯一码
     * @param keyId                         钥匙ID
     */
    void requestAuthKey(String vin, Integer keyId);

    /**
     * 响应钥匙验证请求
     * @param otaDto                        OTA传输对象
     * @param keyIsAccepted                 钥匙是否接受
     * @param failureReason                 失败原因
     */
    void responseAuthKey(OtaDto otaDto, Boolean keyIsAccepted, Integer failureReason);

    /**
     * 请求更改车辆固定状态
     * @param vin                           车辆唯一码
     * @param immoStatus                    固定状态
     */
    void requestImmobilise(String vin, Integer immoStatus);

    /**
     * 响应更改车辆固定状态请求
     * @param otaDto                        OTA传输对象
     * @param immoStatus                    固定状态
     * @param failureReason                 失败原因
     */
    void responseImmobilise(OtaDto otaDto, Integer immoStatus, Integer failureReason);

    /**
     * 请求修改保护策略
     * @param vin                           车辆唯一码
     * @param startTime                     策略开始时间
     * @param endTime                       策略结束时间
     * @param protectStrategySettingDtos    策略配置列表
     */
    void requestUpdateProtectStrategy(String vin, Date startTime, Date endTime,
                                      List<ProtectStrategySettingDto> protectStrategySettingDtos);

    /**
     * 响应修改保护策略请求
     */
    void responseUpdateProtectStrategy();

    /**
     * 请求触发报警
     * @param vin                   车辆唯一码
     */
    void requestAlarm(String vin);
}
