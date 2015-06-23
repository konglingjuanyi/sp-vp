package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IRemoteKeyService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.key.RemoteKeyDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.MsgUtil;
import com.zxq.iov.cloud.sp.vp.dao.key.IRemoteKeyDaoService;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 电子钥匙服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-23 13:47
 * modify date
 * @version 0.1, 2015-6-23
 */
@Service
@Qualifier("remoteKeyService")
public class RemoteKeyServiceImpl implements IRemoteKeyService {

    @Autowired
    private IRemoteKeyDaoService remoteKeyDaoService;

    @Override
    public void requestWriteKey(RemoteKeyDto remoteKeyDto) {
        RemoteKey remoteKey = new RemoteKeyDtoAssembler().fromDto(remoteKeyDto);
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
    public void requestDeleteKey(Long tboxId, Integer keyReference) {

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
