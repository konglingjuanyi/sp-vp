package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.MsgUtil;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 电子钥匙服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:47
 * modify date 2015-8-18 14:42
 * @version 0.7, 2015-8-18
 */
@Service
public class RemoteKeyServiceImpl extends BaseService implements IRemoteKeyService {

    @Autowired
    private IRemoteKeyDao remoteKeyDao;

    @Override
    public void requestWriteKey(String vin, Integer keyType, String keyValue, Long keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) throws ServLayerException {
        AssertRequired("vin,keyType,keyValue", vin, keyType, keyValue);
        RemoteKey remoteKey = new RemoteKey(findTboxIdByVin(vin), keyType, keyValue, keyReference,
                keyValidityStartTime, keyValidityEndTime);
        remoteKeyDao.createRemoteKey(remoteKey);
    }

    @Override
    public void responseWriteKey(Long tboxId, Boolean writeSuccess, Integer writeFailureReason) {
        if(writeSuccess) {
            // 通知请求者成功
        }
        else {
            // 通知请求者失败，并告知理由
            // 物理删除电子钥匙
        }
    }

    @Override
    public void requestDeleteKey(String vin, Long keyReference) throws ServLayerException {
        AssertRequired("vin,keyReference", vin, keyReference);
    }

    @Override
    public void responseDeleteKey(Long tboxId, Boolean deleteSuccess, Integer deleteFailureReason) {
        if(deleteSuccess) {
            // 通知请求者成功
        }
        else {
            // 通知请求者失败，并告知理由
            // 标记或删除电子钥匙
        }
    }

    @Override
    public void keyAlarm(Long tboxId) {
        String mobileId = null; // 根据TBOX ID找到车主手机设备
        MsgUtil.pushDevice(mobileId, Constants.MSG_KEY_ALARM);
    }
}