package com.zxq.iov.cloud.sp.vp.dao.config;

/**
 * 安防 TBOX持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-5-4 15:15
 * modify date 2015-8-4 11:04
 * @version 0.4, 2015-8-4
 */
public interface ITboxDao {

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

	/**
	 * 根据TBOX ID得到VIN码
	 * @param tboxId     	TBOX ID
	 * @return          	VIN码
	 */
	String findVinById(Long tboxId);

	/**
	 * 根据TBOX序列号得到VIN码
	 * @param tboxSn     	TBOX序列号
	 * @return            	VIN码
	 */
	String findVinByTboxSn(String tboxSn);

	/**
	 * 根据车辆唯一码得到TBOX ID
	 * @param vin         	车辆唯一码
	 * @return            	TBOX ID
	 */
	Long findTboxIdByVin(String vin);

	/**
	 * 根据TBOX ID得到用户ID
	 * @param tboxId   		TBOX ID
	 * @return     			用户ID
	 */
	Long findUserIdById(Long tboxId);
	
}