package com.zxq.iov.cloud.sp.vp.api.impl;

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
 * modify date 2015-8-7 13:29
 * @version 0.6, 2015-8-7
 */
@Service
public class RemoteKeyApiImpl extends BaseApi implements IRemoteKeyApi {

    @Autowired
    private IRemoteKeyService remoteKeyService;
    @Autowired
    private IEventService eventService;

    @Override
    public void requestWriteKey(String vin, Integer keyType, String keyValue, Integer keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) throws Exception {
        AssertRequired("vin,keyType,keyValue", vin, keyType, keyValue);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_REMOTE_KEY, 1);
        eventService.start(vin, Constants.AID_REMOTE_KEY + "1");
        remoteKeyService.requestWriteKey(vin, keyType, keyValue, keyReference, keyValidityStartTime,
                keyValidityEndTime);
        sendQueue(otaDto, new WriteKeyDto(keyType, BinaryAndHexUtil.hexStringToByte(keyValue),
                keyReference, keyValidityStartTime, keyValidityEndTime));
        eventService.end(vin, Constants.AID_REMOTE_KEY + "1");
    }

    @Override
    public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess,
                                 Integer writeFailureReason) throws Exception {
        AssertRequired("otaDto,writeSuccess", otaDto, writeSuccess);
        eventService.start(getVin(otaDto), getCode(otaDto));
        remoteKeyService.responseWriteKey(otaDto.getTboxId(), writeSuccess, writeFailureReason);
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void requestDeleteKey(String vin, Integer keyReference) throws Exception {
        AssertRequired("vin,keyReference", vin, keyReference);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_REMOTE_KEY, 3);
        eventService.start(vin, Constants.AID_REMOTE_KEY + "3");
        remoteKeyService.requestDeleteKey(vin, keyReference);
        sendQueue(otaDto, new DeleteKeyDto(keyReference));
        eventService.end(vin, Constants.AID_REMOTE_KEY + "3");
    }

    @Override
    public void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess,
                                  Integer deleteFailureReason) throws Exception {
        AssertRequired("otaDto,deleteSuccess", otaDto, deleteSuccess);
        eventService.start(getVin(otaDto), getCode(otaDto));
        remoteKeyService.responseDeleteKey(otaDto.getTboxId(), deleteSuccess, deleteFailureReason);
        eventService.end(getVin(otaDto), getCode(otaDto));
    }

    @Override
    public void keyAlarm(OtaDto otaDto) throws Exception {
        AssertRequired("otaDto", otaDto);
        eventService.start(getVin(otaDto), getCode(otaDto));
        remoteKeyService.keyAlarm(otaDto.getTboxId());
        eventService.end(getVin(otaDto), getCode(otaDto));
    }
}