package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.TboxConfigPackageDto;

/**
 * User: 荣杰
 * Date: 2015/4/22
 * Time: 11:19
 */
public interface ITboxConfigService {

    /**
     * 检查TBOX配置更新版本，是否需要更新
     * @param mcuVersion     MCU版本
     * @param mpuVersion     MPU版本
     * @param configVersion  配置版本
     * @param configDelta    TBOX上配置更新版本
     * @param vin            车辆ID
     * @return               配置信息
     */
    TboxConfigDto checkConfigDelta(String mcuVersion, String mpuVersion,
                                   String configVersion, String configDelta, String vin);

    /**
     * 获得配置更新包
     * @param eventId       事件ID
     * @param packageId     包ID
     * @param vin           车辆ID
     * @return              配置更新包
     */
    TboxConfigPackageDto getConfigPackage(Long eventId, Integer packageId, String vin);

    /**
     * TBOX配置更新结束
     * @param eventId       事件ID
     * @param result        TBOX更新是否成功
     * @param mcuVersion    MCU版本
     * @param mpuVersion    MPU版本
     * @param configVersion 配置版本
     * @param configDelta   TBOX上配置更新版本
     * @param vin           车辆ID
     */
    void closeConfigUpdate(Long eventId, Boolean result, String mcuVersion, String mpuVersion,
                           String configVersion, String configDelta, String vin);

}
