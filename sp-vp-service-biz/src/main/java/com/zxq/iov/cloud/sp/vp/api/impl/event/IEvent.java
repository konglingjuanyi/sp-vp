package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;

import java.util.Map;

/**
 * 安防 事件消息及回调接口
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17
 * modify date 2015-7-24 10:48
 * @version 0.10, 2015-7-24
 */
public interface IEvent {

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     */
    void start(OtaDto otaDto) throws ServLayerException;

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     */
    void start(OtaDto otaDto, Map<String, Object> paramMap) throws ServLayerException;

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     */
    void end(OtaDto otaDto) throws ServLayerException;

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     */
    void end(OtaDto otaDto, Map<String, Object> paramMap) throws ServLayerException;

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     * @param result        结果对象
     */
    void end(OtaDto otaDto, Object result) throws ServLayerException;

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     * @param result        结果对象
     */
    void end(OtaDto otaDto, Map<String, Object> paramMap, Object result) throws ServLayerException;

    /**
     * 异常事件
     * @param otaDto        OTA传输对象
     * @param errorCode     错误代码
     */
    void error(OtaDto otaDto, Integer errorCode) throws ServLayerException;

    /**
     * 得到拥有者当前实例
     * @param owner         拥有者
     * @param code          代码
     * @return              步骤实例
     */
    StepInstance findInstance(String owner, String code) throws ServLayerException;

}
