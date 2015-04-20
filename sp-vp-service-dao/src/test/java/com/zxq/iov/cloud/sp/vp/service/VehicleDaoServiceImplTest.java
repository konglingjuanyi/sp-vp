package com.zxq.iov.cloud.sp.vp.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zxq.iov.cloud.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.dao.IVehicleDaoService;
import com.zxq.iov.cloud.sp.vp.entity.Vehicle;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-11-27
 * Time: 上午8:56
 */
@Transactional
public class VehicleDaoServiceImplTest extends BaseServiceTestCase {

    @Autowired
    private IVehicleDaoService vehicleDaoService;

    @Test
    public void testCreateVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle = vehicleDaoService.createVehicle(vehicle);
        Assert.assertNotNull(vehicle);
    }

}
