package com.zxq.iov.cloud.sp.vp.api.dto.bcall;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 bCall通话传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-10 17:11
 * modify date 2015-6-24 15:55
 * @version 0.3, 2015-6-24
 */
public class BcallRecordDto extends OtaDto {

    private String callNumber;

    public BcallRecordDto() {}

    public BcallRecordDto(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

}
