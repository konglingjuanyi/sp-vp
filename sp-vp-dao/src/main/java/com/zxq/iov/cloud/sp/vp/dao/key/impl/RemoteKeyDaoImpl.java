package com.zxq.iov.cloud.sp.vp.dao.key.impl;

import com.saicmotor.telematics.framework.core.log.LoggerFactory;
import com.saicmotor.telematics.framework.core.service.BaseServiceImpl;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao;
import com.zxq.iov.cloud.sp.vp.dao.key.repo.IRemoteKeyRepository;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安防 电子钥匙持久化服务接口实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:18
 * modify date
 * @version 0.1, 2015-6-23
 */
@Service
public class RemoteKeyDaoImpl extends BaseServiceImpl<IRemoteKeyRepository, RemoteKey, Long> implements IRemoteKeyDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyDaoImpl.class);

    @Autowired
	public RemoteKeyDaoImpl(IRemoteKeyRepository repo){
		super(repo);
	}

	@Override
	public RemoteKey createRemoteKey(RemoteKey remoteKey) {
		if (remoteKey == null){
			LOGGER.error("remoteKey cannot be null");
		}
		remoteKey.setId(null);
		super.save(remoteKey);
		return remoteKey;
	}

	@Override
	public RemoteKey updateRemoteKey(RemoteKey remoteKey) {
		if (remoteKey == null){
			LOGGER.error("remoteKey cannot be null");
		}
		super.update(remoteKey);
		return remoteKey;
	}

	@Override
	public void removeRemoteKey(Long remoteKeyId) {
		if (remoteKeyId == null){
			LOGGER.error("remoteKeyId cannot be null");
		}
		super.delete(remoteKeyId);
	}

	@Override
	public RemoteKey findRemoteKeyById(Long remoteKeyId) {
		if (remoteKeyId == null){
			LOGGER.error("remoteKeyId cannot be null");
		}
		return super.findOne(remoteKeyId);
	}

}