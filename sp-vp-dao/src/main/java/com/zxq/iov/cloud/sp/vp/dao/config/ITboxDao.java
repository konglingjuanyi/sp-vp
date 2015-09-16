/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-05-04       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

/**
 * 安防 TBOX数据访问接口
 */
public interface ITboxDao {

	/**
	 * 更新TBOX对应的非对称密钥
	 *
	 * @param tboxId          TBOX ID
	 * @param modulus         模
	 * @param publicExponent  公钥指数
	 * @param privateExponent 私钥指数
	 */
	void updateAsymmetricKey(Long tboxId, String modulus, String publicExponent, String privateExponent);

	/**
	 * 更新TBOX私钥
	 *
	 * @param tboxId    TBOX ID
	 * @param secretKey TBOX私钥
	 */
	void updateSecretKey(Long tboxId, String secretKey);

	/**
	 * 根据TBOX ID得到RSA的模
	 *
	 * @param tboxId TBOX ID
	 * @return 模
	 */
	String findModulusById(Long tboxId);

	/**
	 * 根据TBOX ID得到私钥指数
	 *
	 * @param tboxId TBOX ID
	 * @return 私钥指数
	 */
	String findPrivateExponentyById(Long tboxId);

}