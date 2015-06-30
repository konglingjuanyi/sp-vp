package com.zxq.iov.cloud.sp.vp.api.dto.icall;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 iCall通话传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:17
 * modify date 2015-6-25 11:16
 * @version 0.2, 2015-6-25
 */
public class IcallRecordDto extends OtaDto {

    private String callNumber;

    public IcallRecordDto() {}

    public IcallRecordDto(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

}
