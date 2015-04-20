package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 15-1-13
 * Time: 上午8:32
 */
public class TemplateException extends RuntimeException{

    public TemplateException() {
        super();
    }

    public TemplateException(int errorCode) {
        super(errorCode + "");
    }

    public TemplateException(String errorCode) {
        super(errorCode);
    }

}
