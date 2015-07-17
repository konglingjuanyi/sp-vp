package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.MsgUtil;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安防 电子钥匙服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:47
 * modify date 2015-7-17 17:45
 * @version 0.2, 2015-7-17
 */
@Service
@Qualifier("remoteKeyService")
public class RemoteKeyServiceImpl implements IRemoteKeyService {

    @Autowired
    private IRemoteKeyDaoService remoteKeyDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;

    @Override
    public void requestWriteKey(String vin, Integer keyType, byte[] keyValue, Integer keyReference,
                                Date keyValidityStartTime, Date keyValidityEndTime) {
        Long tboxId = tboxDaoService.findTboxIdByVin(vin);
        RemoteKey remoteKey = new RemoteKey(tboxId, keyType,
                BinaryAndHexUtil.bytesToHexString(keyValue, false), keyReference,
                keyValidityStartTime, keyValidityEndTime);
        remoteKeyDaoService.createRemoteKey(remoteKey);
    }

    @Override
    public void responseWriteKey(OtaDto otaDto, Boolean writeSuccess, Integer writeFailureReason) {
        if(writeSuccess) {
            // 通知请求者成功
        }
        else {
            // 通知请求者失败，并告知理由
            // 物理删除电子钥匙
        }
    }

    @Override
    public void requestDeleteKey(String vin, Integer keyReference) {

    }

    @Override
    public void responseDeleteKey(OtaDto otaDto, Boolean deleteSuccess, Integer deleteFailureReason) {
        if(deleteSuccess) {
            // 通知请求者成功
        }
        else {
            // 通知请求者失败，并告知理由
            // 标记或删除电子钥匙
        }
    }

    @Override
    public void keyAlarm(OtaDto otaDto) {
        String mobileId = null; // 根据TBOX ID找到车主手机设备
        MsgUtil.pushDevice(mobileId, Constants.MSG_KEY_ALARM);
    }
}
