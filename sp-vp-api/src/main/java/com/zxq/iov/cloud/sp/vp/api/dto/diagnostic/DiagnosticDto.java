package com.zxq.iov.cloud.sp.vp.api.dto.diagnostic;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 远程诊断DTO
 *
 * @author 叶荣杰
 * create date 2015-6-23 11:55
 * modify date 2015-7-17 17:16
 * @version 0.3, 2015-7-17
 */
public class DiagnosticDto extends OtaDto {

    private Long id;
    private byte[] canId;
    private byte[] serviceId;
    private byte[] parameter;
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
