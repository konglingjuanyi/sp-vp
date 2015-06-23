package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;

/**
 * 安防服务 远程配置接口
 * @author 叶荣杰
 * create date 2015-4-22 11:19
 * modify date 2015-6-23 9:32
 * @version 0.3, 2015-6-23
 */
public interface ITboxConfigService {

    /**
     * 请求TBOX检查配置更新
     * @param tboxId                TBOX ID
     */
    void requestConfigUpdate(Long tboxId);

    /**
     * TBOX响应是否接受后台的配置更新请求
     * @param otaDto                OTA传输对象
     * @param isAccepted            是否接受
     */
    void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted);

    /**
     * 检查TBOX配置更新版本，是否需要更新
     * @param mcuVersion            MCU版本
     * @param mpuVersion            MPU版本
     * @param configVersion         配置版本
     * @param configDelta           TBOX上配置更新版本
     * @param iccid                 ICCID
     * @param vin                   车辆VIN
     * @param otaDto                OTA传输对象
     * @return                      配置信息
     */
    TboxConfigDto checkConfigDelta(Integer mcuVersion, Integer mpuVersion, Integer configVersion,
                                   Integer configDelta, String iccid, String vin, OtaDto otaDto);

    /**
     * 获得配置更新包
     * @param otaDto                OTA传输对象
     * @param packageId             包ID
     * @return                      配置更新包
     */
    TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId);

    /**
     * TBOX配置更新结束
     * @param otaDto                OTA传输对象
     * @param result                TBOX更新是否成功
     * @param mcuVersion            MCU版本
     * @param mpuVersion            MPU版本
     * @param configVersion         配置版本
     * @param configDelta           TBOX上配置更新版本
     */
    void closeConfigUpdate(OtaDto otaDto, Boolean result, Integer mcuVersion, Integer mpuVersion,
                           Integer configVersion, Integer configDelta);

    /**
     * 请求读取TBOX配置参数
     * @param tboxId                TBOX ID
     * @param tboxConfigsettingIds  TBOX配置参数ID列表
     */
    void requestReadConfig(Long tboxId, Long[] tboxConfigsettingIds);

    /**
     * 响应读取TBOX配置参数请求
     * @param otaDto                OTA传输对象
     * @param tboxConfigSettings    TBOX配置参数
     */
    void responseReadConfig(OtaDto otaDto, String tboxConfigSettings);

    /**
     * 生成非对称密钥，用以对TBOX密钥加解密
     * @param otaDto    OTA传输对象
     * @return          密钥传输对象
     */
    KeyDto generateAsymmetricKey(OtaDto otaDto);

    /**
     * 将TBOX密钥与TBOX ID绑定
     * @param keyDto    密钥传输对象（包含公钥加密过的TBOX密钥）
     * @return          密钥传输对象
     */
    KeyDto bindTboxWithSecretKey(KeyDto keyDto);

}
