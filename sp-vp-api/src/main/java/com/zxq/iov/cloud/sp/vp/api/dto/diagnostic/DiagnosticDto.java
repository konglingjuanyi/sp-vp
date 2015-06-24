package com.zxq.iov.cloud.sp.vp.api.dto.diagnostic;


import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 远程诊断DTO
 *
 * @author 叶荣杰
 * create date 2015-6-23 11:55
 * modify date 2015-6-24 10:08
 * @version 0.1, 2015-6-24
 */
public class DiagnosticDto extends OtaDto {

    private Long id;
    private String canId;
    private String serviceId;
    private String parameter;
    private String result;

    public DiagnosticDto() {}

    public DiagnosticDto(String canId, String serviceId, String parameter) {
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

    public String getCanId() {
        return canId;
    }

    public void setCanId(String canId) {
        this.canId = canId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
