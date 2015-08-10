package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
 * modify date 2015-8-5 14:03
 * @version 0.4, 2015-8-5
 */
@Transactional
public class IcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IIcallService icallService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStart() throws Exception {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        icallService.start(tboxId, vehiclePoses, 0, 50, 60, new Date());
    }

    @Test
    @Rollback(false)
    public void testUpdate() throws Exception {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        icallService.update(tboxId, vehiclePoses, 0, 50, 60, new Date());
    }

    @Test
    @Rollback(false)
    public void testHangUp() throws Exception {
        icallService.hangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testCallBack() throws Exception {
        String callNumber = "4008208888";
        icallService.callBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() throws Exception {
        icallService.responseCallBack(tboxId, true, null);
    }

    @Test
    @Rollback(false)
    public void testClose() throws Exception {
        icallService.close(vin);
    }

}