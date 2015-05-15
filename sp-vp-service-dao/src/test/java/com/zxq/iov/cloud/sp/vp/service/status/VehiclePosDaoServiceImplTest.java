package com.zxq.iov.cloud.sp.vp.service.status;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehicleInfoDaoService;
import com.zxq.iov.cloud.sp.vp.dao.status.IVehiclePosDaoService;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 安防 车辆位置信息持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-5-13 13:11
 * modify date 2015-5-15 10:53
 * @version 0.2, 2015-5-15
 */
@Transactional
public class VehiclePosDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IVehicleInfoDaoService vehicleInfoDaoService;
    @Autowired
    private IVehiclePosDaoService vehiclePosDaoService;

    @Test
    @Rollback(true)
    public void testCreateVehiclePos(){
        Long vehicleInfoId = 6L;
        VehiclePos vehiclePos = new VehiclePos();
        vehiclePos.setAltitude(1);
        vehiclePos.setGpsStatus(1);
        vehiclePos.setGpsTime(new Date());
        vehiclePos.setHdop(1);
        vehiclePos.setHeading(0);
        vehiclePos.setLatitude(1);
        vehiclePos.setLongitude(1);
        vehiclePos.setSatellites(1);
        vehiclePos.setSpeed(10);
        vehiclePos.setVehicleInfo(vehicleInfoDaoService.findVehicleInfoById(vehicleInfoId));
        vehiclePos = vehiclePosDaoService.createVehiclePos(vehiclePos);
        Assert.assertNotNull(vehiclePos);
    }

    @Test
    @Rollback(true)
    public void testUpdateVehiclePos(){
        Long vehiclePosId = 13L;
        VehiclePos vehiclePos = vehiclePosDaoService.findVehiclePosById(vehiclePosId);
        vehiclePos.setGpsStatus(2);
        vehiclePos = vehiclePosDaoService.updateVehiclePos(vehiclePos);
        Assert.assertNotNull(vehiclePos);
    }

    @Test
    @Rollback(true)
    public void testFindLatestVehiclePos(){
        String vin = "001";
        VehiclePos vehiclePos = vehiclePosDaoService.findLatestVehiclePos(vin);
        Assert.assertNotNull(vehiclePos);
    }

    @Test
    @Rollback(true)
    public void testFindVehiclePosByVehicleInfoId(){
        Long vehicleInfoId = 6L;
        VehiclePos vehiclePos = vehiclePosDaoService.findVehiclePosByVehicleInfoId(vehicleInfoId);
        Assert.assertNotNull(vehiclePos);
    }

}
