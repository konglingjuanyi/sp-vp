package com.zxq.iov.cloud.sp.vp.api.service;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.api.IIcallService;
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
 * 安防 iCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:50
 * modify date 2015-6-25 16:19
 * @version 0.2, 2015-6-25
 */
@Transactional
public class IcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    @Qualifier("icallServiceProxy")
    private IIcallService icallService;

    private String vin = "1";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStartIcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ICALL, 1);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        icallService.startIcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestIcallStatus() {
        icallService.requestIcallStatus(vin);
    }

    @Test
    @Rollback(false)
    public void testUpdateIcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ICALL, 4);
        List<VehiclePosDto> vehiclePosDtos = new ArrayList<>();
        vehiclePosDtos.add(new VehiclePosDto(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        icallService.updateIcall(otaDto, vehiclePosDtos, 0, 50, 60, null);
    }

    @Test
    @Rollback(false)
    public void testRequestHangUp() {
        icallService.requestHangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testRequestCallBack() {
        String callNumber = "4008208888";
        icallService.requestCallBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ICALL, 8);
        icallService.responseCallBack(otaDto, true, null);
    }

    @Test
    @Rollback(false)
    public void testRequestCloseIcall() {
        icallService.requestCloseIcall(vin);
    }

    @Test
    @Rollback(false)
    public void testCloseIcall() {
        OtaDto otaDto = new OtaDto(tboxId, new Date(), Constants.AID_ICALL, 9);
        icallService.closeIcall(otaDto);
    }

}
