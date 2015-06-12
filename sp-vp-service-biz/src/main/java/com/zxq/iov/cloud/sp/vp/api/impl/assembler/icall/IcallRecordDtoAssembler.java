package com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall;

import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防 eCall通话记录传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:26
 * modify date
 * @version 0.1, 2015-6-12
 */
public class IcallRecordDtoAssembler {

    public CallRecord fromDto(final IcallRecordDto icallRecordDto) {
        return new CallRecord(icallRecordDto.getCallId(), icallRecordDto.getCallNumber(), icallRecordDto.getCallTime(),
                icallRecordDto.getHangUpTime(), icallRecordDto.getErrorCode());
    }

    public IcallRecordDto toDto(final CallRecord callRecord) {
        return new IcallRecordDto(callRecord.getId(), callRecord.getCallId(), callRecord.getCallNumber(),
                callRecord.getCallTime(), callRecord.getHangUpTime(), callRecord.getErrorCode());
    }
}
