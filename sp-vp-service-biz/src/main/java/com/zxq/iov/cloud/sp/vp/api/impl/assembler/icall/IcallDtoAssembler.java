package com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall;

import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallDto;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;

/**
 * 安防 iCall传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:22
 * modify date
 * @version 0.1, 2015-6-12
 */
public class IcallDtoAssembler {

    private static final Integer ICALL = 3;

    public Call fromDto(final IcallDto icallDto) {
        return new Call(icallDto.getTboxId(), ICALL, icallDto.getCallType(), icallDto.getStartTime(),
                icallDto.getEndTime());
    }

    public IcallDto toDto(final Call call) {
        return new IcallDto(call.getId(), call.getTboxId(), call.getCallType(), call.getStartTime(),
                call.getEndTime());
    }
}
