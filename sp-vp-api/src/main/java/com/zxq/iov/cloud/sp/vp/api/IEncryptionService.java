package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;

/**
 * User: 荣杰
 * Date: 2015/4/22
 * Time: 18:05
 */
public interface IEncryptionService {

    /**
     * 生成非对称密钥，用以对TBOX密钥加解密
     * @param tbox      TBOX ID或者SN
     * @return          密钥传输对象
     */
    KeyDto generateAsymmetricKey(String tbox);

    /**
     * 将TBOX密钥与TBOX ID绑定
     * @param tbox      TBOX ID或SN
     * @param keyDto    密钥传输对象（包含公钥加密过的TBOX密钥）
     * @return          TBOX ID
     */
    String bindTboxWithSecretKey(String tbox, KeyDto keyDto);

}
