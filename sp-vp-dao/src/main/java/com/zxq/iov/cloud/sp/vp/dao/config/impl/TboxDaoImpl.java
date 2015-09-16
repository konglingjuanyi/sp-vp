/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-29       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.impl.TboxDaoImpl
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config.impl;

import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.util.JedisClusterUtils;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import com.saicmotor.telematics.framework.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

/**
 * 安防 TBOX数据访问接口实现类
 */
@Service
public class TboxDaoImpl implements ITboxDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TboxDaoImpl.class);

	@Autowired
	private JedisClusterUtils jedisClusterUtils;

	@Override
	public void updateAsymmetricKey(Long tboxId, String modulus, String publicExponent, String privateExponent) {
		JedisCluster jedisCluster = jedisClusterUtils.getJedisCluster();
		Map<String, String> map = new HashMap<>();
		map.put("modulus", modulus);
		map.put("publicExponent", publicExponent);
		map.put("privateExponent", privateExponent);
		jedisCluster.hmset(tboxId.toString(), map);
	}

	@Override
	public void updateSecretKey(Long tboxId, String secretKey) {
		JedisCluster jedisCluster = jedisClusterUtils.getJedisCluster();
		Map<String, String> map = new HashMap<>();
		map.put("secretKey", secretKey);
		jedisCluster.hmset(tboxId.toString(), map);
	}

	@Override
	public String findModulusById(Long tboxId) {
		JedisCluster jedisCluster = jedisClusterUtils.getJedisCluster();
		return jedisCluster.hget(tboxId.toString(), "modulus");
	}

	@Override
	public String findPrivateExponentyById(Long tboxId) {
		JedisCluster jedisCluster = jedisClusterUtils.getJedisCluster();
		return jedisCluster.hget(tboxId.toString(), "privateExponent");
	}

}