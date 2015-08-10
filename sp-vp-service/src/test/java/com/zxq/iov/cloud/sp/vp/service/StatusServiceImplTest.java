package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 车辆状态服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-15 11:08
 * modify date 2015-8-6 9:18
 * @version 0.6, 2015-8-6
 */
@Transactional
public class StatusServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IStatusService statusService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testGetVehicleInfo() throws Exception {
        Long eventId = 197L;
        VehicleInfo vehicleInfo = statusService.getVehicleInfo(vin, eventId);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleInfo() throws Exception {
        Long eventId = 197L;
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus("status", 1));
        List<VehicleStatus> vehicleAlerts = new ArrayList<>();
        vehicleAlerts.add(new VehicleStatus("status", 1));
        statusService.logVehicleInfo(tboxId, null, null, vehiclePos, vehicleStatuses,
                vehicleAlerts, new Date(), eventId);
    }

    @Test
    @Rollback(false)
    public void testLogVehicleAlert() throws Exception {
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1);
        VehicleStatus vehicleAlert = new VehicleStatus("status", 1);
        statusService.logVehicleAlert(tboxId, new Date(), vehiclePos, vehicleAlert);
    }
}