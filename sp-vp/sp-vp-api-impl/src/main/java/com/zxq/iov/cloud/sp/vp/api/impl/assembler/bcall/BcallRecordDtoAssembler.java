package com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;

/**
 * 安防 bCall通话记录传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-6-11 11:05
 * modify date 2015-6-24 17:06
 * @version 0.2, 2015-6-24
 */
public class BcallRecordDtoAssembler {

    public BcallRecordDto toDto(final CallRecord callRecord) {
        return new BcallRecordDto(callRecord.getCallNumber());
    }
}
