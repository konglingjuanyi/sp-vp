package com.zxq.iov.cloud.sp.vp.dao.diagnostic;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.diagnostic.Diagnostic;

/**
 * 安防 远程诊断持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-24 9:11
 * modify date
 * @version 0.1, 2015-6-24
 */
public interface IDiagnosticDaoService extends BaseService<Diagnostic, Long> {

	/**
	 * 创建远程诊断
	 * @param diagnostic	远程诊断对象
	 * @return				远程诊断对象
	 */
	Diagnostic createDiagnostic(Diagnostic diagnostic);

	/**
	 * 更新远程诊断
	 * @param diagnostic	远程诊断对象
	 * @return				远程诊断对象
	 */
	Diagnostic updateDiagnostic(Diagnostic diagnostic);

	/**
	 * 删除远程诊断
	 * @param diagnosticId  远程诊断ID
	 */
	void removeDiagnostic(Long diagnosticId);

	/**
	 * 根据主键得到远程诊断对象
	 * @param diagnosticId 	远程诊断主键
	 * @return				远程诊断对象
	 */
	Diagnostic findDiagnosticById(Long diagnosticId);

	/**
	 * 根据事件实例ID得到远程诊断对象
	 * @param eventId     	事件实例ID
	 * @return            	远程诊断对象
	 */
	Diagnostic findDiagnosticByEventId(Long eventId);
	
}