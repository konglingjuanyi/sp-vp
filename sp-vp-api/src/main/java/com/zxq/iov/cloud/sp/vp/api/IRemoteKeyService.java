package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

import java.util.Date;

/**
 * 安防服务 电子钥匙接口
 * @author 叶荣杰
 * create date 2015-6-23 10:48
 * modify date 2015-7-17 17:44
 * @version 0.3, 2015-7-17
 */
public interface IRemoteKeyService {

    /**
     * 请求写入钥匙
     * @param vin                   OTA传输对象
     * @param keyType               钥匙类型
     * @param keyValue              钥匙
     * @param keyReference          钥匙引用
     * @param keyValidityStartTime  有效开始时间
     * @param keyValidityEndTime    有效结束时间
     */
    void requestWriteKey(String vin, Integer keyType, byte[] keyValue, Integer keyReference,
                         Date keyValidityStartTime, Date keyValidityEndTime);

    /**
     * 响应写入钥匙请求
     * @param otaDto                OTA传输对象
     * @param writeSuccess          是否写入成功
     * @param writeFailureReason    写入失败原因
     */
    void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason);

    /**
     * 请求删除钥匙
     * @param vin                   OTA传输对象
     * @param keyReference          钥匙引用
     */
    void requestDeleteKey(String vin, Integer keyReference);

    /**
     * 响应删除钥匙请求
     * @param otaDto                OTA传输对象
     * @param deleteSuccess         是否删除成功
     * @param deleteFailureReason   删除失败原因
     */
    void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess, Integer deleteFailureReason);

    /**
     * 钥匙异常报警
     * @param otaDto    OTA传输对象
     */
    void keyAlarm(OtaDto otaDto);

}
