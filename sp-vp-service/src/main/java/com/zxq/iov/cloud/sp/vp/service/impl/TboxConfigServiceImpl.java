package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.common.RSAUtils;
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
 * 安防 远程配置服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 11:44
 * modify date 2015-8-18 14:00
 * @version 0.8, 2015-8-18
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
            TboxDto tboxDto = findTboxById(tboxId);
            tboxDto.setPublicKey(publicExponent);
            tboxDto.setPrivateKey(privateExponent);
            //updateTbox(tboxDto); 数据库字段长度不够
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
    public void bindTboxWithSecretKey(Long tboxId, String secretKeyWithEnc, String tboxSnWithEnc) {
        String modulus = tboxDao.findModulusById(tboxId);
        String privateExponent = tboxDao.findPrivateExponentyById(tboxId);
        RSAPrivateKey privateKey = RSAUtils.getPrivateKey(modulus, privateExponent);
        String secretKey = null;
        String tboxSn = null;
        TboxDto tboxDto = findTboxById(tboxId);
        try {
            secretKey = RSAUtils.decryptByPrivateKey(secretKeyWithEnc, privateKey);
            tboxSn = RSAUtils.decryptByPrivateKey(tboxSnWithEnc, privateKey);
            if(tboxSn.equals(tboxDto.getTboxSn())) {
                tboxDto.setSecurityKey(secretKey);
                updateTbox(tboxDto);
                tboxDao.updateSecretKey(tboxId, secretKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}