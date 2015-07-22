package com.zxq.iov.cloud.sp.vp.api.exception;


/**
 * 安防 参数为空异常类
 *
 * @author 叶荣杰
 * create date 2015-7-21 13:01
 * @version 0.1, 2015-7-21
 */
public class ParamsIsNullException extends TemplateException{

    private final static String ERROR_CODE = "";

    public ParamsIsNullException() {
        super(ERROR_CODE);
    }

    public ParamsIsNullException(String param) {
        super(ERROR_CODE, param);
    }

}
