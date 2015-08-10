package com.zxq.iov.cloud.sp.vp.service;


import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;

import java.util.List;

/**
 * 安防服务 远程配置接口
 * @author 叶荣杰
 * create date 2015-4-22 11:19
 * modify date 2015-8-6 10:49
 * @version 0.7, 2015-8-6
 */
public interface ITboxConfigService {

    /**
     * 检查TBOX配置更新版本，是否需要更新
     * @param tboxId                TBOX ID
     * @param mcuVersion            MCU版本
     * @param mpuVersion            MPU版本
     * @param vin                   车辆VIN
     * @param iccid                 ICCID
     * @param configVersion         配置版本
     * @param configDelta           TBOX上配置更新版本
     * @param eventId               事件ID
     * @return                      最新配置更新版本
     */
    Integer checkConfigDelta(Long tboxId, byte[] mcuVersion, byte[] mpuVersion, String vin,
                                   String iccid, byte[] configVersion,
                                   Integer configDelta, Long eventId) throws Exception;

    /**
     * 获得配置更新包
     * @param tboxId                TBOX ID
     * @param packageId             包ID
     * @param eventId               事件ID
     * @return                      配置参数对象列表
     */
    List<TboxConfigSetting> getConfigPackage(Long tboxId, Integer packageId,
                                             Long eventId) throws Exception;

    /**
     * 生成非对称密钥，用以对TBOX密钥加解密
     * @param tboxId                TBOX ID
     * @return                      密钥传输对象
     */
    String generateAsymmetricKey(Long tboxId) throws Exception;

    /**
     * 将TBOX密钥与TBOX ID绑定
     * @param tboxId                TBOX ID
     * @param secretKeyWithEnc      加密过的TBOX密钥
     * @param tboxSnWithEnc         加密过的TBOX序列号
     * @return                      密钥传输对象
     */
    void bindTboxWithSecretKey(Long tboxId, byte[] secretKeyWithEnc,
                                 byte[] tboxSnWithEnc) throws Exception;

}
