/**
 * Copyright (c) 2007-2011 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IS Dept.
 * 
 * @Project: 
 * @Title: VehicleServiceImpl.java
 * @Package com.saicmotor.telematics.tsgp.tcmp.vehicle.service
 * @Description: 
 *
 * @CreateDate : 2012-2-2
 * @CreateBy   : Administrator
 */
package com.zxq.iov.cloud.sp.vp.dao.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.IVehicleDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.IVehicleRepository;
import com.zxq.iov.cloud.sp.vp.entity.Vehicle;

/**
 * The Class VehicleServiceImpl.
 *
 * @ClassName: VehicleServiceImpl
 * @Description: ()
 * @author Administrator
 * @date 2012-2-2 9:40:07
 * date           modify by          workitem
 * 2012-2-2        Administrator
 * @version 0.1, 2012-2-2
 */
@Service
public class VehicleDaoServiceImpl extends BaseServiceImpl<IVehicleRepository, Vehicle, Long> implements IVehicleDaoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDaoServiceImpl.class);
	
    @Autowired
	public VehicleDaoServiceImpl(IVehicleRepository repo){
		super(repo);
	}

	public Vehicle createVehicle(Vehicle vehicle) {		 
		if (vehicle == null){
			LOGGER.error("Vehicle cannot be null");
		}	
        vehicle.setId(null);
		super.save(vehicle);
		return vehicle;
	}
	
}