package com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall;

import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallDto;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;

/**
 * 安防 eCall传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:27
 * modify date
 * @version 0.1, 2015-6-12
 */
public class EcallDtoAssembler {

    private static final Integer ECALL = 2;

    public Call fromDto(final EcallDto ecallDto) {
        return new Call(ecallDto.getTboxId(), ECALL, ecallDto.getCallType(), ecallDto.getStartTime(),
                ecallDto.getEndTime());
    }

    public EcallDto toDto(final Call call) {
        return new EcallDto(call.getId(), call.getTboxId(), call.getCallType(), call.getStartTime(),
                call.getEndTime());
    }
}
