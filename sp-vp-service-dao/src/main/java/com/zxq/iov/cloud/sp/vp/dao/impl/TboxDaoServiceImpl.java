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

import com.zxq.iov.cloud.core.log.LoggerFactory;
import com.zxq.iov.cloud.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.repo.IEventRepository;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 TBOX持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-29 15:01:07
 * @version 0.1, 2015-4-29
 */
@Service
public class TboxDaoServiceImpl implements ITboxDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxDaoServiceImpl.class);

	@Override
	public void updateAsymmetricKey(Long tboxId, String publicKey, String privateKey) {

	}

	@Override
	public void updateSecretKey(Long tboxId, String secretKey) {

	}

	@Override
	public String findPrivateKeyById(Long tboxId) {
		return null;
	}

}