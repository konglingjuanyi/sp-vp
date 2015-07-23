package com.zxq.iov.cloud.sp.vp.api.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IEcallService;
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
 * 安防 eCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-12 13:22
 * modify date 2015-6-25 16:14
 * @version 0.2, 2015-6-25
 */
@Transactional
public class EcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("ecallServiceProxy")
    private IEcallService ecallService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartEcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 1);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallService.startEcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestEcallStatus() {
        ecallService.requestEcallStatus(vin);
    }

    @Test
    @Rollback(false)
    public void testUpdateEcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 4);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        ecallService.updateEcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() {
        ecallService.requestHangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() {
        String callNumber = "4008208888";
        ecallService.requestCallBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 8);
        ecallService.responseCallBack(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseEcall() {
        ecallService.requestCloseEcall(vin);
    }

    @Test
    @Rollback(false)
    public void testCloseEcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ECALL, 9);
        ecallService.closeEcall(otaDto);
    }

}
