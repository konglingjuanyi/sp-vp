package com.zxq.iov.cloud.sp.vp.api.impl.assembler.key;

import com.zxq.iov.cloud.sp.vp.api.dto.key.RemoteKeyDto;
import com.zxq.iov.cloud.sp.vp.entity.key.RemoteKey;

/**
 * 安防 电子钥匙传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-15 13:07
 * modify date
 * @version 0.1, 2015-6-15
 */
public class RemoteKeyDtoAssembler {

    public RemoteKey fromDto(final RemoteKeyDto remoteKeyDto) {
        return new RemoteKey(remoteKeyDto.getTboxId(), remoteKeyDto.getType(), remoteKeyDto.getValue(),
                remoteKeyDto.getReference(), remoteKeyDto.getValidStartTime(), remoteKeyDto.getValidEndTime());
    }

}
