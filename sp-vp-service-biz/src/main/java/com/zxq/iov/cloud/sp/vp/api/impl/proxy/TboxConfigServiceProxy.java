package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 远程配置代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 15:19
 * modify date 2015-6-23 9:58
 * @version 0.2, 2015-6-23
 */
@Service
@Qualifier("tboxConfigServiceProxy")
public class TboxConfigServiceProxy implements ITboxConfigService {

    @Autowired
    @Qualifier("tboxConfigService")
    private ITboxConfigService tboxConfigService;
    @Autowired
    @Qualifier("eventImpl")
    private IEvent event;

    @Override
    public void requestConfigUpdate(Long tboxId) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 3);
        event.start(otaDto);
        tboxConfigService.requestConfigUpdate(tboxId);
        event.end(otaDto);
    }

    @Override
    public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) {
        event.start(otaDto);
        tboxConfigService.responseConfigUpdate(otaDto, isAccepted);
        event.end(otaDto);
    }

    @Override
    public TboxConfigDto checkConfigDelta(Integer mcuVersion, Integer mpuVersion, Integer configVersion,
                                          Integer configDelta, String iccid, String vin, OtaDto otaDto) {
        event.start(otaDto);
        TboxConfigDto tboxConfigDto = tboxConfigService.checkConfigDelta(mcuVersion, mpuVersion, configVersion, configDelta, iccid, vin, otaDto);
        event.end(otaDto);
        OtaDto otaDto2 = new OtaDto(otaDto.getTboxId(), Constants.AID_CONFIGURATION, 2);
        event.start(otaDto2);
        event.end(otaDto2);
        return tboxConfigDto;
    }

    @Override
    public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) {
        event.start(otaDto);
        TboxConfigPackageDto tboxConfigPackageDto = tboxConfigService.getConfigPackage(otaDto, packageId);
        event.end(otaDto);
        OtaDto otaDto2 = new OtaDto(otaDto.getTboxId(), Constants.AID_CONFIGURATION, 7);
        event.start(otaDto2);
        event.end(otaDto2);
        return tboxConfigPackageDto;
    }

    @Override
    public void closeConfigUpdate(OtaDto otaDto, Boolean result, Integer mcuVersion, Integer mpuVersion, Integer configVersion, Integer configDelta) {
        event.start(otaDto);
        tboxConfigService.closeConfigUpdate(otaDto, result, mcuVersion, mpuVersion, configVersion, configDelta);
        event.end(otaDto);
    }

    @Override
    public void requestReadConfig(Long tboxId, Long[] tboxConfigsettingIds) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_CONFIGURATION, 8);
        event.start(otaDto);
        tboxConfigService.requestReadConfig(tboxId, tboxConfigsettingIds);
        event.end(otaDto);
    }

    @Override
    public void responseReadConfig(OtaDto otaDto, String tboxConfigSettings) {
        event.start(otaDto);
        tboxConfigService.responseReadConfig(otaDto, tboxConfigSettings);
        event.end(otaDto);
    }

    @Override
    public KeyDto generateAsymmetricKey(OtaDto otaDto) {
        event.start(otaDto);
        KeyDto keyDto = tboxConfigService.generateAsymmetricKey(otaDto);
        event.end(otaDto);
        OtaDto otaDto2 = new OtaDto(otaDto.getTboxId(), Constants.AID_CONFIGURATION, 11);
        event.start(otaDto2);
        event.end(otaDto2);
        return keyDto;
    }

    @Override
    public KeyDto bindTboxWithSecretKey(KeyDto keyDto) {
        event.start(keyDto);
        keyDto = tboxConfigService.bindTboxWithSecretKey(keyDto);
        event.end(keyDto);
        OtaDto otaDto2 = new OtaDto(keyDto.getTboxId(), Constants.AID_CONFIGURATION, 13);
        event.start(otaDto2);
        event.end(otaDto2);
        return keyDto;
    }
}
