package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 安防 远程控制服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-17 14:25
 * modify date 2015-8-5 17:362
 * @version 0.6, 2015-8-5
 */
@Transactional
public class RvcServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IRvcService rvcService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;
    private Long userId = 1L;

    @Test
    @Rollback(false)
    public void testRequestControl() throws Exception {
        Long eventId = 1L;
        String command = "find_my_car";
        String requestClient = "mobile";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("light", true);
        parameters.put("horn", "low");
        parameters.put("horn_length", 30);
        ControlCommand controlCommand = rvcService.requestControl(requestClient, userId, vin, command,
                parameters, eventId);
        Assert.assertNotNull(controlCommand);
    }

    @Test
    @Rollback(false)
    public void testCancelControl() throws Exception {
        String command = "5";
        String requestClient = "mobile";
        rvcService.cancelControl(requestClient, userId, vin, command);
    }

    @Test
    @Rollback(false)
    public void testUpdateControlStatus() throws Exception {
        Long eventId = 1L;
        String rvcStatus = "3";
        VehiclePos vehiclePos =  new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus("status", 1));
        rvcService.updateControlStatus(tboxId, BinaryAndHexUtil.hexStringToByte(rvcStatus),
                null, vehiclePos, vehicleStatuses, eventId);
    }

}