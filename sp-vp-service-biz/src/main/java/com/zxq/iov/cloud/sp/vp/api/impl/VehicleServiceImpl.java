package com.zxq.iov.cloud.sp.vp.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zxq.iov.cloud.sp.vp.api.IVehicleService;
import com.zxq.iov.cloud.sp.vp.dao.IVehicleDaoService;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 15-1-13
 * Time: 上午10:31
 */
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    private IVehicleDaoService vehicleDaoService;

    @Override
    public void updateVehicle(String vin, String vehicleName, String licenceNumber){
        if(vehicleName != null && vehicleName.length() != 0) {
            // set vehicle nickName 
        }
    }

}
