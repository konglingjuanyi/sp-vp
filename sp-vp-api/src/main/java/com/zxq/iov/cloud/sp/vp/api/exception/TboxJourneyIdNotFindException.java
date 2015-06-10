package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * 安防 未找到TBOX行程ID异常类
 *
 * @author 叶荣杰
 * create date 2015-6-9 16:24
 * @version 0.1, 2015-6-9
 */
public class TboxJourneyIdNotFindException extends TemplateException{

    public TboxJourneyIdNotFindException() {
        super();
    }

    public TboxJourneyIdNotFindException(int errorCode) {
        super(errorCode + "");
    }

    public TboxJourneyIdNotFindException(String errorCode) {
        super(errorCode);
    }

}
