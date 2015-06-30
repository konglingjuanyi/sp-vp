package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSONObject;
import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.QueueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 电子钥匙代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 14:46
 * modify date 2015-6-29 16:03
 * @version 0.2, 2015-6-29
 */
@Service
@Qualifier("remoteKeyServiceProxy")
public class RemoteKeyServiceProxy implements IRemoteKeyService {

    @Autowired
    @Qualifier("remoteKeyService")
    private IRemoteKeyService remoteKeyService;
    @Autowired
    @Qualifier("eventImpl")
    private IEvent event;

    @Override
    public void requestWriteKey(String vin, Integer keyType, String keyValue, Integer keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_REMOTE_KEY, 1);
        Long eventId = event.start(otaDto);
        remoteKeyService.requestWriteKey(vin, keyType, keyValue, keyReference, keyValidityStartTime,
                keyValidityEndTime);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "writeKey");
        msg.put("keyType", keyType);
        msg.put("keyValue", keyValue);
        msg.put("keyReference", keyReference);
        msg.put("keyValidityStartTime", keyValidityStartTime);
        msg.put("keyValidityEndTime", keyValidityEndTime);
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason) {
        event.start(otaDto);
        remoteKeyService.responseWriteKey(otaDto, writeSuccess, writeFailureReason);
        event.end(otaDto);
    }

    @Override
    public void requestDeleteKey(String vin, Integer keyReference) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_REMOTE_KEY, 3);
        Long eventId = event.start(otaDto);
        remoteKeyService.requestDeleteKey(vin, keyReference);
        JSONObject msg = new JSONObject();
        msg.put("eventId", eventId);
        msg.put("owner", vin);
        msg.put("method", "deleteKey");
        msg.put("keyReference", keyReference);
        new QueueUtil().send(Constants.QUEUE_NAME, msg.toString());
        event.end(otaDto);
    }

    @Override
    public void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess, Integer deleteFailureReason) {
        event.start(otaDto);
        remoteKeyService.responseDeleteKey(otaDto, deleteSuccess, deleteFailureReason);
        event.end(otaDto);
    }

    @Override
    public void keyAlarm(OtaDto otaDto) {
        event.start(otaDto);
        remoteKeyService.keyAlarm(otaDto);
        event.end(otaDto);
    }
}
