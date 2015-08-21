package com.zxq.iov.cloud.sp.vp.dao.config;

/**
 * 安防 TBOX持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-4 15:15
 * modify date 2015-8-18 12:47
 * @version 0.5, 2015-8-18
 */
public interface ITboxDao {

	/**
	 * 更新TBOX对应的非对称密钥
	 * @param tboxId			TBOX ID
	 * @param modulus 			模
	 * @param publicExponent	公钥指数
	 * @param privateExponent 	私钥指数
	 */
	void updateAsymmetricKey(Long tboxId, String modulus, String publicExponent,
							 String privateExponent);

	/**
	 * 更新TBOX私钥
	 * @param tboxId      		TBOX ID
	 * @param secretKey     	TBOX私钥
	 */
	void updateSecretKey(Long tboxId, String secretKey);

	/**
	 * 根据TBOX ID得到RSA的模
	 * @param tboxId 			TBOX ID
	 * @return					模
	 */
	String findModulusById(Long tboxId);

	/**
	 * 根据TBOX ID得到私钥指数
	 * @param tboxId 			TBOX ID
	 * @return					私钥指数
	 */
	String findPrivateExponentyById(Long tboxId);
	
}