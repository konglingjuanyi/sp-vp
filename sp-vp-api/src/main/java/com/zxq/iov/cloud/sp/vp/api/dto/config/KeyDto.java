package com.zxq.iov.cloud.sp.vp.api.dto.config;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 密钥DTO
 *
 * @author 叶荣杰
 * create date 2015-4-23 10:30
 * modify date 2015-8-18 17:36
 * @version 0.4, 2015-8-18
 */
public class KeyDto extends OtaDto {

    private byte[] publicKey;
    private Boolean publicKeyGranted;
    private String privateKey;
    private String secretKey;
    private Boolean secretKeyAccepted;
    private byte[] TboxKey;

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public Boolean getPublicKeyGranted() {
        return publicKeyGranted;
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

    public Boolean getSecretKeyAccepted() {
        return secretKeyAccepted;
    }

    public Boolean isSecretKeyAccepted() {
        return secretKeyAccepted;
    }

    public void setSecretKeyAccepted(Boolean secretKeyAccepted) {
        this.secretKeyAccepted = secretKeyAccepted;
    }

    public byte[] getTboxKey() {
        return TboxKey;
    }

    public void setTboxKey(byte[] tboxKey) {
        TboxKey = tboxKey;
    }
}
