package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
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
 * modify date 2015-8-5 16:00
 * @version 0.5, 2015-8-5
 */
@Transactional
public class JourneyApiImplTest extends BaseServiceTestCase {

    @Autowired
    private IJourneyApi journeyApi;

    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartJourney() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 1);
        Integer tboxJourneyId = 15;
        Integer keyId = 1;
        journeyApi.startJourney(otaDto, new Date(), tboxJourneyId, keyId);
    }

    @Test
    @Rollback(false)
    public void testUpdateJourney() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 2);
        Integer tboxJourneyId = 15;
        Integer instFuelConsumption = 1;
        VehiclePosDto vehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        journeyApi.updateJourney(otaDto, tboxJourneyId, instFuelConsumption, vehiclePosDto);
    }

    @Test
    @Rollback(false)
    public void testEndJourney() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_JOURNEY, 3);
        VehiclePosDto startVehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        VehiclePosDto endVehiclePosDto = new VehiclePosDto(1, 1, 1, 1,1, 1, 1, new Date(), 1);
        Integer tboxJourneyId = 15;
        Integer distance = 1;
        Integer avgSpeed = 1;
        Integer fuelEco = 1;
        Integer odometer = 1;
        Integer fuelLevelPrc = 1;
        Integer fuelLevelDisp = 1;
        Integer fuelRange = 1;
        journeyApi.endJourney(otaDto, startVehiclePosDto, endVehiclePosDto, tboxJourneyId,
                distance, avgSpeed, fuelEco, odometer, fuelLevelPrc, fuelLevelDisp, fuelRange);
    }

}
