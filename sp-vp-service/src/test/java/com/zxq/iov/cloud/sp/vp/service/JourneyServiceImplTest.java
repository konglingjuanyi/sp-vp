package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 行程服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-9 16:10
 * modify date 2015-8-5 15:20
 * @version 0.5, 2015-8-5
 */
@Transactional
public class JourneyServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IJourneyService journeyService;

    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStart() throws Exception {
        Integer tboxJourneyId = 12;
        Integer keyId = 1;
        journeyService.start(tboxId, new Date(), tboxJourneyId, keyId);
    }

    @Test
    @Rollback(false)
    public void testUpdate() throws Exception {
        Integer tboxJourneyId = 12;
        Integer instFuelConsumption = 1;
        VehiclePos vehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        journeyService.update(tboxId, tboxJourneyId, instFuelConsumption, vehiclePos);
    }

    @Test
    @Rollback(false)
    public void testEnd() throws Exception {
        VehiclePos startVehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        VehiclePos endVehiclePos = new VehiclePos(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Integer tboxJourneyId = 12;
        Integer distance = 1;
        Integer avgSpeed = 1;
        Integer fuelEco = 1;
        Integer odometer = 1;
        Integer fuelLevelPrc = 1;
        Integer fuelLevelDisp = 1;
        Integer fuelRange = 1;
        journeyService.end(tboxId, startVehiclePos, endVehiclePos, tboxJourneyId,
                distance, avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
    }

}