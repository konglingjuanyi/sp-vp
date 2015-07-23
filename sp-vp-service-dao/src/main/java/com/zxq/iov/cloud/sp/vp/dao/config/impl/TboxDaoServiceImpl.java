package com.zxq.iov.cloud.sp.vp.dao.config.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 安防 TBOX持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-29 15:01
 * modify date 2015-6-29 10:18
 * @version 0.3, 2015-6-29
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

	@Override
	public String findVinById(Long tboxId) {
		// 如何获得待定
		String vin = "1";
		return vin;
	}

	@Override
	public String findVinByTboxSn(String tboxSn) {
		// 如何获得待定
		String vin = "1";
		return vin;
	}

	@Override
	public Long findTboxIdByVin(String vin) {
		// 如何获得待定
		Long tboxId = 1L;
		return tboxId;
	}
}