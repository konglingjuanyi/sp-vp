package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IBcallService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 bCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-11 12:56
 * modify date 2015-6-25 10:45
 * @version 0.3, 2015-6-25
 */
@Transactional
public class BcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("bcallServiceProxy")
    private IBcallService bcallService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartBcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 1);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        bcallService.startBcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestBcallStatus() {
        bcallService.requestBcallStatus(vin);
    }

    @Test
    @Rollback(false)
    public void testUpdateBcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 4);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        bcallService.updateBcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() {
        bcallService.requestHangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() {
        String callNumber = "4008208888";
        bcallService.requestCallBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 8);
        bcallService.responseCallBack(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseBcall() {
        bcallService.requestCloseBcall(vin);
    }

    @Test
    @Rollback(false)
    public void testCloseBcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 9);
        bcallService.closeBcall(otaDto);
    }

}
