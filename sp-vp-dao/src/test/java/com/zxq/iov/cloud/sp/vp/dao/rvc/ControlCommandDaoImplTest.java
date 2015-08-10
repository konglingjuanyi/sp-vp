package com.zxq.iov.cloud.sp.vp.dao.rvc;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.Constants;
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
 * modify date 2015-7-29 17:48
 * @version 0.2, 2015-7-29
 */
@Transactional
public class ControlCommandDaoImplTest extends BaseServiceTestCase {

    private Long tboxId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private IControlCommandDao controlCommandDao;

    @Test
    @Rollback(false)
    public void testCreateControlCommand(){
        Long eventId = 1L;
        String requestClient = "mobile";
        ControlCommand controlCommand = new ControlCommand(tboxId, vin, requestClient,
                Constants.RVC_CMD.get("find_my_car"), Constants.RVC_CMD_CODE.get("find_my_car"), null);
        controlCommand.setEventId(eventId);
        controlCommand = controlCommandDao.createControlCommand(controlCommand);
        Assert.assertNotNull(controlCommand);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlCommand(){
        Long controlCommandId = 8L;
        ControlCommand controlCommand = controlCommandDao.findControlCommandById(controlCommandId);
        controlCommand.setParameter("1");
        controlCommand = controlCommandDao.updateControlCommand(controlCommand);
        Assert.assertNotNull(controlCommand);
    }

    @Test
    @Rollback(false)
    public void testRemoveControlCommand(){
        Long controlCommandId = 8L;
        controlCommandDao.removeControlCommand(controlCommandId);
    }

    @Test
    @Rollback(false)
    public void testFindControlCommandByEventId(){
        Long eventId = 1L;
        ControlCommand controlCommand = controlCommandDao.findControlCommandByEventId(eventId);
        Assert.assertNotNull(controlCommand);
    }

}
