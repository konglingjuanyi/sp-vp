package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;

import java.util.Map;

/**
 * 安防 事件消息及回调接口
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17
 * modify date 2015-6-15 14:00
 * @version 0.5, 2015-6-15
 */
public interface IEvent {

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     */
    void start(OtaDto otaDto);

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     * @param clazz         结果对象类
     * @return              结果对象
     */
    Object start(OtaDto otaDto, Class clazz);

    /**
     * 开始事件
     * @param otaDto        OTA传输对象
     * @param paramMap      参数MAP
     * @param clazz         结果对象类
     * @return              结果对象
     */
    Object start(OtaDto otaDto, Map<String, Object> paramMap, Class clazz);

    /**
     * 结束事件
     * @param otaDto        OTA传输对象
     */
    void end(OtaDto otaDto);

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
     * 得到拥有者当前实例
     * @param owner         拥有者
     * @param code          代码
     * @return              步骤实例
     */
    StepInstance findInstance(String owner, String code);

}
