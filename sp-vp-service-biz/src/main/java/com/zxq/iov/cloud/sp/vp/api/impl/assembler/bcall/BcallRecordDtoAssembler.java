package com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防 bCall通话记录传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-11 11:05
 * modify date
 * @version 0.1, 2015-6-11
 */
public class BcallRecordDtoAssembler {

    public CallRecord fromDto(final BcallRecordDto bcallRecordDto) {
        return new CallRecord(bcallRecordDto.getCallId(), bcallRecordDto.getCallNumber(), bcallRecordDto.getCallTime(),
                bcallRecordDto.getHangUpTime(), bcallRecordDto.getErrorCode());
    }

    public BcallRecordDto toDto(final CallRecord callRecord) {
        return new BcallRecordDto(callRecord.getId(), callRecord.getCallId(), callRecord.getCallNumber(),
                callRecord.getCallTime(), callRecord.getHangUpTime(), callRecord.getErrorCode());
    }
}
