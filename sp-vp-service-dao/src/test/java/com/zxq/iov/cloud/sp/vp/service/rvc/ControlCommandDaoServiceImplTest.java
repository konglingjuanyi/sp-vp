package com.zxq.iov.cloud.sp.vp.service.rvc;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 控制命令持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-17 11:52
 * modify date
 * @version 0.1, 2015-6-17
 */
@Transactional
public class ControlCommandDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IControlCommandDaoService controlCommandDaoService;

    @Test
    @Rollback(false)
    public void testCreateControlCommand(){
        Long tboxId = 1L;
        Long eventId = 1L;
        ControlCommand controlCommand = new ControlCommand();
        controlCommand.setTboxId(tboxId);
        controlCommand.setEventId(eventId);
        controlCommand.setCode("0");
        controlCommand.setName(Constants.RVC_CMD.get(0));
        controlCommand = controlCommandDaoService.createControlCommand(controlCommand);
        Assert.assertNotNull(controlCommand);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlCommand(){
        Long controlCommandId = 8L;
        ControlCommand controlCommand = controlCommandDaoService.findControlCommandById(controlCommandId);
        controlCommand.setParameter("1");
        controlCommand = controlCommandDaoService.updateControlCommand(controlCommand);
        Assert.assertNotNull(controlCommand);
    }

    @Test
    @Rollback(false)
    public void testRemoveControlCommand(){
        Long controlCommandId = 8L;
        controlCommandDaoService.removeControlCommand(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testFindControlCommandByEventId(){
        Long eventId = 1L;
        ControlCommand controlCommand = controlCommandDaoService.findControlCommandByEventId(eventId);
        Assert.assertNotNull(controlCommand);
    }

}
