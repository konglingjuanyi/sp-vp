package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.BaseDto;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.vp.api.impl.EncryptionServiceImpl;
import com.zxq.iov.cloud.sp.vp.api.impl.event.EncryptionEvent;
import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;

/**
 * 安防 事件代理加密服务实现类
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:17:07
 * @version 0.1, 2015-4-23
 */
public class EncryptionServiceProxy implements IEncryptionService {

    private IEncryptionService encryptionService;
    private IEvent event;

    public EncryptionServiceProxy() {
        this.encryptionService = EncryptionServiceImpl.getInstance();
        this.event = new EncryptionEvent();
    }

    @Override
    public KeyDto generateAsymmetricKey(TboxDto tboxDto) {
        BaseDto baseDto = this.event.startEvent(tboxDto);
        KeyDto keyDto = this.encryptionService.generateAsymmetricKey(tboxDto);
        keyDto.setTaskId(baseDto.getTaskId());
        return keyDto;
    }

    @Override
    public TboxDto bindTboxWithSecretKey(TboxDto tboxDto, KeyDto keyDto) {
        tboxDto = this.encryptionService.bindTboxWithSecretKey(tboxDto, keyDto);
        this.event.finishEvent(tboxDto);
        return tboxDto;
    }
}
