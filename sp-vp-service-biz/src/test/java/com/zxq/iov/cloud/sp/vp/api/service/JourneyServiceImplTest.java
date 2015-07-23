package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IJourneyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 行程服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-9 16:10
 * modify date 2015-6-26 13:56
 * @version 0.4, 2015-6-26
 */
@Transactional
public class JourneyServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("journeyServiceProxy")
    private IJourneyService journeyService;

    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartJourney() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 1);
        Integer tboxJourneyId = 10;
        Integer keyId = 1;
        journeyService.startJourney(otaDto, new Date(), tboxJourneyId, keyId);
    }

    @Test
    @Rollback(false)
    public void testUpdateJourney() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 2);
        Integer tboxJourneyId = 10;
        Integer instFuelConsumption = 1;
        VehiclePosDto vehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        journeyService.updateJourney(otaDto, tboxJourneyId, instFuelConsumption, vehiclePosDto);
    }

    @Test
    @Rollback(false)
    public void testEndJourney() {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 3);
        VehiclePosDto startVehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        VehiclePosDto endVehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Integer tboxJourneyId = 10;
        Integer distance = 1;
        Integer avgSpeed = 1;
        Integer fuelEco = 1;
        Integer odometer = 1;
        Integer fuelLevelPrc = 1;
        Integer fuelLevelDisp = 1;
        Integer fuelRange = 1;
        journeyService.endJourney(otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId,
                distance, avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
    }

}
