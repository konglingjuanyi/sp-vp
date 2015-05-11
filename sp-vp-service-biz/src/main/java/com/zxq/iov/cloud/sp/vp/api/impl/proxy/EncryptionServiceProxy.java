package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.vp.api.impl.EncryptionServiceImpl;
import com.zxq.iov.cloud.sp.vp.api.impl.event.EncryptionEvent;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.Key;

/**
 * 安防 事件代理加密服务实现类
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17:07
 * @version 0.1, 2015-4-23
 */
@Service
@Qualifier("encryptionServiceProxy")
public class EncryptionServiceProxy implements IEncryptionService {

    @Autowired
    @Qualifier("encryptionService")
    private IEncryptionService encryptionService;
    @Autowired
    @Qualifier("encryptionEvent")
    private IEvent event;

    @Override
    public KeyDto generateAsymmetricKey(TboxDto tboxDto) {
        this.event.startEvent(tboxDto);
        this.event.startTask(tboxDto);
        Object result = this.event.startStep(tboxDto, KeyDto.class);
        if(null == result) {
            result = this.encryptionService.generateAsymmetricKey(tboxDto);
            this.event.finishStep(tboxDto, result);
        }
        KeyDto keyDto = (KeyDto)result;
        keyDto.setTaskId(tboxDto.getTaskId());
        return keyDto;
    }

    @Override
    public TboxDto bindTboxWithSecretKey(TboxDto tboxDto, KeyDto keyDto) {
        Object result = this.event.startStep(tboxDto, TboxDto.class);
        if(null == result) {
            result = this.encryptionService.bindTboxWithSecretKey(tboxDto, keyDto);
            this.event.finishStep(tboxDto, result);
        }
        this.event.finishTask(tboxDto);
        this.event.finishEvent(tboxDto);
        return (TboxDto)result;
    }
}
