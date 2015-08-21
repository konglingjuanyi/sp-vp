package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.dao.diagnostic.IDiagnosticDao;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;
import com.zxq.iov.cloud.sp.vp.service.IDiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程诊断服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-24 10:06
 * modify date 2015-8-18 14:37
 * @version 0.6, 2015-8-18
 */
@Service
public class DiagnosticServiceImpl extends BaseService implements IDiagnosticService {

    @Autowired
    private IDiagnosticDao diagnosticDao;

    @Override
    public void requestDiagnostic(String vin, List<Diagnostic> diagnostics) throws ServLayerException {
        AssertRequired("vin,diagnostics", vin, diagnostics);
        Long tboxId = findTboxIdByVin(vin);
        for(Diagnostic diagnostic : diagnostics) {
            diagnostic.setVin(vin);
            diagnostic.setTboxId(tboxId);
            diagnosticDao.createDiagnostic(diagnostic);
        }
    }

    @Override
    public void responseDiagnostic(Long tboxId, List<Diagnostic> diagnostics) throws ServLayerException {
        AssertRequired("tboxId,diagnostics", tboxId, diagnostics);
        for(Diagnostic diagnostic : diagnostics) {
            diagnostic = diagnosticDao.findDiagnosticByEventId(diagnostic.getEventId());
            diagnostic.setResult(diagnostic.getResult());
            diagnosticDao.updateDiagnostic(diagnostic);
        }
    }
}