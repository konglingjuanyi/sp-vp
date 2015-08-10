package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxConfigSettingDao;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxPersonalConfigDao;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import com.zxq.iov.cloud.sp.vp.service.ITboxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 远程配置服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 11:44
 * modify date 2015-8-6 10:50
 * @version 0.7, 2015-8-6
 */
@Service
public class TboxConfigServiceImpl extends BaseService implements ITboxConfigService {

    @Autowired
    private ITboxPersonalConfigDao tboxPersonalConfigDao;
    @Autowired
    private ITboxConfigSettingDao tboxConfigSettingDao;
    @Autowired
    private ITboxDao tboxDao;

    @Override
    public Integer checkConfigDelta(Long tboxId, byte[] mcuVersion, byte[] mpuVersion,
                                    String vin, String iccid, byte[] configVersion,
                                    Integer configDelta, Long eventId) throws Exception {
        AssertRequired("mcuVersion,mpuVersion,vin,iccid,configVersion,configDelta,eventId", mcuVersion,
                mpuVersion, vin, iccid, configVersion, configDelta, eventId);
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDao.findTboxPersonalConfigByVin(vin);
        if(null == tboxPersonalConfig) {
            throw new ServLayerException(ExceptionConstants.PERSONAL_CONFIG_NOT_FIND);
        }
        if(tboxPersonalConfig.getTboxId().intValue() != tboxId) {
            throw new ServLayerException(ExceptionConstants.TBOX_NOT_MATCH_VIN);
        }
        if(tboxPersonalConfig.getConfigDelta().intValue() > configDelta.intValue()) {
            int packageSize = 10;
            List<TboxConfigSetting> list = tboxConfigSettingDao.listTboxConfigSettingByTboxId(tboxId);
            int packageCount = list.size() / packageSize;
            if(list.size() % packageSize > 0) {
                packageCount++;
            }
            // 此处写入缓存
            String key = eventId.toString();
            JSONArray configPackages = new JSONArray();
            JSONObject configPackage = null;
            int toIndex = 0;
            for(int i=0; i<packageCount; i++) {
                configPackage = new JSONObject();
                toIndex = i*packageSize+packageCount-1;
                if(toIndex > list.size()-1) {
                    toIndex = list.size()-1;
                }
                configPackage.put(String.valueOf(i), list.subList(i*packageSize, toIndex));
                configPackages.add(configPackage);
            }
            String value = JSON.json(configPackages);
        }
        return tboxPersonalConfig.getConfigDelta();
    }

    @Override
    public List<TboxConfigSetting> getConfigPackage(Long tboxId, Integer packageId,
                                                    Long eventId) throws Exception {
        AssertRequired("packageId,eventId", packageId, eventId);
        // 此处读取缓存
        String key = eventId.toString();
        List<TboxConfigSetting> list = new ArrayList<>();
        // 此处假数据
        list.add(new TboxConfigSetting());
        list.add(new TboxConfigSetting());
        return list;
    }

    @Override
    public String generateAsymmetricKey(Long tboxId) {
        // 生成非对称的public key, private key
        String publicKey = "";
        String privateKey = "";
        tboxDao.updateAsymmetricKey(tboxId, publicKey, privateKey); // 绑定tbox写入缓存
        return publicKey;
    }

    @Override
    public void bindTboxWithSecretKey(Long tboxId, byte[] secretKeyWithEnc, byte[] tboxSnWithEnc) {
        String privateKey = tboxDao.findPrivateKeyById(tboxId);  // 根据tbox从缓存中读出private key
        // 用private Key对tboxSnWithEnc进行解密，验证是否正确，如果正确的话对secretKey进行解密
        // 用private key对secretKey进行解密
        String secretKey = "";
        tboxDao.updateSecretKey(tboxId, secretKey);  // 绑定解密后的secretKey
    }
}