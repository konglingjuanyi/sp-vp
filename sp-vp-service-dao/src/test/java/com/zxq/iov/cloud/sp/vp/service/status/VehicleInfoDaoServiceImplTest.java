package com.zxq.iov.cloud.sp.vp.service.status;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDaoService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleInfo;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 车辆信息持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-13 12:52
 * modify date 2015-7-21 15:19
 * @version 0.3, 2015-7-21
 */
@Transactional
public class VehicleInfoDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IVehicleInfoDaoService vehicleInfoDaoService;

    @Test
    @Rollback(true)
    public void testCreateVehicleInfo(){
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setOwnerId(1L);
        vehicleInfo.setSourceType(1);
        vehicleInfo.setVin("001");
        vehicleInfo = vehicleInfoDaoService.createVehicleInfo(vehicleInfo);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(true)
    public void testUpdateVehicleInfo(){
        Long vehicleInfoId = 6L;
        VehicleInfo vehicleInfo = vehicleInfoDaoService.findVehicleInfoById(vehicleInfoId);
        vehicleInfo.setVin("002");
        vehicleInfo = vehicleInfoDaoService.updateVehicleInfo(vehicleInfo);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(true)
    public void testFindLatestVehicleInfo(){
        String vin = "001";
        VehicleInfo vehicleInfo = vehicleInfoDaoService.findLatestVehicleInfo(vin);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(true)
    public void testListVehicleInfoByEventId(){
        Long eventId = 1L;
        List<VehicleInfo> list = vehicleInfoDaoService.listVehicleInfoByEventId(eventId);
        Assert.assertTrue(list.size() > 0);
    }
}
