package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.*;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程配置代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 15:19
 * modify date 2015-7-24 11:07
 * @version 0.6, 2015-7-24
 */
@Service
@Qualifier("tboxConfigServiceProxy")
public class TboxConfigServiceProxy extends BaseProxy implements ITboxConfigService {

    @Autowired
    @Qualifier("tboxConfigService")
    private ITboxConfigService tboxConfigService;
    @Autowired
    @Qualifier("eventImpl")
    private IEvent event;

    @Override
    public void requestConfigUpdate(String vin) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_CONFIGURATION, 3);
        event.start(otaDto);
        tboxConfigService.requestConfigUpdate(vin);
        sendQueue(otaDto);
        event.end(otaDto);
    }

    @Override
    public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) throws Exception {
        event.start(otaDto);
        tboxConfigService.responseConfigUpdate(otaDto, isAccepted);
        event.end(otaDto);
    }

    @Override
    public TboxConfigDto checkConfigDelta(OtaDto otaDto, byte[] mcuVersion, byte[] mpuVersion, String vin,
                                          String iccid, byte[] configVersion,
                                          Integer configDelta) throws Exception {
        event.start(otaDto);
        TboxConfigDto tboxConfigDto = tboxConfigService.checkConfigDelta(otaDto, mcuVersion, mpuVersion,
                vin, iccid, configVersion, configDelta);
        event.end(otaDto, tboxConfigDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        tboxConfigDto.setEventId(otaDto.getEventId());
        tboxConfigDto.setAid(otaDto.getAid());
        tboxConfigDto.setMid(otaDto.getMid());
        return tboxConfigDto;
    }

    @Override
    public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) throws Exception {
        event.start(otaDto);
        TboxConfigPackageDto tboxConfigPackageDto = tboxConfigService.getConfigPackage(otaDto, packageId);
        event.end(otaDto, tboxConfigPackageDto);
        otaDto.setMid(7);
        event.start(otaDto);
        event.end(otaDto);
        tboxConfigPackageDto.setEventId(otaDto.getEventId());
        tboxConfigPackageDto.setAid(otaDto.getAid());
        tboxConfigPackageDto.setMid(otaDto.getMid());
        return tboxConfigPackageDto;
    }

    @Override
    public void closeConfigUpdate(OtaDto otaDto, Boolean result, byte[] mcuVersion, byte[] mpuVersion,
                                  byte[] configVersion, Integer configDelta) throws Exception {
        event.start(otaDto);
        tboxConfigService.closeConfigUpdate(otaDto, result, mcuVersion, mpuVersion, configVersion, configDelta);
        event.end(otaDto);
    }

    @Override
    public void requestReadConfig(String vin, Long[] tboxConfigsettingIds) throws Exception {
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_CONFIGURATION, 8);
        event.start(otaDto);
        tboxConfigService.requestReadConfig(vin, tboxConfigsettingIds);
        sendQueue(otaDto, new ReadConfigReqDto(tboxConfigsettingIds));
        event.end(otaDto);
    }

    @Override
    public void responseReadConfig(OtaDto otaDto, List<TboxConfigSettingDto> tboxConfigSettingDtos)
            throws Exception {
        event.start(otaDto);
        tboxConfigService.responseReadConfig(otaDto, tboxConfigSettingDtos);
        event.end(otaDto);
    }

    @Override
    public KeyDto generateAsymmetricKey(OtaDto otaDto) throws Exception {
        event.start(otaDto);
        KeyDto keyDto = tboxConfigService.generateAsymmetricKey(otaDto);
        event.end(otaDto, keyDto);
        otaDto.setMid(11);
        event.start(otaDto);
        event.end(otaDto);
        keyDto.setEventId(otaDto.getEventId());
        keyDto.setAid(otaDto.getAid());
        keyDto.setMid(otaDto.getMid());
        return keyDto;
    }

    @Override
    public KeyDto bindTboxWithSecretKey(OtaDto otaDto, byte[] secretKeyWithEnc,
                                        byte[] tboxSnWithEnc) throws Exception {
        event.start(otaDto);
        KeyDto keyDto = tboxConfigService.bindTboxWithSecretKey(otaDto, secretKeyWithEnc, tboxSnWithEnc);
        event.end(otaDto, keyDto);
        otaDto.setMid(13);
        event.start(otaDto);
        event.end(otaDto);
        keyDto.setEventId(otaDto.getEventId());
        keyDto.setAid(otaDto.getAid());
        keyDto.setMid(otaDto.getMid());
        return keyDto;
    }
}
