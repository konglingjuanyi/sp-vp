package com.zxq.iov.cloud.sp.vp.api.dto.config;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 密钥DTO
 *
 * @author 叶荣杰
 * create date 2015-4-23 10:30
 * modify date 2015-6-23 9:24
 * @version 0.2, 2015-6-23
 */
public class KeyDto extends OtaDto {

    private String publicKey;
    private Boolean publicKeyGranted;
    private String privateKey;
    private String secretKey;
    private Boolean secretKeyAccepted;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Boolean isPublicKeyGranted() {
        return publicKeyGranted;
    }

    public void setPublicKeyGranted(Boolean publicKeyGranted) {
        this.publicKeyGranted = publicKeyGranted;
    }

    public String getPrivateKey() {

        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean isSecretKeyAccepted() {
        return secretKeyAccepted;
    }

    public void setSecretKeyAccepted(Boolean secretKeyAccepted) {
        this.secretKeyAccepted = secretKeyAccepted;
    }
}
