/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-07-17       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto
 *
 * sp - sp-vp-api
 */

package com.zxq.iov.cloud.sp.vp.api.dto.diagnostic;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防服务 远程诊断传输对象
 */
public class DiagnosticDto extends OtaDto {

    // 诊断ID
    private Long id;
    // CAN ID
    private byte[] canId;
    // 服务ID
    private byte[] serviceId;
    // 诊断参数
    private byte[] parameter;
    // 诊断结果
    private byte[] result;

    public DiagnosticDto() {}

    public DiagnosticDto(byte[] canId, byte[] serviceId, byte[] parameter) {
        this.canId = canId;
        this.serviceId = serviceId;
        this.parameter = parameter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getCanId() {
        return canId;
    }

    public void setCanId(byte[] canId) {
        this.canId = canId;
    }

    public byte[] getServiceId() {
        return serviceId;
    }

    public void setServiceId(byte[] serviceId) {
        this.serviceId = serviceId;
    }

    public byte[] getParameter() {
        return parameter;
    }

    public void setParameter(byte[] parameter) {
        this.parameter = parameter;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }
}
