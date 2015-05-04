package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.vp.dao.ITboxDaoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 安防 加密服务实现类
 *
 * @author 叶荣杰
 * create date 2015-4-23 10:56:07
 * @version 0.1, 2015-4-23
 */
public class EncryptionServiceImpl implements IEncryptionService {

    @Autowired
    private ITboxDaoService tboxDaoService;

    private EncryptionServiceImpl(){}

    public static EncryptionServiceImpl getInstance(){
        return EncryptionServiceInstance.instance;
    }

    private static class EncryptionServiceInstance{
        static EncryptionServiceImpl instance = new EncryptionServiceImpl();
    }

    @Override
    public KeyDto generateAsymmetricKey(TboxDto tboxDto) {
        String key = (null != tboxDto.getTboxId())?tboxDto.getTboxId().toString():tboxDto.getTboxSn();
        // 生成非对称的public key, private key
        String publicKey = "";
        String privateKey = "";
        tboxDaoService.updateAsymmetricKey(tboxDto.getTboxId(), publicKey, privateKey); // 绑定tbox写入缓存
        KeyDto asymmetricKeyDto = new KeyDto();
        asymmetricKeyDto.setPublicKey(publicKey);
        return asymmetricKeyDto;
    }

    @Override
    public TboxDto bindTboxWithSecretKey(TboxDto tboxDto, KeyDto keyDto) {
        String privateKey = tboxDaoService.findPrivateKeyById(tboxDto.getTboxId());  // 根据tbox从缓存中读出private key
        // 用private key对secretKey进行解密
        String secrectKey = "";
        tboxDaoService.updateSecretKey(tboxDto.getTboxId(), secrectKey);  // 绑定解密后的secretKey
        return tboxDto;
    }

}
