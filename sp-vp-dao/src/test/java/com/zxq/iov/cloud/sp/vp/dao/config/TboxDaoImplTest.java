package com.zxq.iov.cloud.sp.vp.dao.config;

import com.saicmotor.telematics.framework.core.test.BaseServiceTestCase;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安防 TBOX持久化服务测试类
 *
 * @author 叶荣杰
 * create date 2015-8-12 17:19
 * modify date
 * @version 0.1, 2015-8-12
 */
@Transactional
public class TboxDaoImplTest extends BaseServiceTestCase {

    private Long tboxId = 1L;
    private Long userId = 1L;
    private String vin = "11111111111111111";

    @Autowired
    private ITboxDao tboxDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    @Rollback(false)
    public void testUpdateAsymmetricKey(){
        String modulus = "1";
        String publicExponent = "1";
        String privateExponent = "2";
        tboxDao.updateAsymmetricKey(tboxId, modulus, publicExponent, privateExponent);
        String _publicKey = redisTemplate.opsForValue().get(tboxId+"_publicKey").toString();
        Assert.assertNotNull(_publicKey);
    }

}
