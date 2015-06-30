package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * 安防 MID不匹配异常类
 *
 * @author 叶荣杰
 * create date 2015-6-25 10:21
 * @version 0.1, 2015-6-25
 */
public class MidNotMatchException extends TemplateException{

    public MidNotMatchException() {
        super();
    }

    public MidNotMatchException(int errorCode) {
        super(errorCode + "");
    }

    public MidNotMatchException(String errorCode) {
        super(errorCode);
    }

}
