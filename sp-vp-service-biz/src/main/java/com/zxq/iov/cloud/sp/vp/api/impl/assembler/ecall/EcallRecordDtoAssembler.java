package com.zxq.iov.cloud.sp.vp.api.impl.assembler.ecall;

import com.zxq.iov.cloud.sp.vp.api.dto.ecall.EcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防 eCall通话记录传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:33
 * modify date
 * @version 0.1, 2015-6-12
 */
public class EcallRecordDtoAssembler {

    public CallRecord fromDto(final EcallRecordDto ecallRecordDto) {
        return new CallRecord(ecallRecordDto.getCallId(), ecallRecordDto.getCallNumber(), ecallRecordDto.getCallTime(),
                ecallRecordDto.getHangUpTime(), ecallRecordDto.getErrorCode());
    }

    public EcallRecordDto toDto(final CallRecord callRecord) {
        return new EcallRecordDto(callRecord.getId(), callRecord.getCallId(), callRecord.getCallNumber(),
                callRecord.getCallTime(), callRecord.getHangUpTime(), callRecord.getErrorCode());
    }
}
