/*
 * Licensed to SAICMotor,Inc. under the terms of the SAICMotor
 * Software License version 1.0.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author      Version        Comments
 * 2015-06-23       荣杰         1.0            Initial Version
 * 2015-08-18       荣杰         1.1
 *
 * com.zxq.iov.cloud.sp.vp.service.impl.RemoteKeyServiceImpl
 *
 * sp - sp-vp-service
 */

package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.saicmotor.telematics.framework.core.logger.Logger;
import com.saicmotor.telematics.framework.core.logger.LoggerFactory;
import com.zxq.iov.cloud.sp.vp.common.constants.Constants;
import com.zxq.iov.cloud.sp.vp.common.util.MsgUtil;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDao;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import com.zxq.iov.cloud.sp.vp.service.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.service.domain.Tbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防服务 电子钥匙服务接口实现类
 */
@Service
public class RemoteKeyServiceImpl extends BaseService implements IRemoteKeyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteKeyServiceImpl.class);

    @Autowired
    private IRemoteKeyDao remoteKeyDao;

    @Override
    public void requestWriteKey(Tbox tbox, Integer keyType, String keyValue, Long keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) throws ServLayerException {
        AssertRequired("vin,keyType,keyValue", tbox.getVin(), keyType, keyValue);
        RemoteKey remoteKey = new RemoteKey(tbox.getTboxId(), keyType, keyValue, keyReference,
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