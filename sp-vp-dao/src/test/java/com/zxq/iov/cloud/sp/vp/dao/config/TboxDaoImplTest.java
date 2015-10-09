/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-08-12       荣杰         1.0            Initial Version
 *
 * com.zxq.iov.cloud.sp.vp.dao.config.TboxDaoImplTest
 *
 * sp - sp-vp-dao
 */

package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import com.zxq.iov.cloud.sp.vp.common.util.JedisClusterUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Vector;

/**
 * 安防服务 TBOX数据访问测试类
 */
@Transactional
public class TboxDaoImplTest extends BaseServiceTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TboxDaoImplTest.class);
    private static List list = new Vector<>();

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private ITboxDao tboxDao;
    @Autowired
    private JedisClusterUtils jedisClusterUtils;

    @Test
    @Rollback(false)
    public void testUpdateAsymmetricKey(){
        String modulus = "1";
        String publicExponent = "1";
        String privateExponent = "2";
        tboxDao.updateAsymmetricKey(tboxId, modulus, publicExponent, privateExponent);
//        JedisCluster jedisCluster = jedisClusterUtils.getJedisCluster();
//        jedisCluster.set("test", "222");
//        jedisCluster.lpush("test2", String.valueOf(Thread.currentThread().getId()));
//        list.add(Thread.currentThread().getId());
        LOGGER.info("插入 = " + Thread.currentThread().getId());
    }

    /**
     * 压力测试内部类，用以测试DAO接口的并发情况
     */
    class LoadTest extends Thread {
        @Override
        public void run() {
//            LOGGER.info("线程" + Thread.currentThread().getName() + "开始");
            double begin = System.currentTimeMillis();
            testUpdateAsymmetricKey();
            double end = System.currentTimeMillis();
            double time = end - begin;
//            LOGGER.info("current thread time is " + time + "millis");
//            LOGGER.info("线程" + Thread.currentThread().getName() + "结束");
        }
    }

    /**
     * 压力测试执行方法
     *
     * @throws Exception
     */
    @Test
    @Rollback(false)
    public void testLoad() throws Exception {
        JedisCluster jedisCluster = jedisClusterUtils.getJedisCluster();
        jedisCluster.del("test2");

        LoadTest test = new LoadTest();
        int base = 1;
        for (int i = 5; i < 10; i++) {
            System.out.println("==========开始" + i * base + "线程并发==========");
            for (int j = 0; j < i * base; j++) {
                new Thread(test).start();
            }
            //            Thread.sleep(i * base * 500);
            System.out.println("==========并发结束==========");
        }
//        LOGGER.info("publicKey = " + jedisCluster.lindex("test2", jedisCluster.llen("test2")-1));
        LOGGER.info("publicKey = " + list.get(0));
    }

}
