/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-04-23       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.config.KeyDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.config;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 密钥传输对象
 */
public class KeyDto extends OtaDto {

    // 公钥
    private byte[] publicKey;
    // 是否授权公钥
    private Boolean publicKeyGranted;
    // 私钥
    private String privateKey;
    // 密钥
    private String secretKey;
    // 密钥是否通过
    private Boolean secretKeyAccepted;
    // TBOX KEY
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
