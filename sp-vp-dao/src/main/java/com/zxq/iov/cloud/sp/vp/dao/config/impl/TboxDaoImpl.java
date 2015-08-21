package com.zxq.iov.cloud.sp.vp.dao.config.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 安防 TBOX持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-4-29 15:01
 * modify date 2015-8-18 12:50
 * @version 0.5, 2015-8-18
 */
@Service
public class TboxDaoImpl implements ITboxDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxDaoImpl.class);

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@Override
	public void updateAsymmetricKey(Long tboxId, String modulus, String publicExponent,
									String privateExponent) {
		redisTemplate.opsForHash().put(tboxId.toString(), "modulus", modulus);
		redisTemplate.opsForHash().put(tboxId.toString(), "publicExponent", publicExponent);
		redisTemplate.opsForHash().put(tboxId.toString(), "privateExponent", privateExponent);
	}

	@Override
	public void updateSecretKey(Long tboxId, String secretKey) {
		redisTemplate.opsForHash().put(tboxId.toString(), "secretKey", secretKey);
	}

	@Override
	public String findModulusById(Long tboxId) {
		return redisTemplate.opsForHash().get(tboxId.toString(), "modulus").toString();
	}

	@Override
	public String findPrivateExponentyById(Long tboxId) {
		return redisTemplate.opsForHash().get(tboxId.toString(), "privateExponent").toString();
	}

}