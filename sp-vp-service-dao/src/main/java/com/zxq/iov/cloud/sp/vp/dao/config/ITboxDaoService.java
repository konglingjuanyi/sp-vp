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
package com.zxq.iov.cloud.sp.vp.dao.config;

/**
 * 安防 TBOX持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-4 15:15:07
 * @version 0.1, 2015-5-4
 */
public interface ITboxDaoService {

	/**
	 * 更新非对称密钥
	 * @param tboxId		TBOX ID
	 * @param publicKey		公钥
	 * @param privateKey 	私钥
	 */
	void updateAsymmetricKey(Long tboxId, String publicKey, String privateKey);

	/**
	 * 更新TBOX私钥
	 * @param tboxId      	TBOX ID
	 * @param secretKey     TBOX私钥
	 */
	void updateSecretKey(Long tboxId, String secretKey);

	/**
	 * 根据TBOX ID得到私钥
	 * @param tboxId 		TBOX ID
	 * @return				私钥
	 */
	String findPrivateKeyById(Long tboxId);
	
}