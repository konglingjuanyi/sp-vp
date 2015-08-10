package com.zxq.iov.cloud.sp.vp.dao.key;

import com.saicmotor.telematics.framework.core.service.BaseService;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

/**
 * 安防 电子钥匙持久化服务接口
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:11
 * modify date
 * @version 0.1, 2015-6-23
 */
public interface IRemoteKeyDao extends BaseService<RemoteKey, Long> {

	/**
	 * 创建电子钥匙
	 * @param remoteKey		电子钥匙对象
	 * @return				电子钥匙对象
	 */
	RemoteKey createRemoteKey(RemoteKey remoteKey);

	/**
	 * 更新电子钥匙
	 * @param remoteKey		电子钥匙对象
	 * @return				电子钥匙对象
	 */
	RemoteKey updateRemoteKey(RemoteKey remoteKey);

	/**
	 * 删除电子钥匙
	 * @param remoteKeyId  	电子钥匙ID
	 */
	void removeRemoteKey(Long remoteKeyId);

	/**
	 * 根据主键得到电子钥匙对象
	 * @param remoteKeyId 	电子钥匙主键
	 * @return				电子钥匙对象
	 */
	RemoteKey findRemoteKeyById(Long remoteKeyId);
	
}