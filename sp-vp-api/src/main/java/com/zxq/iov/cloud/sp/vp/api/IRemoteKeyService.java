package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;

/**
 * 安防服务 电子钥匙接口
 * @author 叶荣杰
 * create date 2015-6-23 10:48
 * modify date
 * @version 0.1, 2015-6-23
 */
public interface IRemoteKeyService {

    /**
     * 请求写入钥匙
     * @param remoteKeyDto          电子钥匙传输对象
     */
    void requestWriteKey(RemoteKeyDto remoteKeyDto);

    /**
     * 响应写入钥匙请求
     * @param otaDto                OTA传输对象
     * @param writeSuccess          是否写入成功
     * @param writeFailureReason    写入失败原因
     */
    void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason);

    /**
     * 请求删除钥匙
     * @param tboxId                TBOX ID
     * @param keyReference          钥匙引用
     */
    void requestDeleteKey(Long tboxId, Integer keyReference);

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
