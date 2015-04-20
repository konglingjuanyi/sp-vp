package com.zxq.iov.cloud.sp.vp.mgmt.api;

import java.util.List;

import com.zxq.iov.cloud.sp.vp.mgmt.api.dto.VehicleDto;


/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-9-19
 * Time: 下午3:42
 */
public interface IVehicleManager {

	/**
	 * delete vehicle function for BSS System
	 * @param vehicles
	 */
    public void deleteVehicle(List<VehicleDto> vehicles);

}
