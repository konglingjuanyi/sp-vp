package com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall;

import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防 eCall通话记录传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:26
 * modify date 2015-6-26 9:59
 * @version 0.3, 2015-6-26
 */
public class IcallRecordDtoAssembler {

    public IcallRecordDto toDto(final CallRecord callRecord) {
        return new IcallRecordDto(callRecord.getCallNumber());
    }
}
