package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.ITboxConfigApi;
import com.zxq.iov.cloud.sp.vp.api.dto.config.*;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.ITboxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 远程配置服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 11:44
 * modify date 2015-8-11 10:44
 * @version 0.9, 2015-8-11
 */
@Service
public class TboxConfigApiImpl extends BaseApi implements ITboxConfigApi {

    @Autowired
    private ITboxConfigService tboxConfigService;
    @Autowired
    private IEventService eventService;

    @Override
    public void requestConfigUpdate(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_CONFIGURATION, 3);
        Long eventId = eventService.start(vin, Constants.AID_CONFIGURATION + "3", null);
        otaDto.setEventId(eventId);
        sendQueue(otaDto);
        eventService.end(vin, Constants.AID_CONFIGURATION + "3", eventId);
    }

    @Override
    public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) throws ServLayerException {
        AssertRequired("isAccepted", isAccepted);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public TboxConfigDto checkConfigDelta(OtaDto otaDto, byte[] mcuVersion, byte[] mpuVersion, String vin,
                                          String iccid, byte[] configVersion, Integer configDelta)
            throws ServLayerException {
        AssertRequired("mcuVersion,mpuVersion,vin,iccid,configVersion,configDelta,eventId", mcuVersion,
                mpuVersion, vin, iccid, configVersion, configDelta, otaDto.getEventId());
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        TboxConfigDto tboxConfigDto = new TboxConfigDto();
        Integer lastConfigDelta = tboxConfigService.checkConfigDelta(otaDto.getTboxId(), mcuVersion,
                mpuVersion, vin, iccid, configVersion, configDelta, eventId);
        tboxConfigDto.setConfigDelta(lastConfigDelta);
        eventService.end(getVin(otaDto), getCode(otaDto), tboxConfigDto, eventId);
        otaDto.setMid(2);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        tboxConfigDto.setEventId(eventId);
        tboxConfigDto.setAid(otaDto.getAid());
        tboxConfigDto.setMid(otaDto.getMid());
        return tboxConfigDto;
    }

    @Override
    public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) throws ServLayerException {
        AssertRequired("packageId,eventId", packageId, otaDto.getEventId());
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        TboxConfigPackageDto tboxConfigPackageDto = new TboxConfigPackageDto();
        // 此处读取缓存
        String key = otaDto.getEventId().toString();
        tboxConfigPackageDto.setPackageId(packageId);
        List<TboxConfigSettingDto> list = new ArrayList<>();
        // 此处假数据
        list.add(new TboxConfigSettingDto(1, BinaryAndHexUtil.hexStringToByte("01")));
        list.add(new TboxConfigSettingDto(2, BinaryAndHexUtil.hexStringToByte("02")));
        tboxConfigPackageDto.setTboxConfigSettingDtos(list);
        eventService.end(getVin(otaDto), getCode(otaDto), tboxConfigPackageDto, eventId);
        otaDto.setMid(7);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        tboxConfigPackageDto.setEventId(eventId);
        tboxConfigPackageDto.setAid(otaDto.getAid());
        tboxConfigPackageDto.setMid(otaDto.getMid());
        return tboxConfigPackageDto;
    }

    @Override
    public void closeConfigUpdate(OtaDto otaDto, Boolean result, byte[] mcuVersion, byte[] mpuVersion,
                                  byte[] configVersion, Integer configDelta) throws ServLayerException {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public void requestReadConfig(String vin, Long[] tboxConfigsettingIds) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_CONFIGURATION, 8);
        Long eventId = eventService.start(vin, Constants.AID_CONFIGURATION + "8", null);
        otaDto.setEventId(eventId);
        sendQueue(otaDto, new ReadConfigReqDto(tboxConfigsettingIds));
        eventService.end(vin, Constants.AID_CONFIGURATION + "8", eventId);
    }

    @Override
    public void responseReadConfig(OtaDto otaDto,
                                   List<TboxConfigSettingDto> tboxConfigSettingDtos) throws ServLayerException {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public KeyDto generateAsymmetricKey(OtaDto otaDto) throws ServLayerException {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        KeyDto asymmetricKeyDto = new KeyDto();
        String key = tboxConfigService.generateAsymmetricKey(otaDto.getTboxId());
        asymmetricKeyDto.setPublicKey(BinaryAndHexUtil.hexStringToByte(key));
        eventService.end(getVin(otaDto), getCode(otaDto), asymmetricKeyDto, eventId);
        otaDto.setMid(11);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        asymmetricKeyDto.setEventId(eventId);
        asymmetricKeyDto.setAid(otaDto.getAid());
        asymmetricKeyDto.setMid(otaDto.getMid());
        return asymmetricKeyDto;
    }

    @Override
    public KeyDto bindTboxWithSecretKey(OtaDto otaDto, byte[] secretKeyWithEnc,
                                        byte[] tboxSnWithEnc) throws ServLayerException {
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        tboxConfigService.bindTboxWithSecretKey(otaDto.getTboxId(), secretKeyWithEnc, tboxSnWithEnc);
        KeyDto keyDto = new KeyDto();
        keyDto.setEventId(eventId);
        keyDto.setTboxId(otaDto.getTboxId());
        keyDto.setSecretKeyAccepted(true);
        eventService.end(getVin(otaDto), getCode(otaDto), keyDto, eventId);
        otaDto.setMid(13);
        eventService.start(getVin(otaDto), getCode(otaDto), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
        keyDto.setEventId(eventId);
        keyDto.setAid(otaDto.getAid());
        keyDto.setMid(otaDto.getMid());
        return keyDto;
    }
}