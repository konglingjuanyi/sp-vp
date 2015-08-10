package com.zxq.iov.cloud.sp.vp.api;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
 * modify date 2015-8-5 10:16
 * @version 0.4, 2015-8-5
 */
@Transactional
public class BcallApiImplTest extends BaseServiceTestCase {

    @Autowired
    private IBcallApi bcallApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartBcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 1);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        bcallApi.startBcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestBcallStatus() throws Exception {
        bcallApi.requestBcallStatus(vin);
    }

    @Test
    @Rollback(false)
    public void testUpdateBcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 4);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        bcallApi.updateBcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() throws Exception {
        bcallApi.requestHangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() throws Exception {
        String callNumber = "4008208888";
        bcallApi.requestCallBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 8);
        bcallApi.responseCallBack(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseBcall() throws Exception {
        bcallApi.requestCloseBcall(vin);
    }

    @Test
    @Rollback(false)
    public void testCloseBcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_BCALL, 9);
        bcallApi.closeBcall(otaDto);
    }

}
