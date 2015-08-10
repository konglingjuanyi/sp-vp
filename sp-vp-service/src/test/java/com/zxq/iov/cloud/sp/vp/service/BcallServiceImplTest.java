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
 * 安防 bCall服务测试类
 *
 * @author 叶荣杰
 * create date 2015-6-11 12:56
 * modify date 2015-8-4 16:36
 * @version 0.4, 2015-8-4
 */
@Transactional
public class BcallServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IBcallService bcallService;

    private String vin = "11111111111111111";
    private Long tboxId = 1L;

    @Test
    @Rollback(false)
    public void testStart() throws Exception {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        bcallService.start(tboxId, vehiclePoses, 0, 50, 60, null, new Date());
    }

    @Test
    @Rollback(false)
    public void testUpdate() throws Exception {
        List<VehiclePos> vehiclePoses = new ArrayList<>();
        vehiclePoses.add(new VehiclePos(1, 1, 1, 1, 1, 1, 1, new Date(), 1));
        bcallService.update(tboxId, vehiclePoses, 0, 50, 60, null, new Date());
    }

    @Test
    @Rollback(false)
    public void testHangUp() throws Exception {
        bcallService.hangUp(vin);
    }

    @Test
    @Rollback(false)
    public void testCallBack() throws Exception {
        String callNumber = "4008208888";
        bcallService.callBack(vin, callNumber);
    }

    @Test
    @Rollback(false)
    public void testResponseCallBack() throws Exception {
        bcallService.responseCallBack(tboxId, false, 1);
    }

    @Test
    @Rollback(false)
    public void testClose() throws Exception {
        bcallService.close(vin);
    }

}
