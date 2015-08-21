package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.event.StepInstance;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;

/**
 * 安防 事件消息及回调接口
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17
 * modify date 2015-8-11 9:53
 * @version 0.13, 2015-8-11
 */
public interface IEventService {

    /**
     * 开始事件
     * @param event             事件对象
     * @return                  事件对象
     */
    Event start(Event event) throws ServLayerException;

    /**
     * 结束事件
     * @param event             事件对象
     */
    void end(Event event) throws ServLayerException;

    /**
     * 异常事件
     * @param owner             事件拥有者
     * @param code              触发代码
     * @param errorCode         错误代码
     * @param eventId           事件ID
     */
    void error(String owner, String code, Integer errorCode, Long eventId) throws ServLayerException;

    /**
     * 得到拥有者当前实例
     * @param owner             拥有者
     * @param code              代码
     * @return                  步骤实例
     */
    StepInstance findInstance(String owner, String code) throws ServLayerException;

    /**
     * Dispatch超时
     * @param stepInstanceId    步骤实例ID
     */
    void dispatchTimeout(Long stepInstanceId);


    /**
     * Dispatch确认
     * @param owner             事件拥有者
     * @param stepInstanceId    步骤实例ID
     */
    void dispatchAck(String owner, Long stepInstanceId) throws ServLayerException;

    /**
     * 检查超时步骤、任务及事件
     */
    void checkTimeout();

}