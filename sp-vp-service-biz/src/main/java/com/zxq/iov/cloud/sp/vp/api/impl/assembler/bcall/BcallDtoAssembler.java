package com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallDto;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;

/**
 * 安防 bCall传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-11 10:51
 * modify date
 * @version 0.1, 2015-6-11
 */
public class BcallDtoAssembler {

    private static final Integer BCALL = 1;

    public Call fromDto(final BcallDto bcallDto) {
        return new Call(bcallDto.getTboxId(), BCALL, bcallDto.getCallType(), bcallDto.getStartTime(),
                bcallDto.getEndTime());
    }

    public BcallDto toDto(final Call call) {
        return new BcallDto(call.getId(), call.getTboxId(), call.getCallType(), call.getStartTime(),
                call.getEndTime());
    }
}
