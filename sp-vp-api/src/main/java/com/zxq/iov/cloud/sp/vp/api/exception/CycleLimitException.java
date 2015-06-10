package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * 安防 存在子元素异常类
 *
 * @author 叶荣杰
 * create date 2015-6-4 11:21
 * @version 0.1, 2015-6-4
 */
public class CycleLimitException extends TemplateException{

    public CycleLimitException() {
        super();
    }

    public CycleLimitException(int errorCode) {
        super(errorCode + "");
    }

    public CycleLimitException(String errorCode) {
        super(errorCode);
    }

}
