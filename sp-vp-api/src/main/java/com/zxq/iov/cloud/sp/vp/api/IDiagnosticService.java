package com.zxq.iov.cloud.sp.vp.api;

import com.zxq.iov.cloud.sp.vp.api.dto.diagnostic.DiagnosticDto;

/**
 * 安防服务 远程诊断接口
 * @author 叶荣杰
 * create date 2015-6-23 17:45
 * modify date
 * @version 0.1, 2015-6-23
 */
public interface IDiagnosticService {

    /**
     * 请求远程诊断
     * @param diagnosticDto         远程诊断传输对象
     */
    void requestDiagnostic(DiagnosticDto diagnosticDto);

    /**
     * 响应远程诊断请求
     * @param diagnosticDto         远程诊断传输对象
     */
    void responseDiagnostic(DiagnosticDto diagnosticDto);

}
