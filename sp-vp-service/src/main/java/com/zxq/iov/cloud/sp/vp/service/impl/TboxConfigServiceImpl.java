/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-19       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.TboxConfigServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.common.util.RSAUtils;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxConfigSettingDao;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxPersonalConfigDao;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import com.zxq.iov.cloud.sp.vp.service.ITboxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 安防服务 远程配置服务接口实现类
 */
@Service
public class TboxConfigServiceImpl extends BaseService implements ITboxConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TboxConfigServiceImpl.class);

    @Autowired
    private ITboxPersonalConfigDao tboxPersonalConfigDao;
    @Autowired
    private ITboxConfigSettingDao tboxConfigSettingDao;
    @Autowired
    private ITboxDao tboxDao;


    @Override
    public Integer checkConfigDelta(Long tboxId, byte[] mcuVersion, byte[] mpuVersion,
                                    String vin, String iccid, byte[] configVersion,
                                    Integer configDelta, Long eventId) throws ServLayerException {
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
            try {
                String value = JSON.json(configPackages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tboxPersonalConfig.getConfigDelta();
    }

    @Override
    public List<TboxConfigSetting> getConfigPackage(Long tboxId, Integer packageId,
                                                    Long eventId) throws ServLayerException {
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
    public byte[] generateAsymmetricKey(Long tboxId) throws ServLayerException {
        try {
            Map<String, Object> keyMap = RSAUtils.getKeys();
            RSAPublicKey publicKey = (RSAPublicKey)keyMap.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey)keyMap.get("private");
            //模
            BigInteger modulus = publicKey.getModulus();
            //公钥指数
            String publicExponent = publicKey.getPublicExponent().toString();
            //私钥指数
            String privateExponent = privateKey.getPrivateExponent().toString();
            tboxDao.updateAsymmetricKey(tboxId, modulus.toString(), publicExponent, privateExponent);
            byte[] tmp = modulus.toByteArray();
            byte[] modulusByte = new byte[128];
            for(int i=1; i<tmp.length; i++) {
                modulusByte[i-1] = tmp[i];
            }
            return modulusByte;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServLayerException("");
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey(Long tboxId) throws ServLayerException {
        String modulus = tboxDao.findModulusById(tboxId);
        String privateExponent = tboxDao.findPrivateExponentyById(tboxId);
        return RSAUtils.getPrivateKey(modulus, privateExponent);
    }

    @Override
    public void updateSecretKey(Long tboxId, String secretKey) {
        tboxDao.updateSecretKey(tboxId, secretKey);
    }
}