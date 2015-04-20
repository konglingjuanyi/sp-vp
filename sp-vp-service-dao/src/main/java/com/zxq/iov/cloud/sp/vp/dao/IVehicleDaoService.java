/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: IVehicleService.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.service
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.dao;

import com.zxq.iov.cloud.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.Vehicle;

/**
 * The Class IVehicleService.
 *
 * @ClassName: IVehicleService
 * @Description: ()
 * @author Administrator
 * @date 2012-2-2 9:40:07
 * date           modify by          workitem
 * 2012-2-2        Administrator
 * @version 0.1, 2012-2-2
 */
public interface IVehicleDaoService extends BaseService<Vehicle, Long> {
	
	/**
	 * 创建Vehicle
	 */
	Vehicle createVehicle(Vehicle vehicle);
	
}