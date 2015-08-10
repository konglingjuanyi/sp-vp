package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.entity.svt.StolenAlarm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 被盗追踪服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-15 16:49
 * modify date 2015-8-6 11:13
 * @version 0.4, 2015-8-6
 */
@Transactional
public class SvtServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private ISvtService svtService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testAlarm() throws Exception {
        StolenAlarm stolenAlarm = new StolenAlarm();
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Long eventId = 1L;
        svtService.alarm(tboxId, stolenAlarm, vehiclePos, eventId);
    }

    @Test
    @Rollback(false)
    public void testUpdateTrack() throws Exception {
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Long eventId = 1L;
        svtService.updateTrack(tboxId, vehicleStatuses, vehiclePos, eventId);
    }

}