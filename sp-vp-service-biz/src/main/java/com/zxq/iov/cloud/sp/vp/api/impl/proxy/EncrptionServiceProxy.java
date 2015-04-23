package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.impl.EncryptionServiceImpl;
import com.zxq.iov.cloud.sp.vp.api.impl.event.EncryptionEventService;
import com.zxq.iov.cloud.sp.vp.api.IEventService;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 11:17
 */
public class EncrptionServiceProxy implements IEncryptionService {

    private IEncryptionService encryptionService;
    private IEventService eventService;

    public EncrptionServiceProxy() {
        this.encryptionService = new EncryptionServiceImpl();
        this.eventService = new EncryptionEventService();
    }

    @Override
    public KeyDto generateAsymmetricKey(String tbox) {
        Long eventId = this.eventService.startEvent();
        Long taskId = this.eventService.startTask(eventId);
        this.eventService.startAndFinishStep(taskId);
        KeyDto keyDto = this.encryptionService.generateAsymmetricKey(tbox);
        keyDto.setTaskId(taskId);
        return keyDto;
    }

    @Override
    public String bindTboxWithSecretKey(String tbox, KeyDto keyDto) {
        String tboxId = this.encryptionService.bindTboxWithSecretKey(tbox, keyDto);
        this.eventService.startAndFinishStep(keyDto.getTaskId());
        Long eventId = this.eventService.finishTask(keyDto.getTaskId());
        this.eventService.finishEvent(eventId);
        return tboxId;
    }
}
