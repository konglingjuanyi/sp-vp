package com.zxq.iov.cloud.sp.vp.dao.call;

import com.zxq.iov.cloud.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;

import java.util.List;

/**
 * 安防 呼叫持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-11 9:37
 * modify date
 * @version 0.1, 2015-6-11
 */
public interface ICallDaoService extends BaseService<Call, Long> {

	/**
	 * 创建呼叫
	 * @param call		呼叫对象
	 * @return			呼叫对象
	 */
	Call createCall(Call call);

	/**
	 * 更新呼叫
	 * @param call		呼叫对象
	 * @return			呼叫对象
	 */
	Call updateCall(Call call);

	/**
	 * 删除呼叫
	 * @param callId  	呼叫主键
	 */
	void removeCall(Long callId);

	/**
	 * 根据主键得到呼叫对象
	 * @param callId 	呼叫主键
	 * @return			呼叫对象
	 */
	Call findCallById(Long callId);

	/**
	 * 根据TBOX ID得到呼叫对象列表
	 * @param tboxId  	TBOX ID
	 * @param status 	状态
	 * @return        	呼叫对象列表
	 */
	List<Call> listCallByTboxId(Long tboxId, Integer status);
	
}