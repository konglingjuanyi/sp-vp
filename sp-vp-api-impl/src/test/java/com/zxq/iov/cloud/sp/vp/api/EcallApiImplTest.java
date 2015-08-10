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
 * 安防 eCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-12 13:22
 * modify date 2015-8-5 13:28
 * @version 0.3, 2015-8-5
 */
@Transactional
public class EcallApiImplTest extends BaseServiceTestCase {

    @Autowired
    private IEcallApi ecallApi;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartEcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 1);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallApi.startEcall(otaDto, vehiclePosDtos, 0, 1, 50, 60);
    }

    @Test
    @Rollback(false)
    public void testRequestEcallStatus() throws Exception {
        ecallApi.requestEcallStatus(vin);
    }

    @Test
    @Rollback(false)
    public void testUpdateEcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 4);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallApi.updateEcall(otaDto, vehiclePosDtos, 0, 1, 50, 60);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() throws Exception {
        ecallApi.requestHangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() throws Exception {
        String callNumber = "4008208888";
        ecallApi.requestCallBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 8);
        ecallApi.responseCallBack(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseEcall() throws Exception {
        ecallApi.requestCloseEcall(vin);
    }

    @Test
    @Rollback(false)
    public void testCloseEcall() throws Exception {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 9);
        ecallApi.closeEcall(otaDto);
    }

}
