package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IEncryptionService;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;

/**
 * User: 荣杰
 * Date: 2015/4/23
 * Time: 10:56
 */
public class EncryptionServiceImpl implements IEncryptionService {

    @Override
    public KeyDto generateAsymmetricKey(String tbox) {
        // 生成非对称的public key, private key
        // 绑定tbox写入缓存
        KeyDto asymmetricKeyDto = new KeyDto();
        asymmetricKeyDto.setPublicKey("生成的public key");
        return asymmetricKeyDto;
    }

    @Override
    public String bindTboxWithSecretKey(String tbox, KeyDto keyDto) {
        // 根据tbox从换成中读出private key
        // 用private key对secretKey进行解密
        // 根据tbox获得tbox对象
        // 绑定解密后的secretKey
        return null;
    }
}
