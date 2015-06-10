package com.zxq.iov.cloud.sp.vp.mgmt.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zxq.iov.cloud.sp.vp.dao.IVehicleDaoService;
import com.zxq.iov.cloud.sp.vp.mgmt.api.IVehicleManager;
import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.VehicleDto;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 15-1-13
 * Time: 上午10:31
 */
public class VehicleManagerImpl implements IVehicleManager {

    @Autowired
    private IVehicleDaoService vehicleDaoService;

	@Override
	public void deleteVehicle(List<VehicleDto> vehicles) {
		// delete vehicles 
	}

}
