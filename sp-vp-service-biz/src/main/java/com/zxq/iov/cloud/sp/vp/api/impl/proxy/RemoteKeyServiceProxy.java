package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 电子钥匙代理服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 14:46
 * modify date
 * @version 0.1, 2015-6-23
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
    public void requestWriteKey(RemoteKeyDto remoteKeyDto) {
        remoteKeyDto.setAid(Constants.AID_REMOTE_KEY);
        remoteKeyDto.setMid(1);
        remoteKeyDto.setEventCreateTime(new Date());
        event.start(remoteKeyDto);
        remoteKeyService.requestWriteKey(remoteKeyDto);
        event.end(remoteKeyDto);
    }

    @Override
    public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason) {
        event.start(otaDto);
        remoteKeyService.responseWriteKey(otaDto, writeSuccess, writeFailureReason);
        event.end(otaDto);
    }

    @Override
    public void requestDeleteKey(Long tboxId, Integer keyReference) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_REMOTE_KEY, 3);
        event.start(otaDto);
        remoteKeyService.requestDeleteKey(tboxId, keyReference);
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
