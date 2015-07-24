package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;

import java.util.List;

/**
 * 安防服务 远程诊断接口
 * @author 叶荣杰
 * create date 2015-6-23 17:45
 * modify date 2015-7-24 11:19
 * @version 0.3, 2015-7-24
 */
public interface IDiagnosticService {

    /**
     * 请求远程诊断
     * @param vin                   车辆唯一码
     * @param diagnosticDtos        远程诊断传输对象列表
     */
    void requestDiagnostic(String vin, List<DiagnosticDto> diagnosticDtos) throws Exception;

    /**
     * 响应远程诊断请求
     * @param otaDto                OTA传输对象
     * @param diagnosticDtos        远程诊断传输对象列表
     */
    void responseDiagnostic(OtaDto otaDto, List<DiagnosticDto> diagnosticDtos) throws Exception;

}
