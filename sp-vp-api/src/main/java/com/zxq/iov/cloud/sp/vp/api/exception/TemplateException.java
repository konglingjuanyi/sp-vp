package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * 安防 模板异常类
 *
 * @author 叶荣杰
 * create date 2015-1-13 13:01
 * modify date 2015-7-21 13:15
 * @version 0.2, 2015-7-21
 */
public class TemplateException extends RuntimeException{

    public TemplateException() {
        super();
    }

    public TemplateException(String errorCode) {
        super(errorCode);
    }

    public TemplateException(String errorCode, String msg) {
        super(errorCode);
    }

}
