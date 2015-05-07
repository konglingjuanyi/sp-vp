package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * 安防 AID不匹配异常类
 *
 * @author 叶荣杰
 * create date 2015-5-7 9:59
 * @version 0.1, 2015-5-7
 */
public class AidNotMatchException extends TemplateException{

    public AidNotMatchException() {
        super();
    }

    public AidNotMatchException(int errorCode) {
        super(errorCode + "");
    }

    public AidNotMatchException(String errorCode) {
        super(errorCode);
    }

}
