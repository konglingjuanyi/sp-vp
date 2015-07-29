package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.saicmotor.telematics.framework.core.dal.repo.mybatis.PageResult;
import com.zxq.iov.cloud.sp.vp.api.ITboxConfigService;
import com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigPackageDto;
import com.zxq.iov.cloud.sp.vp.api.dto.config.TboxConfigSettingDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxConfigSettingDaoService;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxPersonalConfigDaoService;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxConfigSetting;
import com.zxq.iov.cloud.sp.vp.entity.config.TboxPersonalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 安防 远程配置服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-19 11:44
 * modify date 2015-7-29 10:53
 * @version 0.6, 2015-7-29
 */
@Service
@Qualifier("tboxConfigService")
public class TboxConfigServiceImpl extends BaseService implements ITboxConfigService {

    @Autowired
    private ITboxPersonalConfigDaoService tboxPersonalConfigDaoService;
    @Autowired
    private ITboxConfigSettingDaoService tboxConfigSettingDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;

    @Override
    public void requestConfigUpdate(String vin) throws Exception {
        AssertRequired("vin", vin);
        // 无业务操作
    }

    @Override
    public void responseConfigUpdate(OtaDto otaDto, Boolean isAccepted) throws Exception {
        AssertRequired("isAccepted", isAccepted);
        // 无业务操作，拒绝也没有原因？
    }

    @Override
    public TboxConfigDto checkConfigDelta(OtaDto otaDto, byte[] mcuVersion, byte[] mpuVersion, String vin,
                                          String iccid, byte[] configVersion, Integer configDelta)
            throws Exception {
        AssertRequired("mcuVersion,mpuVersion,vin,iccid,configVersion,configDelta,eventId", mcuVersion,
                mpuVersion, vin, iccid, configVersion, configDelta, otaDto.getEventId());
        TboxPersonalConfig tboxPersonalConfig = tboxPersonalConfigDaoService.findTboxPersonalConfigByTboxId(otaDto.getTboxId());
        TboxConfigDto tboxConfigDto = new TboxConfigDto();
        if(tboxPersonalConfig.getConfigDelta().intValue() > configDelta.intValue()) {
            int packageSize = 10;
            List<TboxConfigSetting> list = tboxConfigSettingDaoService.listTboxConfigSettingByTboxId(otaDto.getTboxId());
            int packageCount = list.size() / packageSize;
            if(list.size() % packageSize > 0) {
                packageCount++;
            }
            tboxConfigDto.setPackageCount(packageCount);
            tboxConfigDto.setConfigDelta(tboxPersonalConfig.getConfigDelta());
            // 此处写入缓存
            String key = otaDto.getEventId().toString();
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
        return tboxConfigDto;
    }

    @Override
    public TboxConfigPackageDto getConfigPackage(OtaDto otaDto, Integer packageId) throws Exception {
        AssertRequired("packageId,eventId", packageId, otaDto.getEventId());
        TboxConfigPackageDto tboxConfigPackageDto = new TboxConfigPackageDto();
        // 此处读取缓存
        String key = otaDto.getEventId().toString();
        tboxConfigPackageDto.setPackageId(packageId);
        List<TboxConfigSettingDto> list = new ArrayList<>();
        // 此处假数据
        list.add(new TboxConfigSettingDto(1, BinaryAndHexUtil.hexStringToByte("01")));
        list.add(new TboxConfigSettingDto(2, BinaryAndHexUtil.hexStringToByte("02")));
        tboxConfigPackageDto.setTboxConfigSettingDtos(list);
        return tboxConfigPackageDto;
    }

    @Override
    public void closeConfigUpdate(OtaDto otaDto, Boolean result, byte[] mcuVersion, byte[] mpuVersion,
                                  byte[] configVersion, Integer configDelta) {
        // 暂时不知道需要做啥
    }

    @Override
    public void requestReadConfig(String vin, Long[] tboxConfigsettingIds) throws Exception {
        AssertRequired("vin", vin);
        // 无业务操作
    }

    @Override
    public void responseReadConfig(OtaDto otaDto, List<TboxConfigSettingDto> tboxConfigSettingDtos) {
        // 暂不知道做什么
    }

    @Override
    public KeyDto generateAsymmetricKey(OtaDto otaDto) {
        Long tboxId = null;
        if(null != otaDto.getTboxSn()) {
            // 根据TBOX SN定位到TBOX对象
        }
        else {
            // 根据TBOX ID定位到TBOX对象
        }
        // 生成非对称的public key, private key
        String publicKey = "";
        String privateKey = "";
        tboxDaoService.updateAsymmetricKey(tboxId, publicKey, privateKey); // 绑定tbox写入缓存
        KeyDto asymmetricKeyDto = new KeyDto();
        asymmetricKeyDto.setPublicKey(BinaryAndHexUtil.hexStringToByte(publicKey));
        return asymmetricKeyDto;
    }

    @Override
    public KeyDto bindTboxWithSecretKey(OtaDto otaDto, byte[] secretKeyWithEnc, byte[] tboxSnWithEnc) {
        Long tboxId = null;
        if(null != otaDto.getTboxSn()) {
            // 根据TBOX SN定位到TBOX对象
        }
        else {
            // 根据TBOX ID定位到TBOX对象
        }
        String privateKey = tboxDaoService.findPrivateKeyById(tboxId);  // 根据tbox从缓存中读出private key
        // 用private Key对tboxSnWithEnc进行解密，验证是否正确，如果正确的话对secretKey进行解密
        // 用private key对secretKey进行解密
        String secretKey = "";
        tboxDaoService.updateSecretKey(tboxId, secretKey);  // 绑定解密后的secretKey
        KeyDto keyDto = new KeyDto();
        keyDto.setEventId(otaDto.getEventId());
        keyDto.setTboxId(tboxId);
        keyDto.setSecretKeyAccepted(true);
        return keyDto;
    }
}
