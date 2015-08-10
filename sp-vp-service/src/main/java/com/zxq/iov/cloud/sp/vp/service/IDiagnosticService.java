package com.zxq.iov.cloud.sp.vp.service;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;

import java.util.List;

/**
 * 安防服务 远程诊断接口
 * @author 叶荣杰
 * create date 2015-6-23 17:45
 * modify date 2015-8-5 16:33
 * @version 0.4, 2015-8-5
 */
public interface IDiagnosticService {

    /**
     * 请求远程诊断
     * @param vin                   车辆唯一码
     * @param diagnostics           远程诊断对象列表
     */
    void requestDiagnostic(String vin, List<Diagnostic> diagnostics) throws ServLayerException;

    /**
     * 响应远程诊断请求
     * @param tboxId                TBOX ID
     * @param diagnostics           远程诊断对象列表
     */
    void responseDiagnostic(Long tboxId, List<Diagnostic> diagnostics) throws ServLayerException;

}
