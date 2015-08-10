package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
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
 * modify date 2015-8-3 16:09
 * @version 0.3, 2015-7-21
 */
@Transactional
public class VehicleInfoDaoImplTest extends BaseServiceTestCase {

    private Long tboxId = 1L;

    @Autowired
    private IVehicleInfoDao vehicleInfoDao;

    @Test
    @Rollback(false)
    public void testCreateVehicleInfo(){
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setOwnerId(1L);
        vehicleInfo.setSourceType(1);
        vehicleInfo.setVin("001");
        vehicleInfo.setTboxId(tboxId);
        vehicleInfo = vehicleInfoDao.createVehicleInfo(vehicleInfo);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(false)
    public void testUpdateVehicleInfo(){
        Long vehicleInfoId = 6L;
        VehicleInfo vehicleInfo = vehicleInfoDao.findVehicleInfoById(vehicleInfoId);
        vehicleInfo.setVin("002");
        vehicleInfo = vehicleInfoDao.updateVehicleInfo(vehicleInfo);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(false)
    public void testFindLatestVehicleInfo(){
        String vin = "001";
        VehicleInfo vehicleInfo = vehicleInfoDao.findLatestVehicleInfo(vin);
        Assert.assertNotNull(vehicleInfo);
    }

    @Test
    @Rollback(false)
    public void testListVehicleInfoByEventId(){
        Long eventId = 1L;
        List<VehicleInfo> list = vehicleInfoDao.listVehicleInfoByEventId(eventId);
        Assert.assertTrue(list.size() > 0);
    }
}
