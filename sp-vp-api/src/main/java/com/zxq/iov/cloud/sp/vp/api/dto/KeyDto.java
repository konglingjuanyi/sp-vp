package com.zxq.iov.cloud.sp.vp.api.dto;

/**
 * 安防 密钥DTO
 *
 * @author 叶荣杰
 * create date 2015-4-23 10:30:07
 * @version 0.1, 2015-4-23
 */
public class KeyDto extends BaseDto {

    private String publicKey;
    private String privateKey;
    private String secretKey;

    public String getPrivateKey() {

        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
