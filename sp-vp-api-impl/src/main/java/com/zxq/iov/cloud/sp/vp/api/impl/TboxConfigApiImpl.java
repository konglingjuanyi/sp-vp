package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.ITboxConfigApi;
import com.zxq.iov.cloud.sp.vp.api.dto.config.*;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 远程配置服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 11:44
 * modify date 2015-8-18 17:17
 * @version 0.10, 2015-8-18
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
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto);
    }

    @Override
    public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) throws ServLayerException {
        AssertRequired("isAccepted", isAccepted);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
    }

    @Override
    public TboxConfigDto checkConfigDelta(OtaDto otaDto, byte[] mcuVersion, byte[] mpuVersion, String vin,
                                          String iccid, byte[] configVersion, Integer configDelta)
            throws ServLayerException {
        AssertRequired("mcuVersion,mpuVersion,vin,iccid,configVersion,configDelta", mcuVersion,
                mpuVersion, vin, iccid, configVersion, configDelta);
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        TboxConfigDto tboxConfigDto = new TboxConfigDto();
        if(!event.isRetry()) {
            Integer lastConfigDelta = tboxConfigService.checkConfigDelta(otaDto.getTboxId(), mcuVersion,
                    mpuVersion, vin, iccid, configVersion, configDelta, event.getId());
            tboxConfigDto.setConfigDelta(lastConfigDelta);
            event.setResult(tboxConfigDto);
            eventService.end(event);
            otaDto.setMid(2);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                tboxConfigDto = JSON.parse(event.getResult().toString(), TboxConfigDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        tboxConfigDto.setEventId(event.getId());
        tboxConfigDto.setAid(otaDto.getAid());
        tboxConfigDto.setMid(otaDto.getMid());
        return tboxConfigDto;
    }

    @Override
    public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) throws ServLayerException {
        AssertRequired("packageId,eventId", packageId, otaDto.getEventId());
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        TboxConfigPackageDto tboxConfigPackageDto = new TboxConfigPackageDto();
        if(!event.isRetry()) {
            // 此处读取缓存
            String key = otaDto.getEventId().toString();
            tboxConfigPackageDto.setPackageId(packageId);
            List<TboxConfigSettingDto> list = new ArrayList<>();
            // 此处假数据
            list.add(new TboxConfigSettingDto(1, BinaryAndHexUtil.hexStringToByte("01")));
            list.add(new TboxConfigSettingDto(2, BinaryAndHexUtil.hexStringToByte("02")));
            tboxConfigPackageDto.setTboxConfigSettingDtos(list);
            event.setResult(tboxConfigPackageDto);
            eventService.end(event);
            otaDto.setMid(7);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                tboxConfigPackageDto = JSON.parse(event.getResult().toString(), TboxConfigPackageDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        tboxConfigPackageDto.setEventId(event.getId());
        tboxConfigPackageDto.setAid(otaDto.getAid());
        tboxConfigPackageDto.setMid(otaDto.getMid());
        return tboxConfigPackageDto;
    }

    @Override
    public void closeConfigUpdate(OtaDto otaDto, Boolean result, byte[] mcuVersion, byte[] mpuVersion,
                                  byte[] configVersion, Integer configDelta) throws ServLayerException {
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
    }

    @Override
    public void requestReadConfig(String vin, Long[] tboxConfigsettingIds) throws ServLayerException {
        AssertRequired("vin", vin);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_CONFIGURATION, 8);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, new ReadConfigReqDto(tboxConfigsettingIds));
    }

    @Override
    public void responseReadConfig(OtaDto otaDto,
                                   List<TboxConfigSettingDto> tboxConfigSettingDtos) throws ServLayerException {
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            eventService.end(event);
        }
    }

    @Override
    public KeyDto generateAsymmetricKey(OtaDto otaDto) throws ServLayerException {
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        KeyDto asymmetricKeyDto = new KeyDto();
        if(!event.isRetry()) {
            byte[] modulus = tboxConfigService.generateAsymmetricKey(otaDto.getTboxId());
            asymmetricKeyDto.setPublicKey(modulus);
            asymmetricKeyDto.setPublicKeyGranted(true);
            event.setResult(asymmetricKeyDto);
            eventService.end(event);
            otaDto.setMid(11);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                asymmetricKeyDto = JSON.parse(event.getResult().toString(), KeyDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        asymmetricKeyDto.setEventId(event.getId());
        asymmetricKeyDto.setAid(otaDto.getAid());
        asymmetricKeyDto.setMid(otaDto.getMid());
        return asymmetricKeyDto;
    }

    @Override
    public KeyDto bindTboxWithSecretKey(OtaDto otaDto, byte[] secretKeyWithEnc,
                                        byte[] tboxSnWithEnc) throws ServLayerException {
        EventAssembler assembler = new EventAssembler();
        Event event = assembler.fromOtaDto(otaDto);
        eventService.start(event);
        KeyDto keyDto = new KeyDto();
        if(!event.isRetry()) {
            tboxConfigService.bindTboxWithSecretKey(otaDto.getTboxId(), new String(secretKeyWithEnc),
                    new String(tboxSnWithEnc));
            keyDto.setEventId(event.getId());
            keyDto.setTboxId(otaDto.getTboxId());
            keyDto.setSecretKeyAccepted(true);
            event.setResult(keyDto);
            eventService.end(event);
            otaDto.setMid(13);
            event = assembler.fromOtaDto(otaDto);
            eventService.start(event);
            eventService.end(event);
        }
        else {
            try {
                keyDto = JSON.parse(event.getResult().toString(), KeyDto.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        keyDto.setEventId(event.getId());
        keyDto.setAid(otaDto.getAid());
        keyDto.setMid(otaDto.getMid());
        return keyDto;
    }
}