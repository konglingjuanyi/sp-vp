package com.zxq.iov.cloud.sp.vp.api.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.DeleteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.WriteKeyDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 电子钥匙服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:47
 * modify date 2015-8-11 10:22
 * @version 0.7, 2015-8-11
 */
@Service
public class RemoteKeyApiImpl extends BaseApi implements IRemoteKeyApi {

    @Autowired
    private IRemoteKeyService remoteKeyService;
    @Autowired
    private IEventService eventService;

    @Override
    public void requestWriteKey(String vin, Integer keyType, String keyValue, Integer keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) throws ServLayerException {
        AssertRequired("vin,keyType,keyValue", vin, keyType, keyValue);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_REMOTE_KEY, 1);
        Long eventId = eventService.start(vin, Constants.AID_REMOTE_KEY + "1", null);
        remoteKeyService.requestWriteKey(vin, keyType, keyValue, keyReference, keyValidityStartTime,
                keyValidityEndTime);
        sendQueue(otaDto, new WriteKeyDto(keyType, BinaryAndHexUtil.hexStringToByte(keyValue),
                keyReference, keyValidityStartTime, keyValidityEndTime));
        eventService.end(vin, Constants.AID_REMOTE_KEY + "1", eventId);
    }

    @Override
    public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess,
                                 Integer writeFailureReason) throws ServLayerException {
        AssertRequired("otaDto,writeSuccess", otaDto, writeSuccess);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        remoteKeyService.responseWriteKey(otaDto.getTboxId(), writeSuccess, writeFailureReason);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public void requestDeleteKey(String vin, Integer keyReference) throws ServLayerException {
        AssertRequired("vin,keyReference", vin, keyReference);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_REMOTE_KEY, 3);
        Long evnetId = eventService.start(vin, Constants.AID_REMOTE_KEY + "3", null);
        remoteKeyService.requestDeleteKey(vin, keyReference);
        sendQueue(otaDto, new DeleteKeyDto(keyReference));
        eventService.end(vin, Constants.AID_REMOTE_KEY + "3", evnetId);
    }

    @Override
    public void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess,
                                  Integer deleteFailureReason) throws ServLayerException {
        AssertRequired("otaDto,deleteSuccess", otaDto, deleteSuccess);
        Long evnetId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        remoteKeyService.responseDeleteKey(otaDto.getTboxId(), deleteSuccess, deleteFailureReason);
        eventService.end(getVin(otaDto), getCode(otaDto), evnetId);
    }

    @Override
    public void keyAlarm(OtaDto otaDto) throws ServLayerException {
        AssertRequired("otaDto", otaDto);
        Long evnetId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        remoteKeyService.keyAlarm(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto), evnetId);
    }
}