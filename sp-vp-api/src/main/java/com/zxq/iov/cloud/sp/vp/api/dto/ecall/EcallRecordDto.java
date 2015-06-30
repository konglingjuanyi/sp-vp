package com.zxq.iov.cloud.sp.vp.api.dto.ecall;

import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;

/**
 * 安防 eCall通话传输对象
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:20
 * modify date 2015-6-25 11:30
 * @version 0.2, 2015-6-25
 */
public class EcallRecordDto extends OtaDto {

    private String callNumber;

    public EcallRecordDto() {}

    public EcallRecordDto(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

}
