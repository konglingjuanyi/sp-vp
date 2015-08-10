package com.zxq.iov.cloud.sp.vp.dao.status;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 安防 车辆状态信息持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-13 13:31
 * modify date 2015-5-15 11:02
 * @version 0.2, 2015-5-15
 */
@Transactional
public class VehicleStatusDaoImplTest extends BaseServiceTestCase {

    @Autowired
    private IVehicleInfoDao vehicleInfoDao;
    @Autowired
    private IVehicleStatusDao vehicleStatusDao;

    @Test
    @Rollback(true)
    public void testCreateVehicleStatus(){
        Long vehicleInfoId = 6L;
        VehicleStatus vehicleStatus = new VehicleStatus();
        vehicleStatus.setCode("001");
        vehicleStatus.setName("001");
        vehicleStatus.setType(1);
        vehicleStatus.setValue(1);
        vehicleStatus.setVehicleInfo(vehicleInfoDao.findVehicleInfoById(vehicleInfoId));
        vehicleStatus = vehicleStatusDao.createVehicleStatus(vehicleStatus);
        Assert.assertNotNull(vehicleStatus);
    }

    @Test
    @Rollback(true)
    public void testUpdateVehicleStatus(){
        Long vehicleStatusId = 8L;
        VehicleStatus vehicleStatus = vehicleStatusDao.findVehicleStatusById(vehicleStatusId);
        vehicleStatus.setName("002");
        vehicleStatus = vehicleStatusDao.updateVehicleStatus(vehicleStatus);
        Assert.assertNotNull(vehicleStatus);
    }

    @Test
    @Rollback(true)
    public void testFindLatestVehicleStatus(){
        String vin = "001";
        List<VehicleStatus> vehicleStatuses = vehicleStatusDao.findLatestVehicleStatus(vin, 1);
        Assert.assertTrue(vehicleStatuses.size() >= 0);
    }

    @Test
    @Rollback(true)
    public void testFindVehicleStatusByVehicleInfoId(){
        Long vehicleInfoId = 6L;
        List<VehicleStatus> vehicleStatuses = vehicleStatusDao.findVehicleStatusByVehicleInfoId(vehicleInfoId, 1);
        Assert.assertTrue(vehicleStatuses.size() > 0);
    }
}
