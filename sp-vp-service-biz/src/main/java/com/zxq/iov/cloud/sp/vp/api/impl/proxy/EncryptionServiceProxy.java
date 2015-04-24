package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.impl.EncryptionServiceImpl;
import com.zxq.iov.cloud.sp.vp.api.impl.event.EncryptionEvent;
import com.zxq.iov.cloud.sp.vp.api.IEventService;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:17
 */
public class EncryptionServiceProxy implements IEncryptionService {

    private IEncryptionService encryptionService;
    private IEvent event;

    public EncryptionServiceProxy() {
        this.encryptionService = new EncryptionServiceImpl();
        this.event = new EncryptionEvent();
    }

    @Override
    public KeyDto generateAsymmetricKey(String tbox) {
        Long eventId = this.event.startEvent();
        Long taskId = this.event.startTask(eventId);
        this.event.startAndFinishStep(taskId);
        KeyDto keyDto = this.encryptionService.generateAsymmetricKey(tbox);
        keyDto.setTaskId(taskId);
        return keyDto;
    }

    @Override
    public String bindTboxWithSecretKey(String tbox, KeyDto keyDto) {
        String tboxId = this.encryptionService.bindTboxWithSecretKey(tbox, keyDto);
        this.event.startAndFinishStep(keyDto.getTaskId());
        Long eventId = this.event.finishTask(keyDto.getTaskId());
        this.event.finishEvent(eventId);
        return tboxId;
    }
}
