package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.api.dto.TboxDto;

/**
 * 安防 加密服务实现类
 *
 * @author 叶荣杰
 * create date 2015-4-23 10:56:07
 * @version 0.1, 2015-4-23
 */
public class EncryptionServiceImpl implements IEncryptionService {

    private EncryptionServiceImpl(){}

    public static EncryptionServiceImpl getInstance(){

        return EncryptionServiceInstance.instance;

    }

    private static class EncryptionServiceInstance{
        static EncryptionServiceImpl instance = new EncryptionServiceImpl();
    }

    @Override
    public KeyDto generateAsymmetricKey(TboxDto tboxDto) {
        // 生成非对称的public key, private key
        // 绑定tbox写入缓存
        KeyDto asymmetricKeyDto = new KeyDto();
        asymmetricKeyDto.setPublicKey("生成的public key");
        return asymmetricKeyDto;
    }

    @Override
    public TboxDto bindTboxWithSecretKey(TboxDto tboxDto, KeyDto keyDto) {
        // 根据tbox从换成中读出private key
        // 用private key对secretKey进行解密
        // 根据tbox获得tbox对象
        // 绑定解密后的secretKey
        return null;
    }
}
