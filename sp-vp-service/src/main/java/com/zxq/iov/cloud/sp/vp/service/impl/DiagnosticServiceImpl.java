package com.zxq.iov.cloud.sp.vp.service.impl;

import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
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
 * modify date 2015-8-5 16:34
 * @version 0.5, 2015-8-5
 */
@Service
public class DiagnosticServiceImpl extends BaseService implements IDiagnosticService {

    @Autowired
    private IDiagnosticDao diagnosticDao;
    @Autowired
    private ITboxDao tboxDao;

    @Override
    public void requestDiagnostic(String vin, List<Diagnostic> diagnostics) throws Exception {
        AssertRequired("vin,diagnostics", vin, diagnostics);
        Long tboxId = tboxDao.findTboxIdByVin(vin);
        for(Diagnostic diagnostic : diagnostics) {
            diagnostic.setVin(vin);
            diagnostic.setTboxId(tboxId);
            diagnosticDao.createDiagnostic(diagnostic);
        }
    }

    @Override
    public void responseDiagnostic(Long tboxId, List<Diagnostic> diagnostics) {
        for(Diagnostic diagnostic : diagnostics) {
            diagnostic = diagnosticDao.findDiagnosticByEventId(diagnostic.getEventId());
            diagnostic.setResult(diagnostic.getResult());
            diagnosticDao.updateDiagnostic(diagnostic);
        }
    }
}