package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.DeleteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.WriteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.EventAssembler;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 电子钥匙服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:47
 * modify date 2015-8-12 14:40
 * @version 0.8, 2015-8-12
 */
@Service
public class RemoteKeyApiImpl extends BaseApi implements IRemoteKeyApi {

    @Autowired
    private IRemoteKeyService remoteKeyService;
    @Autowired
    private IEventService eventService;

    @Override
    public void requestWriteKey(String vin, Integer keyType, String keyValue, Long keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) throws ServLayerException {
        AssertRequired("vin,keyType,keyValue", vin, keyType, keyValue);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_REMOTE_KEY, 1);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            remoteKeyService.requestWriteKey(vin, keyType, keyValue, keyReference, keyValidityStartTime,
                    keyValidityEndTime);
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, new WriteKeyDto(keyType, BinaryAndHexUtil.hexStringToByte(keyValue),
                keyReference, keyValidityStartTime, keyValidityEndTime));
    }

    @Override
    public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess,
                                 Integer writeFailureReason) throws ServLayerException {
        AssertRequired("otaDto,writeSuccess", otaDto, writeSuccess);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            remoteKeyService.responseWriteKey(otaDto.getTboxId(), writeSuccess, writeFailureReason);
            eventService.end(event);
        }
    }

    @Override
    public void requestDeleteKey(String vin, Long keyReference) throws ServLayerException {
        AssertRequired("vin,keyReference", vin, keyReference);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_REMOTE_KEY, 3);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            remoteKeyService.requestDeleteKey(vin, keyReference);
            eventService.end(event);
        }
        otaDto.setEventId(event.getId());
        sendQueue(otaDto, new DeleteKeyDto(keyReference));
    }

    @Override
    public void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess,
                                  Integer deleteFailureReason) throws ServLayerException {
        AssertRequired("otaDto,deleteSuccess", otaDto, deleteSuccess);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            remoteKeyService.responseDeleteKey(otaDto.getTboxId(), deleteSuccess, deleteFailureReason);
            eventService.end(event);
        }
    }

    @Override
    public void keyAlarm(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Event event = new EventAssembler().fromOtaDto(otaDto);
        eventService.start(event);
        if(!event.isRetry()) {
            remoteKeyService.keyAlarm(otaDto.getTboxId());
            eventService.end(event);
        }
    }
}