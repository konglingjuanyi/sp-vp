package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSONObject;
import com.zxq.iov.cloud.sp.vp.api.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.QueueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 远程配置代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 15:19
 * modify date 2015-6-29 12:57
 * @version 0.3, 2015-6-29
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
    public void requestConfigUpdate(String vin) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_CONFIGURATION, 3);
        Long eventId = event.start(otaDto);
        tboxConfigService.requestConfigUpdate(vin);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "checkConfigUpdate");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) {
        event.start(otaDto);
        tboxConfigService.responseConfigUpdate(otaDto, isAccepted);
        event.end(otaDto);
    }

    @Override
    public TboxConfigDto checkConfigDelta(OtaDto otaDto, String mcuVersion, String mpuVersion, String vin,
                                          String iccid, String configVersion, Integer configDelta) {
        event.start(otaDto);
        TboxConfigDto tboxConfigDto = tboxConfigService.checkConfigDelta(otaDto, mcuVersion, mpuVersion,
                vin, iccid, configVersion, configDelta);
        event.end(otaDto);
        otaDto.setMid(2);
        event.start(otaDto);
        event.end(otaDto);
        return tboxConfigDto;
    }

    @Override
    public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) {
        event.start(otaDto);
        TboxConfigPackageDto tboxConfigPackageDto = tboxConfigService.getConfigPackage(otaDto, packageId);
        event.end(otaDto);
        otaDto.setMid(7);
        event.start(otaDto);
        event.end(otaDto);
        return tboxConfigPackageDto;
    }

    @Override
    public void closeConfigUpdate(OtaDto otaDto, Boolean result, String mcuVersion, String mpuVersion,
                                  String configVersion, Integer configDelta) {
        event.start(otaDto);
        tboxConfigService.closeConfigUpdate(otaDto, result, mcuVersion, mpuVersion, configVersion, configDelta);
        event.end(otaDto);
    }

    @Override
    public void requestReadConfig(String vin, Long[] tboxConfigsettingIds) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_CONFIGURATION, 8);
        Long eventId = event.start(otaDto);
        tboxConfigService.requestReadConfig(vin, tboxConfigsettingIds);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "checkConfigUpdate");
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
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
        otaDto.setMid(11);
        event.start(otaDto);
        event.end(otaDto);
        return keyDto;
    }

    @Override
    public KeyDto bindTboxWithSecretKey(OtaDto otaDto, String secretKeyWithEnc, String tboxSnWithEnc) {
        event.start(otaDto);
        KeyDto keyDto = tboxConfigService.bindTboxWithSecretKey(otaDto, secretKeyWithEnc, tboxSnWithEnc);
        event.end(otaDto);
        otaDto.setMid(13);
        event.start(otaDto);
        event.end(otaDto);
        return keyDto;
    }
}
