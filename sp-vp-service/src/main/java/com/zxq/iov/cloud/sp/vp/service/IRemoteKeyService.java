package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.service.domain.Ota;

import java.util.Date;

/**
 * 安防服务 电子钥匙接口
 * @author 叶荣杰
 * create date 2015-6-23 10:48
 * modify date 2015-8-5 17:04
 * @version 0.6, 2015-8-5
 */
public interface IRemoteKeyService {

    /**
     * 请求写入钥匙
     * @param vin                   车辆唯一码
     * @param keyType               钥匙类型
     * @param keyValue              钥匙
     * @param keyReference          钥匙引用
     * @param keyValidityStartTime  有效开始时间
     * @param keyValidityEndTime    有效结束时间
     */
    void requestWriteKey(String vin, Integer keyType, String keyValue, Integer keyReference,
                         Date keyValidityStartTime, Date keyValidityEndTime) throws ServLayerException;

    /**
     * 响应写入钥匙请求
     * @param tboxId                TBOX ID
     * @param writeSuccess          是否写入成功
     * @param writeFailureReason    写入失败原因
     */
    void responseWriteKey(Long tboxId, Boolean writeSuccess,
                          Integer writeFailureReason) throws ServLayerException;

    /**
     * 请求删除钥匙
     * @param vin                   OTA传输对象
     * @param keyReference          钥匙引用
     */
    void requestDeleteKey(String vin, Integer keyReference) throws ServLayerException;

    /**
     * 响应删除钥匙请求
     * @param tboxId                TBOX ID
     * @param deleteSuccess         是否删除成功
     * @param deleteFailureReason   删除失败原因
     */
    void responseDeleteKey(Long tboxId, Boolean deleteSuccess,
                           Integer deleteFailureReason) throws ServLayerException;

    /**
     * 钥匙异常报警
     * @param tboxId    OTA传输对象
     */
    void keyAlarm(Long tboxId) throws ServLayerException;

}
