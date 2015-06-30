package com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall;

import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防 eCall通话记录传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:33
 * modify date 2015-6-26 9:59
 * @version 0.3, 2015-6-26
 */
public class EcallRecordDtoAssembler {

    public EcallRecordDto toDto(final CallRecord callRecord) {
        return new EcallRecordDto(callRecord.getCallNumber());
    }
}
