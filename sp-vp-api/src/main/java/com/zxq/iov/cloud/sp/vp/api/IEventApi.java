package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.exception.ApiException;

/**
 * 安防 事件接口
 *
 * @author 叶荣杰
 * create date 2015-6-29 17:04
 * modify date 2015-8-7 16:09
 * @version 0.5, 2015-8-7
 */
public interface IEventApi {

    /**
     * Dispatch超时
     * @param stepInstanceId    步骤实例ID
     */
    void dispatchTimeout(Long stepInstanceId) throws ApiException;


    /**
     * Dispatch确认
     * @param tboxId            TBOX ID
     * @param stepInstanceId    步骤实例ID
     */
    void dispatchAck(Long tboxId, Long stepInstanceId) throws ApiException;

    /**
     * 检查超时步骤、任务及事件
     */
    void checkTimeout() throws ApiException;
}
