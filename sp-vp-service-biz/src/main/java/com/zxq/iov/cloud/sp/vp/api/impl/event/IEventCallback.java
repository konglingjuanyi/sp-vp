package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;

import java.util.Map;

/**
 * 安防 事件消息及回调接口
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17
 * modify date 2015-6-29 17:16
 * @version 0.9, 2015-6-29
 */
public interface IEventCallback {

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     * @return              事件实例ID
     */
    Long start(OtaDto otaDto);

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     * @return              结果对象
     */
    Long start(OtaDto otaDto, Map<String, Object> paramMap);

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     */
    void end(OtaDto otaDto);

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     */
    void end(OtaDto otaDto, Map<String, Object> paramMap);

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     * @param result        结果对象
     */
    void end(OtaDto otaDto, Object result);

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     * @param result        结果对象
     */
    void end(OtaDto otaDto, Map<String, Object> paramMap, Object result);

    /**
     * 异常事件
     * @param otaDto        OTA传输对象
     * @param errorCode     错误代码
     */
    void error(OtaDto otaDto, Integer errorCode);

    /**
     * 超时事件
     */
    void timeout();

    /**
     * 得到拥有者当前实例
     * @param owner         拥有者
     * @param code          代码
     * @return              步骤实例
     */
    StepInstance findInstance(String owner, String code);

}
