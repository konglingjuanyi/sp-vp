package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Map;

/**
 * 安防 事件消息及回调接口
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17
 * modify date 2015-6-10 9:39
 * @version 0.3, 2015-6-10
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
     * @param paramMap      参数MAP
     * @param result        结果对象
     */
    void end(OtaDto otaDto, Map<String, Object> paramMap, Object result);

}
